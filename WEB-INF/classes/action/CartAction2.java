package action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import entity.Cart2;
import entity.Customer;
import entity.Goods;
import entity.Item;
import entity.Order;
import myutils.CartUtil;
import myutils.EshopUtil;
import myutils.OrderUtil;
import service.IGoodsService;
import service.IOrderService;

public class CartAction2 extends ActionSupport {

	private int num;
	private String gid;
	private String gidList;
	private IOrderService orderService;
	private IGoodsService goodsService;
	
	/**
	 * 前往购物车列表
	 * @return
	 */
	public String myCartList() {
		
		if(!EshopUtil.customerIsLogin()) {
			return "login";
		}
		
		Cart2 cart = (Cart2) ServletActionContext.getRequest().getSession().getAttribute("cart");
		Customer cu = (Customer) ServletActionContext.getRequest().getSession().getAttribute("customer");
		if(cart.getOrder() != null) {
			for(Item item : cart.getOrder().getItems()) {
				Goods goods = goodsService.findGoodsById(item.getGoods().getGid());
				if(item.getNumber() > goods.getGstock()) {
					this.addActionError("商品:" + goods.getGname() + "，库存为" + goods.getGstock() + 
							"，当前数量以超过库存上限");
					item.setGoods(goods);
					ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
					return "myCartList";
				}
			}
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return "myCartList";
	}
	
	/**
	 * 商品数量更改1（即加1或减1）
	 * @return
	 */
	public String goodsCountChangeOne() {
		// 获取购物车
		Cart2 cart = (Cart2) ServletActionContext.getRequest().getSession().getAttribute("cart");
		// System.out.println("CartAction2:num=" + num + "-----gid=" + gid);
		
		Goods goods = goodsService.findGoodsById(gid);
		Item item = cart.findItem(gid);
		if(goods.getGstock() == 0) {
			this.addActionError("商品:" + goods.getGname() +"，该商品已售罄");
			cart.updateStockInCart(gid, goods.getGstock());//使车中的商品的库存与数据库中的商品库存一致
			if(num > 0) {
				return "cartList";
			}
		}
		if(item.getNumber() + num > goods.getGstock()) {
			if(item.getNumber() == goods.getGstock()) {
				this.addActionError("商品:" + goods.getGname() + "，库存为" + goods.getGstock() + "，当前数量以超过库存上限");
				cart.updateStockInCart(gid, goods.getGstock());//使车中的商品的库存与数据库中的商品库存一致
				if(num > 0) {
					return "cartList";
				}
			} else if(item.getNumber() > goods.getGstock()) {
				this.addActionError("商品:" + goods.getGname() + "，库存为" + goods.getGstock() + "，当前数量以超过库存上限");
				cart.updateStockInCart(gid, goods.getGstock());//使车中的商品的库存与数据库中的商品库存一致
				if(num > 0) {
					return "cartList";
				}
			}
		}

		cart.itemCountChangeOne(gid, num);// 修改指定商品的数量
		cart.getOrder().setItems(cart.getOrder().getItems());// 更新cart里面商品条目，和cart的信息
		cart.getOrder().setTotalprice(cart.getTotal());// 修改总金额
		orderService.updateOrder(cart.getOrder());// 数据库更新
		// 将购物车重新放回到session中
		ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		return "cartList";
	}
	
	/**
	 * 往购物车中添加商品
	 * @return
	 */
	public String goodsAdd() {
		
		if(!EshopUtil.customerIsLogin()) {
			return "login";
		}
		
		// 获取购物车
		Cart2 cart = (Cart2) ServletActionContext.getRequest().getSession().getAttribute("cart");
		System.out.println("CartAction2.goodsAdd:num=" + num + "-----gid=" + gid);
		System.out.println("CartAction2.goodsAdd:goodsService=" + goodsService);
		Goods goods = goodsService.findGoodsById(gid);// 获取顾客添加到购物车中商品
		Item item = new Item();// 创建一个条目，保存顾客保存的商品
		item.setGoods(goods);// 条目中保存该商品
		
		item.setPrice(item.getPriceInCart());// 计算该条目的小计，并保存到数据库中
		
		if(cart.getOrder() == null) { // 如果该顾客购物车为空
			item.setNumber(num);// 保存该商品的数量
			Order order = new Order(); // 新建一个订单对象，用于保存购物车中的内容
			order.setOid(EshopUtil.createUUID());// 设置订单id
			item.setOrder(order);// 设置条目的归属订单
			item.setPrice(item.getPriceInCart());// 记录条目小计（保存到数据库）
			order.setOstate("5"); // 并设置这个订单为“购物车”
			order.getItems().add(item); // 订单添加商品
			cart.setOrder(order); // 并把这个订单放入购物车中
			order.setTotalprice(cart.getTotal());// 计算车中的商品的总金额
			// 设置该订单的归属者
			order.setCustomer(cart.getCustomer());
			orderService.orderAdd(order);// 将购物车的内容保存到数据库中
			// 将购物车重新放回到session中
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		} else {// 如果该顾客本的购物车不为空
			item.setNumber(num);// 保存该商品的数量
			item.setPrice(item.getPriceInCart());// 记录条目小计（保存到数据库）
			item.setOrder(cart.getOrder());
			cart.addItem(item);// 购物车中添加该商品信息
			cart.getOrder().setTotalprice(cart.getTotal());
			orderService.updateOrder(cart.getOrder());// 保存到数据库中
		}
		return "goodsAdd";
	}
	
	
	/**
	 * 批量删除购物车中的商品
	 * @return
	 */
	public String deleteList() {
		System.out.println("CartAction2.deleteList:gidList=" + gidList);
		String[] gids = gidList.split(",");
		if(gids.length < 1) {// 如果没有选中商品，则不操作，直接返回购物车
			return "cartList";
		}
		Cart2 cart = (Cart2) ServletActionContext.getRequest().getSession().getAttribute("cart");
		// 删除车中的商品
		cart = CartUtil.deleteItemList(cart, gids);
		ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		return "cartList";
	}
	
	/**
	 * 删除一个商品
	 * @return
	 */
	public String deleteItem() {
		System.out.println("CartAction2.deleteItem:gid=" + gid);
		Cart2 cart = (Cart2) ServletActionContext.getRequest().getSession().getAttribute("cart");
		cart.removeItem(gid, cart.getOrder().getOid());// 购物车从数据库中删除
		// 如果删除的是最后一条，则购物车置为空
		if(cart.getOrder().getItems().size() < 1) {
			orderService.deleteOrder(cart.getOrder().getOid());// 购物车从数据库中删除
			cart.setOrder(null);
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
			return "cartList";
		}
		cart.getOrder().setTotalprice(cart.getTotal());
		// 更新数据库中的购物车
		orderService.updateOrder(cart.getOrder());
		ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		return "cartList";
	}
	
	/**
	 * 即将生成订单
	 * @return
	 */
	public String readyToOrder() {
		System.out.println("CartAction2.createOrder:gidList=" + gidList);
		String[] gids = gidList.split(",");
		if(gids.length < 1) {// 如果没有选中商品，则不操作，直接返回购物车
			return "cartList";
		}
		Cart2 cart = (Cart2) ServletActionContext.getRequest().getSession().getAttribute("cart");
		Cart2 tempCart = new Cart2();
		
		Item item = OrderUtil.checkStock(cart.getOrder());
		if(item != null) {
			this.addActionError("商品:" + item.getGoods().getGname() + "，库存为" + item.getGoods().getGstock() + 
					"，当前数量以超过库存上限");
			return "myCartList";
		}
		
		// 给临时购物车赋值
		tempCart = CartUtil.createTempCart(cart, gids);
		ServletActionContext.getRequest().getSession().setAttribute("tempCart", tempCart);
		return "readyToOrder";
	}

	//-------------------getter and setter 方法-------------------
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getGidList() {
		return gidList;
	}
	public void setGidList(String gidList) {
		this.gidList = gidList;
	}
	public void setOrderService(IOrderService orderService) {
		this.orderService = orderService;
	}
	public void setGoodsService(IGoodsService goodsService) {
		this.goodsService = goodsService;
	}
}
