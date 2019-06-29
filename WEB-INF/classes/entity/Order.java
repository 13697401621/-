package entity;

import java.util.HashSet;
import java.util.Set;

public class Order {

	private String oid;
	private String time;//下单时间
	private String paytime;//付款时间
	private Customer customer;
	private double totalprice;// 合计
	private String address;
	private String message;//顾客留言
	private String ostate;
	private String consigneeName;// 收货人姓名
	private String consigneePhone;// 收货人电话
	
	private Set<Item> items = new HashSet<Item>(0);
	
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPaytime() {
		return paytime;
	}
	public void setPaytime(String paytime) {
		this.paytime = paytime;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
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
	public String getOstate() {
		return ostate;
	}
	public void setOstate(String ostate) {
		this.ostate = ostate;
	}
	public String getConsigneeName() {
		return consigneeName;
	}
	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}
	public String getConsigneePhone() {
		return consigneePhone;
	}
	public void setConsigneePhone(String consigneePhone) {
		this.consigneePhone = consigneePhone;
	}
	public Set<Item> getItems() {
		return items;
	}
	public void setItems(Set<Item> items) {
		this.items = items;
	}
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", time=" + time + ", paytime=" + paytime + ", customer=" + customer
				+ ", totalprice=" + totalprice + ", address=" + address + ", message=" + message + ", ostate=" + ostate
				+ ", consigneeName=" + consigneeName + ", consigneePhone=" + consigneePhone + "]";
	}
}
