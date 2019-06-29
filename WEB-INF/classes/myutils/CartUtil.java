package myutils;

import java.util.HashSet;
import java.util.Set;

import entity.Cart2;
import entity.Item;
import entity.Order;
import service.IGoodsService;
import service.IOrderService;

public class CartUtil {
	
	private static IOrderService orderService;
	private static IGoodsService goodsService;
	
	/**
	 * 从数据库中查找出订单状态为5的订单，然后将其内容转化为购物车
	 * 
	 * @return
	 */
	public static Cart2 orderToCart2(String cusername) {
		Cart2 cart = new Cart2();
		Order order = orderService.findItemInCart(cusername);
		if(order != null) {
			cart.setOrder(order);
			return cart;
		} else {
			return null;
		}
		
	}

	/**
	 * 创建一个临时购物车
	 * @param cart
	 * @param gids
	 * @return
	 */
	public static Cart2 createTempCart(Cart2 cart, String[] gids) {
		Cart2 tempCart = new Cart2();// 创建一个新的临时购物车，用于记录顾客即将要生成订单的商品
		tempCart.setCustomer(cart.getCustomer());
		Order order = new Order();// 创建一个新订单
		/*
		 * 给订单设置相应的值
		 */
		order.setCustomer(cart.getCustomer());
		/*order.setTotalprice(cart.getOrder().getTotalprice());*/
		order.setTotalprice(5);
		// order.setOid(EshopUtil.createUUID());
		Set<Item> items = new HashSet<Item>();// 用于记录顾客即将要生成订单的商品

		if (gids.length == cart.getOrder().getItems().size()) {
			for (Item it : cart.getOrder().getItems()) {
				items.add(it);
			}
		} else {
			for (String gid : gids) {
				items.add(cart.findItem(gid));
			}
		}
		order.setItems(items);// 保存要购买的商品
		tempCart.setOrder(order);
		order.setTotalprice(tempCart.getTotal());
		return tempCart;
	}

	/**
	 * 批量删除车中的商品
	 * @param cart
	 * @param gids
	 * @return
	 */
	public static Cart2 deleteItemList(Cart2 cart, String[] gids) {
		if(gids.length == cart.getOrder().getItems().size()) {// 如果全选，则直接删除整个订单
			orderService.deleteOrder(cart.getOrder().getOid());// 购物车从数据库中删除
			cart.setOrder(null); // 将车清空
		} else {
			// 从购物车中删除商品
			for(String gid : gids) {
				cart.removeItem(gid, cart.getOrder().getOid());
			}
			cart.getOrder().setTotalprice(cart.getTotal());
			// 更新数据库中的购物车
			orderService.updateOrder(cart.getOrder());
		}
		return cart;
	}
	
	public void setOrderService(IOrderService orderService) {
		this.orderService = orderService;
	}
	public void setGoodsService(IGoodsService goodsService) {
		this.goodsService = goodsService;
	}
}
