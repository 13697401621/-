package myutils;

import java.util.Set;

import entity.Item;
import service.IOrderService;
import service.impl.OrderServiceImpl;

public class OrderItemUtil {

	private static IOrderService orderService;
	public void setOrderService(IOrderService orderService) {
		this.orderService = orderService;
	}

	/**
	 * 根据id查找item
	 * 
	 * @param items
	 * @param iid
	 * @return
	 */
	public static Item findItemById(Set<Item> items, String gid) {
		for (Item it : items) {
			if (it.getGoods().getGid().equals(gid)) {
				return it;
			}
		}
		return null;
	}
	
	/**
	 * 根据id删除条目
	 * @param items
	 * @param gid
	 */
	public static void deleteItem(String iid, String oid) {
		orderService.deleteItem(iid, oid);
	}
	
	/**
	 * 更新itmes里面的数据
	 * @param items
	 * @param num
	 * @param item
	 * @return
	 */
	public static Set<Item> updateItems(Set<Item> items, Item item) {
		
		boolean find = false;// false表示不存在，否则存在
		for (Item it : items) {
			if (it.getGoods().getGid().equals(item.getGoods().getGid())) {
				it.setNumber(item.getNumber() + it.getNumber());
				find = true;
				break;
			}
		}
		if (!find) {
			items.add(item);
		}
		return items;
	}
	
	/**
	 * 商品数量加1或减1
	 * @param items
	 * @param num
	 * @param gid
	 * @return
	 */
	public static Set<Item> updateItemCount(Set<Item> items, int num, String gid) {
		
		for (Item it : items) {
			if (it.getGoods().getGid().equals(gid)) {
				it.setNumber(num + it.getNumber());
				break;
			}
		}
		return items;
	}
}
