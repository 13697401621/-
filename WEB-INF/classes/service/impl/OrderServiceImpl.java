package service.impl;

import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dao.IGoodsDao;
import dao.IOrderDao;
import entity.Goods;
import entity.Item;
import entity.Order;
import entity.PageBean;
import service.IGoodsService;
import service.IOrderService;

@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class OrderServiceImpl implements IOrderService {

	private IOrderDao orderDao;
	private IGoodsDao goodsDao;
	private IGoodsService goodsService;
	public void setOrderDao(IOrderDao orderDao) {
		this.orderDao = orderDao;
	}
	public void setGoodsDao(IGoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}
	public void setGoodsService(IGoodsService goodsService) {
		this.goodsService = goodsService;
	}
	
	/**
	 * 根据订单状态寻找订单某个顾客的订单
	 * 
	 * @param ostate
	 * @return
	 */
	@Override
	public List<Order> findCustomerOrderByOstate(String ostate, String cusername) {
		return orderDao.findCustomerOrderByOstate(ostate, cusername);
	}

	/**
	 * 查找购物车
	 * 
	 * @return
	 */
	@Override
	public Order findItemInCart(String cusername) {
		System.out.println("orderService"+cusername);
		return orderDao.findItemInCart(cusername);
	}

	/**
	 * 查询某顾客的所有订单
	 * 
	 * @param ostate
	 * @return
	 */
	@Override
	public List<Order> findCustomerAllOrder(String cusername) {
		return orderDao.findCustomerAllOrder(cusername);
	}

	/**
	 * 添加订单
	 * 
	 * @param order
	 */
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void orderAdd(Order order) {
		orderDao.orderAdd(order);
	}

	/**
	 * 根据id删除订单
	 */
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void deleteOrder(String oid) {
		orderDao.deleteOrder(oid);
	}

	/**
	 * 添加订单
	 * 
	 * @param order
	 */
	@Override
	public Order findOrderById(String oid) {
		return orderDao.findOrderById(oid);
	}

	/**
	 * 更新订单
	 */
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void updateOrder(Order order) {
		orderDao.update(order);
	}

	/**
	 * 根据订单条目id查找条目
	 */
	@Override
	public Item findItemById(String iid) {
		return orderDao.findItemById(iid);
	}

	/**
	 * 删除订单条目
	 */
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void deleteItem(String iid, String oid) {
		orderDao.deleteItem(iid, oid);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void backToStock(Set<Item> items) {
		for (Item item : items) {
			Goods go = item.getGoods();
			Goods goods = goodsService.findGoodsById(go.getGid());
			if(goods != null) {
				goods.setGstock(goods.getGstock() + item.getNumber());
				goodsService.editGoods(goods);
			} else {
				goodsService.addGoods(go);
			}
		}
	}
	
	//-----------------------------------管理员的方法----------------------------------------
	/**
	 * 查找所有订单（0~3的状态）
	 * @return
	 */
	@Override
	public List<Order> findAllOrder() {
		return orderDao.findAllOrder();
	}
	
	/**
	 * 根据状态查找订单
	 * @param ostate
	 * @return
	 */
	@Override
	public List<Order> findAllOrderByState(String ostate) {
		return orderDao.findAllOrderByState(ostate);
	}
	@Override
	public PageBean findOrderByPageBean(DetachedCriteria criteria, Integer currPage, Integer pageSize) {
		PageBean<Order> pageBean = new PageBean<>();
		pageBean.setCurrPage(currPage);
		pageBean.setPageSize(pageSize);
		criteria.setProjection(Projections.rowCount());
		Integer totalCount = orderDao.findCount(criteria);
		pageBean.setTotalCount(totalCount);
		Double totalPage = Math.ceil((double)totalCount/pageSize);
		pageBean.setTotalPage(totalPage.intValue());
		Integer begin = (currPage-1)*pageSize;
		List<Order> list = orderDao.findOrderByPageBean(criteria,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}
}
