package action;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.config.AlipayConfig;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import entity.Cart2;
import entity.Customer;
import entity.Goods;
import entity.Item;
import entity.Order;
import entity.PageBean;
import myutils.CartUtil;
import myutils.EshopUtil;
import myutils.OrderUtil;
import service.IGoodsService;
import service.IOrderService;

public class OrderAction extends ActionSupport implements ModelDriven<Order> {

	private String address;
	private String message;// 顾客留言
	private String cusername;
	private String oid;
	private Order order = new Order();
	private IOrderService orderService;
	private IGoodsService goodsService;
	private PageBean pageBean;
	private Integer currPage = 1;
	private Integer pageSize = 6;
	private List<Order> orders;

	@Override
	public Order getModel() {
		// TODO Auto-generated method stub
		return order;
	}
	
	/**
	 * 生成订单
	 * 
	 * @return
	 */
	public String createOrder() {
		System.out.println("order=" + order);
		Cart2 tempCart = (Cart2) ServletActionContext.getRequest().getSession().getAttribute("tempCart");
		// 判断要购买的商品是否大于库存量，返回的是一个数量大于库存量的条目
		Item item = OrderUtil.checkStock(tempCart.getOrder());
		if(item != null) {
			// 返回错误信息
			this.addActionError("商品:" + item.getGoods().getGname() + "，库存为" + item.getGoods().getGstock() + 
					"，当前数量以超过库存上限");
			return "myCartList";
		}
		
		Set<Item> its = tempCart.getOrder().getItems();
		for(Item it : its) {
			Goods goods = it.getGoods();
			goods.setGstock(goods.getGstock()-it.getNumber());
			goodsService.editGoods(goods);
		}
		
		Order orderToSave = new Order();
		Set<Item> cartItems = tempCart.getOrder().getItems();
		String[] gids = new String[cartItems.size()];// 用于获取订单中商品的id

		orderToSave.setOid(EshopUtil.createUUID());// 设置id
		orderToSave.setTime(EshopUtil.getNowTime());// 设置下单时间
		orderToSave.setCustomer(tempCart.getCustomer());// 设置所属顾客
		orderToSave.setTotalprice(tempCart.getTotal());// 设置合计
		orderToSave.setAddress(order.getAddress());// 设置收货地址
		orderToSave.setMessage(order.getMessage());// 设置顾客留言
		orderToSave.setConsigneeName(order.getConsigneeName());
		orderToSave.setOstate("0");// 设置订单状态（0表示：未付款）
		orderToSave.setConsigneePhone(order.getConsigneePhone());// 设置收货人电话
		
		Set<Item> items = new HashSet<Item>();
		int index = 0;
		for (Item it : cartItems) {
			it.setOrder(orderToSave);
			items.add(it);
			gids[index] = it.getGoods().getGid();
			index++;
		}
		orderToSave.setItems(items);
		orderService.orderAdd(orderToSave);
		Cart2 cart = (Cart2) ServletActionContext.getRequest().getSession().getAttribute("cart");
		// 把生成订单的商品从购物车中删除
		cart = CartUtil.deleteItemList(cart, gids);
		// 并从新保存到session中
		ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		// 将临时的购物车删除
		ServletActionContext.getRequest().getSession().removeAttribute("tempCart");
		ServletActionContext.getRequest().setAttribute("order", orderToSave);
		return "createOrder";
	}
	
	/**
	 * 查看某个顾客全部订单
	 * @return
	 */
	public String customerOrderList() {
		
		if(!EshopUtil.customerIsLogin()) {
			return "login";
		}
		
		Customer cu = (Customer) ServletActionContext.getRequest().getSession().getAttribute("customer");
		//orders = orderService.findCustomerAllOrder(cusername);
		DetachedCriteria criteria = DetachedCriteria.forClass(Order.class);
		criteria.add(Restrictions.eq("customer.cusername", cu.getCusername()));
		pageBean = orderService.findOrderByPageBean(criteria, currPage, pageSize);
		return "customerOrderList";
	}
	
	/**
	 * 查找指定的订单
	 * @return
	 */
	public String findOrderById() {
		order = orderService.findOrderById(order.getOid());
		// 计算单价
		order = OrderUtil.claUnitPrice(order);
		ServletActionContext.getRequest().setAttribute("order", order);
		return "findOrderById";
	}
	
	/**
	 * 更改订单状态
	 * @return
	 */
	public String changeOrderState() {
		Order orderToUpdate = orderService.findOrderById(order.getOid());
		orderToUpdate.setOstate(order.getOstate());
		orderService.updateOrder(orderToUpdate);
		
		return "changeOrderState";
	}
	
