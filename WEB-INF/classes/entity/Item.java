package entity;

import java.math.BigDecimal;

public class Item {

	private String iid;
	private Order order;
	private Goods goods;
	private int number;// 数量
	private double price;// 小计
	private double uprice;// 商品单价
	private double priceInCart;// 在购物车中显示的小计
	
	//计算小计
	public double getPriceInCart() {
		BigDecimal d1 = new BigDecimal(goods.getGprice()+"");
		BigDecimal d2 = new BigDecimal(number + "");
		return d1.multiply(d2).doubleValue();
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}	
	public double getUprice() {
		return uprice;
	}
	public void setUprice(double uprice) {
		this.uprice = uprice;
	}

	@Override
	public String toString() {
		return "Item [iid=" + iid + ", order=" + order + ", goods=" + goods + ", number=" + number + ", price=" + price
				+ "]";
	}
	
}
