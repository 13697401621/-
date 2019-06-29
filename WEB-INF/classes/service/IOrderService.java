package service;

import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;

import entity.Item;
import entity.Order;
import entity.PageBean;

public interface IOrderService {

	/**
	 * 根据订单状态寻找订单某个顾客的订单
	 * 
	 * @param ostate
	 * @return
	 */
	public List<Order> findCustomerOrderByOstate(String ostate, String cusername);

	/**
	 * 查找购物车
	 * 
	 * @return
	 */
	public Order findItemInCart(String cusername);

	/**
	 * 查询某顾客的所有订单
	 * 
	 * @param ostate
	 * @return
	 */
	public List<Order> findCustomerAllOrder(String cusername);
	
	/**
	 * 添加订单
	 * 
	 * @param order
	 */
	public void orderAdd(Order order);

	/**
	 * 删除订单
	 * 
	 * @param oid
	 */
	public void deleteOrder(String oid);
	
	/**
	 * 订单取消，将数量还原到商品库存
	 * 
	 * @param item
	 */
	public void backToStock(Set<Item> items);

	/**
	 * 根据查找订单
	 * 
	 * @param oid
	 * @return
	 */
	public Order findOrderById(String oid);
	
	/**
	 * 更新订单
	 * @param order
	 */
	public void updateOrder(Order order);
	
	/**
	 * 根据id删除item记录
	 * @param iid
	 * @param oid
	 */
	public void deleteItem(String iid, String oid);
	
	/**
	 * 根据id查找item记录
	 * @param iid
	 */
	public Item findItemById(String iid);
	
	//-----------------------------------管理员的方法----------------------------------------
	/**
	 * 查找所有订单（0~3的状态）
	 * @return
	 */
	public List<Order> findAllOrder();
	
	/**
	 * 根据状态查找订单
	 * @param ostate
	 * @return
	 */
	public List<Order> findAllOrderByState(String ostate);

	public PageBean findOrderByPageBean(DetachedCriteria criteria, Integer currPage, Integer pageSize);
}