	/**
	 * 准备单独购买一个商品（“立即购买”，没有经过购物车直接购买）
	 * @return
	 */
	public String readyToBuyOne() {
		
		if(!EshopUtil.customerIsLogin()) {
			return "login";
		}
		
		String gid = ServletActionContext.getRequest().getParameter("gid");
		String num = ServletActionContext.getRequest().getParameter("num");
		int number = Integer.parseInt(num);
		Goods goods = goodsService.findGoodsById(gid);
		
		if(number > goods.getGstock()) {
			goods = goodsService.findGoodsById(gid);
			this.addActionError("该商品库存为" + goods.getGstock() + "，您选购的数量以超过库存上限");
			ActionContext.getContext().getValueStack().push(goods);
			return "gtStock";
		}
		
		Set<Item> items = new HashSet<Item>();
		Item item = new Item();
		item.setGoods(goods);
		item.setNumber(number);
		item.setUprice(goods.getGprice());
		item.setPrice(item.getPriceInCart());
		items.add(item);
		Order orderToSave = new Order();
		orderToSave.setOid(EshopUtil.createUUID());// 设置id
		item.setOrder(orderToSave);
		orderToSave.setItems(items);
		orderToSave.setTotalprice(item.getPriceInCart());
		//Cart2 tempCart = new Cart2();
		//tempCart.setOrder(orderToSave);
		ServletActionContext.getRequest().getSession().setAttribute("orderToSave", orderToSave);
		return "readyToBuyOne";
	}
	
	/**
	 * 没有经过购物车直接购买
	 * @return
	 */
	public String buyOneItem() {
		Order orderToSave = (Order) ServletActionContext.getRequest().getSession().getAttribute("orderToSave");
		Customer customer = (Customer) ServletActionContext.getRequest().getSession().getAttribute("customer");
		// 判断要购买的商品是否大于库存量，返回的是一个数量大于库存量的条目
		Item item = OrderUtil.checkStock(orderToSave);
		if(item != null) {
			this.addActionError("商品:" + item.getGoods().getGname() + "，库存为" + item.getGoods().getGstock() + 
					"，当前数量以超过库存上限");
			return "readyToBuyOne";
		}
		
		Set<Item> its = orderToSave.getItems();
		for(Item it : its) {
			Goods goods = it.getGoods();
			goods.setGstock(goods.getGstock()-it.getNumber());
			goodsService.editGoods(goods);
		}
		
		orderToSave.setTime(EshopUtil.getNowTime());// 设置下单时间
		orderToSave.setCustomer(customer);// 设置顾客订单归属
		orderToSave.setAddress(order.getAddress());// 设置收货地址
		orderToSave.setMessage(order.getMessage());// 设置顾客留言
		orderToSave.setConsigneeName(order.getConsigneeName());
		orderToSave.setOstate("0");// 设置订单状态（0表示：未付款）
		orderToSave.setConsigneePhone(order.getConsigneePhone());// 设置收货人电话
		orderService.orderAdd(orderToSave);
		//ServletActionContext.getRequest().getSession().removeAttribute("orderToSave");
		ServletActionContext.getRequest().setAttribute("order", orderToSave);
		return "buyOneItem";
	}
	
	/**
	 * 支付
	 * @return
	 */
	public String pay() {
		order = orderService.findOrderById(order.getOid());
		return "pay";
	}

	/**
	 * 取消订单
	 * @return
	 */
	public String canceOrder() {
		Order or = orderService.findOrderById(order.getOid());// 获取订单编号
		Set<Item> items = or.getItems();// 获取该订单的的条目
		OrderUtil.backToStock(items);// 将订单中商品归还给数据库
		orderService.deleteOrder(order.getOid());// 删除订单
		return "canceOrder";
	}
	
	/**
	 * 删除
	 * @return
	 */
	public String deleteOrder() {
		orderService.deleteOrder(order.getOid());
		return "deleteOrder";
	}
	/**
	 * 校验是否付款成功，修改订单状态
	 * @return
	 * @throws Exception
	 */
	public String isPay() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		
		boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

		//——请在这里编写您的程序（以下代码仅作参考）——
		if(signVerified) {
			//商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		
			//支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
		
			//付款金额
			String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
			
			Order order = orderService.findOrderById(out_trade_no);
			order.setOstate("1");
			orderService.updateOrder(order);
			request.setAttribute("msg", "支付成功！");
		}else {
			Order order = orderService.findOrderById(new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8"));
			order.setOstate("4");
			OrderUtil.backToStock(order.getItems());// 将订单中商品归还给数据库
			orderService.updateOrder(order);
			request.setAttribute("msg", "支付异常！交易失败！");
		}
		return "isPay";
		
	}

	// -------------------getter and setter 方法-------------------
	
	
	
	public void setOrderService(IOrderService orderService) {
		this.orderService = orderService;
	}
	public Integer getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public void setGoodsService(IGoodsService goodsService) {
		this.goodsService = goodsService;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCusername() {
		return cusername;
	}
	public void setCusername(String cusername) {
		this.cusername = cusername;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
}
