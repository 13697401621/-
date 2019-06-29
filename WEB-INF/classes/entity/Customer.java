package entity;

import java.util.HashSet;
import java.util.Set;

public class Customer {

	private String cusername;// 用户名（昵称）
	private String cpassword;// 密码
	private String cname;// 真实姓名
	private String cgender; // 性别
	private String cidnumber;// 身份证号
	private String cphone;
	private String cemail;
	private int clevel;
	private String cstate;
	private String registtime;
	private String lastlogintime;
	private String caddress;
	private Set<Order> orderSet = new HashSet<>();// 初始化为0，内存不开辟，使用时再开辟
	
	public Set<Order> getOrderSet() {
		return orderSet;
	}
	public void setOrderSet(Set<Order> orderSet) {
		this.orderSet = orderSet;
	}
	public String getCusername() {
		return cusername;
	}
	public void setCusername(String cusername) {
		this.cusername = cusername;
	}
	public String getCpassword() {
		return cpassword;
	}
	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCgender() {
		return cgender;
	}
	public void setCgender(String cgender) {
		this.cgender = cgender;
	}
	public String getCidnumber() {
		return cidnumber;
	}
	public void setCidnumber(String cidnumber) {
		this.cidnumber = cidnumber;
	}
	public String getCphone() {
		return cphone;
	}
	public void setCphone(String cphone) {
		this.cphone = cphone;
	}
	public String getCemail() {
		return cemail;
	}
	public void setCemail(String cemail) {
		this.cemail = cemail;
	}
	public int getClevel() {
		return clevel;
	}
	public void setClevel(int clevel) {
		this.clevel = clevel;
	}
	public String getCstate() {
		return cstate;
	}
	public void setCstate(String cstate) {
		this.cstate = cstate;
	}
	public String getRegisttime() {
		return registtime;
	}
	public void setRegisttime(String registtime) {
		this.registtime = registtime;
	}
	public String getLastlogintime() {
		return lastlogintime;
	}
	public void setLastlogintime(String lastlogintime) {
		this.lastlogintime = lastlogintime;
	}
	public String getCaddress() {
		return caddress;
	}
	public void setCaddress(String caddress) {
		this.caddress = caddress;
	}
	@Override
	public String toString() {
		return "Customer [cusername=" + cusername + ", cpassword=" + cpassword + ", cname=" + cname + ", cgender="
				+ cgender + ", cidnumber=" + cidnumber + ", cphone=" + cphone + ", cemail=" + cemail + ", clevel="
				+ clevel + ", cstate=" + cstate + ", registtime=" + registtime + ", lastlogintime=" + lastlogintime
				+ ", caddress=" + caddress + "]";
	}
	
}
