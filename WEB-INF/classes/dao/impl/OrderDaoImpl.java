package dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import dao.IOrderDao;
import entity.Item;
import entity.Order;

/**
 * 订单实现类
 * @author JiMI
 *
 */
public class OrderDaoImpl extends HibernateDaoSupport implements IOrderDao {

	/**
	 * 根据id删除订单
	 */
	@Override
	public void deleteOrder(String oid) {
		getHibernateTemplate().delete(findOrderById(oid));
	}

	/**
	 * 根据id查找订单
	 */
	@Override
	public Order findOrderById(String oid) {
		return getHibernateTemplate().get(Order.class, oid);
	}

	/**
	 * 根据订单状态寻找订单某个顾客的订单
	 * 
	 * @param ostate
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@Override
	public List<Order> findCustomerOrderByOstate(String ostate, String cusername) {
		String sql = "from Order where ostate=?0 and cusername=?1";
		@SuppressWarnings("unchecked")
		List<Order> orders = (List<Order>) getHibernateTemplate().find(sql, ostate, cusername);
		if (orders.size() > 0) {
			return orders;
		}
		return null;
	}

	/**
	 * 查找购物车
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Order findItemInCart(String cusername) {
		System.out.println("orderDao" +cusername);
		String sql = "from Order where ostate='5' and cusername=?0";
		@SuppressWarnings("deprecation")
		List<Order> orders = (List<Order>) getHibernateTemplate().find(sql, cusername);
		//System.out.println(orders.get(0));
		if (orders.size() > 0) {
			return orders.get(0);
		}
		
		return null;
	}

	/**
	 * 查询某顾客的所有订单
	 * 
	 * @param ostate
	 * @return
	 */
	@Override
	public List<Order> findCustomerAllOrder(String cusername) {
		// 购物车中的信息不能是一个订单
		String sql = "from Order where cusername=?0 and ostate in ('0','1','2','3','4') ORDER BY time DESC";
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<Order> orders = (List<Order>) getHibernateTemplate().find(sql, cusername);
		if (orders.size() > 0) {
			return orders;
		}
		return null;
	}

	/**
	 * 添加订单
	 * 
	 * @param order
	 */
	@Override
	public void orderAdd(Order order) {
		getHibernateTemplate().save(order);
	}

	/**
	 * 更新订单
	 */
	@Override
	public void update(Order order) {
		getHibernateTemplate().saveOrUpdate(order);
	}

	/**
	 * 根据id查找条目
	 */
	@Override
	public Item findItemById(String iid) {
		return getHibernateTemplate().get(Item.class, iid);
	}
	
	public void updateItem(Item item) {
		getHibernateTemplate().update(item);
	}

	@Override
	public void deleteItem(String iid, String oid) {
		Order order = findOrderById(oid);// 找出该条目所在的订单
		Item item = findItemById(iid);// 找出该条目
		order.getItems().remove(item);// 在所关联的一方的set中移走当前要删除的对象，解除关联
		item.setOrder(null);// 在多的以解除关联
		getHibernateTemplate().delete(findItemById(iid));// 删除
	}

	//-----------------------------------------------------------------------------------------
	/**
	 * 查找所有订单（0~3的状态）
	 * @return
	 */
	@Override
	public List<Order> findAllOrder() {
		String sql = "from Order where ostate in ('0','1','2','3','4') ORDER BY TIME DESC";
		return (List<Order>) getHibernateTemplate().find(sql);
	}

	/**
	 * 根据状态查找订单
	 * @param ostate
	 * @return
	 */
	@Override
	public List<Order> findAllOrderByState(String ostate) {
		String sql = "from Order where ostate=?0 ORDER BY TIME DESC";
		return (List<Order>) getHibernateTemplate().find(sql, ostate);
	}

	@Override
	public Integer findCount(DetachedCriteria criteria) {
		
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list.size()>0)return list.get(0).intValue();
		return null;
	}

	@Override
	public List<Order> findOrderByPageBean(DetachedCriteria criteria, Integer begin, Integer pageSize) {
		criteria.setProjection(null);
		List<Order> list = (List<Order>) this.getHibernateTemplate().findByCriteria(criteria,begin,pageSize);
		if(list.size()>0)return list;
		return null;
	}

}
