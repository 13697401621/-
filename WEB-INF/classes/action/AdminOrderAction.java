package action;

import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import entity.Item;
import entity.Order;
import entity.PageBean;
import myutils.OrderUtil;
import service.IOrderService;

public class AdminOrderAction extends ActionSupport implements ModelDriven<Order> {

	private IOrderService orderService;
	private List<Order> orders;
	/**
	 * 分页参数
	 */
	private Integer currPage = 1;
	private Integer pageSize = 6;
	private PageBean pageBean = new PageBean<Order>();
	
	private Order order = new Order();
	@Override
	public Order getModel() {
		return order;
	}
	
	/**
	 * 根据状态查找订单
	 * 
	 * @return
	 */
	public String findAllOrderByState() {
		System.out.println("---------ostate=" + order.getOstate());
		if(currPage==null) {
			currPage = 1;
		}
		if(pageSize==null) {
			pageSize = 6;
		}
		DetachedCriteria criteria = DetachedCriteria.forClass(Order.class);
		criteria.add(Restrictions.eq("ostate", order.getOstate()));
		pageBean = orderService.findOrderByPageBean(criteria,currPage,pageSize);
		return "findAllOrderByState";
	}
	
	/**
	 * 查看订单详细
	 * @return
	 */
	public String orderDesc() {
		order = orderService.findOrderById(order.getOid());
		order = OrderUtil.claUnitPrice(order);
		return "orderDesc";
	}
	
	/**
	 * 管理员后台取消订单
	 * @return
	 */
	public String adminCanceOrder() {
		Order or = orderService.findOrderById(order.getOid());// 获取订单编号
		Set<Item> items = or.getItems();// 获取该订单的的条目
		OrderUtil.backToStock(items);// 将订单中商品归还给数据库
		or.setOstate("4");// 修改订单状态
		orderService.updateOrder(or);// 同步到数据库
		return "adminCanceOrder";
	}
	
	public String updateOrderState() {
		Order or = orderService.findOrderById(order.getOid());// 获取订单编号
		or.setOstate(order.getOstate());
		orderService.updateOrder(or);// 同步到数据库
		return "updateOrderState";
	}
	public String bkfindOrderByPageBean() {
		
		if(currPage==null) {
			currPage = 1;
		}
		if(pageSize==null) {
			pageSize = 6;
		}
		DetachedCriteria criteria = DetachedCriteria.forClass(Order.class);
		if(order.getOstate()!=null) {
			criteria.add(Restrictions.eq("ostate", order.getOstate()));
		}
		pageBean = orderService.findOrderByPageBean(criteria,currPage,pageSize);
		
		return "bkfindOrderByPageBean";
	}
	
	//--------------------------getter and setter 方法------------------------
	public void setOrderService(IOrderService orderService) {
		this.orderService = orderService;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
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
	
}
