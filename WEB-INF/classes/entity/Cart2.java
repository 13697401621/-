package entity;

import java.math.BigDecimal;

import myutils.OrderItemUtil;

/**
 * 购物车
 * 
 * @author JiMI
 *
 */
public class Cart2 {

	private Customer customer;
	private Order order;
	
	/**
	 * 计算总金额
	 * 
	 * @return
	 */
	public double getTotal() {
		BigDecimal total = new BigDecimal("0");
		for (Item item : order.getItems()) {
			BigDecimal subtotal = new BigDecimal("" + item.getPriceInCart());
			total = total.add(subtotal);
		}
		return total.doubleValue();
	}
	
	/**
	 * 查找定的商品条目
	 * @param iid
	 * @return
	 */
	public Item findItem(String gid) {
		for(Item item : order.getItems()) {
			if(item.getGoods().getGid().equals(gid)) {
				return item;
			}
		}
		return null;
	}

	/**
	 * 删除指定的商品
	 * 
	 * @param iid
	 */
	public void removeItem(String gid, String oid) {
		Item item = OrderItemUtil.findItemById(order.getItems(), gid);
		order.getItems().remove(item);
		// 从数据库中删除
		OrderItemUtil.deleteItem(item.getIid(), oid);
	}

	/**
	 * 添加商品
	 * 
	 * @param item
	 */
	public void addItem(Item item) {
		boolean find = false;
		for (Item it : order.getItems()) {
			if (it.getGoods().getGid().equals(item.getGoods().getGid())) {
				it.setNumber(item.getNumber() + it.getNumber());
				find = true;
				break;
			}
		}
		if (!find) {
			order.getItems().add(item);
		}
		//order.setItems(OrderItemUtil.updateItems(order.getItems(), item));

	}

	/**
	 * 已存在的商品数量改变1（加1或减1）
	 * 
	 * @param gid
	 */
	public void itemCountChangeOne(String gid, int num) {
		//order.setItems(OrderItemUtil.updateItemCount(order.getItems(), num, gid));
		for (Item it : order.getItems()) {
			if (it.getGoods().getGid().equals(gid)) {
				it.setNumber(num + it.getNumber());
				it.setPrice(it.getPriceInCart());
				break;
			}
		}
	}
	
	/**
	 * 使车中的商品的库存与数据库中的商品库存一致
	 * 
	 * @param gid
	 */
	public void updateStockInCart(String gid, int num) {
		//order.setItems(OrderItemUtil.updateItemCount(order.getItems(), num, gid));
		for (Item it : order.getItems()) {
			if (it.getGoods().getGid().equals(gid)) {
				it.getGoods().setGstock(num);
				break;
			}
		}
	}
	
	/**
	 * 删除所有商品
	 */
	public void removeAll() {
		order.getItems().removeAll(order.getItems());
	}

	//--------------------getter and setter方法-------------------------------------
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
