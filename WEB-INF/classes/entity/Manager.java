package entity;

public class Manager {

	private String musername;
	private String mpassword;
	private String mname;
	private String mtype;
	private String goodpower;// 物品管理权限
	private String orderpower;// 订单管理权限
	private String customerpower;// 顾客管理权限
	private String workerpower;// 工作人员管理权限

	public String getMusername() {
		return musername;
	}

	public void setMusername(String musername) {
		this.musername = musername;
	}

	public String getMpassword() {
		return mpassword;
	}

	public void setMpassword(String mpassword) {
		this.mpassword = mpassword;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getMtype() {
		return mtype;
	}

	public void setMtype(String mtype) {
		this.mtype = mtype;
	}

	public String getGoodpower() {
		return goodpower;
	}

	public void setGoodpower(String goodpower) {
		this.goodpower = goodpower;
	}

	public String getOrderpower() {
		return orderpower;
	}

	public void setOrderpower(String orderpower) {
		this.orderpower = orderpower;
	}

	public String getCustomerpower() {
		return customerpower;
	}

	public void setCustomerpower(String customerpower) {
		this.customerpower = customerpower;
	}

	public String getWorkerpower() {
		return workerpower;
	}

	public void setWorkerpower(String workerpower) {
		this.workerpower = workerpower;
	}

	@Override
	public String toString() {
		return "Manager [musername=" + musername + ", mpassword=" + mpassword + ", mname=" + mname + ", mtype=" + mtype
				+ ", goodpower=" + goodpower + ", orderpower=" + orderpower + ", customerpower=" + customerpower
				+ ", workerpower=" + workerpower + "]";
	}

}
