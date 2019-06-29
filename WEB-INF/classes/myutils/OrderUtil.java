package myutils;

import java.math.BigDecimal;
import java.util.Set;

import entity.Goods;
import entity.Item;
import entity.Order;
import service.IGoodsService;

public class OrderUtil {

	private static IGoodsService goodsService;

	public static void setGoodsService(IGoodsService goodsService) {
		OrderUtil.goodsService = goodsService;
	}

	/**
	 * 计算单价
	 * @param order
	 * @return
	 */
	public static Order claUnitPrice(Order order) {
		for(Item item : order.getItems()) {
			BigDecimal num = new BigDecimal("" + item.getNumber());
			BigDecimal price = new BigDecimal("" + item.getPrice());
			BigDecimal uprice = price.divide(num);
			item.setUprice(uprice.doubleValue());
		}
		return order;
	}
	
	/**
	 * 判断当前订单的item中的数量是否大于数据库中商品的数量
	 * 返回大于库存的商品
	 * @param order
	 * @return 
	 */
	public static Item checkStock(Order order) {
		for (Item item : order.getItems()) {
			Goods goods = goodsService.findGoodsById(item.getGoods().getGid());
			item.setGoods(goods);
			if (item.getNumber() > goods.getGstock()) {
				item.setGoods(goods);
				return item;
			}
		}
		return null;
	}
	
	/**
	 * 将条目数目归还个商品库存
	 * @param items
	 */
	public static void backToStock(Set<Item> items) {
		for (Item item : items) {
			Goods go = item.getGoods();
			Goods goods = goodsService.findGoodsById(go.getGid());// 获取数据库中的商品
			if(goods != null) {
				goods.setGstock(goods.getGstock() + item.getNumber());// 回归库存
				goodsService.editGoods(goods);// 更改数据库
			} else {
				goodsService.addGoods(go);
			}
		}
	}
}
