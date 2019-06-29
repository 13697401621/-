package entity;

public class Goods {

	private String gid;
	private String gname;
	private int gstock;
	private double gprice;
	private String gtopcategory;
	private String gtype;
	private String gdescription;//商品描述
	private String gpicturepath;//图片路径
	private String gmanufacturer;//生产厂家
	private String gmodel;//商品型号
	private String gproducedate;//生产日期
	private String gguaranteedate;//保质日期
	private String gstate;//商品状态
	
	
	public String getGtopcategory() {
		return gtopcategory;
	}
	public void setGtopcategory(String gtopcategory) {
		this.gtopcategory = gtopcategory;
	}
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public int getGstock() {
		return gstock;
	}
	public void setGstock(int gstock) {
		this.gstock = gstock;
	}
	public double getGprice() {
		return gprice;
	}
	public void setGprice(double gprice) {
		this.gprice = gprice;
	}
	public String getGtype() {
		return gtype;
	}
	public void setGtype(String gtype) {
		this.gtype = gtype;
	}
	public String getGdescription() {
		return gdescription;
	}
	public void setGdescription(String gdescription) {
		this.gdescription = gdescription;
	}
	public String getGpicturepath() {
		return gpicturepath;
	}
	public void setGpicturepath(String gpicturepath) {
		this.gpicturepath = gpicturepath;
	}
	public String getGmanufacturer() {
		return gmanufacturer;
	}
	public void setGmanufacturer(String gmanufacturer) {
		this.gmanufacturer = gmanufacturer;
	}
	
	public String getGmodel() {
		return gmodel;
	}
	public void setGmodel(String gmodel) {
		this.gmodel = gmodel;
	}
	public String getGproducedate() {
		return gproducedate;
	}
	public void setGproducedate(String gproducedate) {
		this.gproducedate = gproducedate;
	}
	public String getGguaranteedate() {
		return gguaranteedate;
	}
	public void setGguaranteedate(String gguaranteedate) {
		this.gguaranteedate = gguaranteedate;
	}
	public String getGstate() {
		return gstate;
	}
	public void setGstate(String gstate) {
		this.gstate = gstate;
	}
	@Override
	public String toString() {
		return "Goods [gid=" + gid + ", gname=" + gname + ", gstock=" + gstock + ", gprice=" + gprice + ", gtopcategory="
				+ gtopcategory + ", gtype=" + gtype + ", gdescription=" + gdescription + ", gpicturepath=" + gpicturepath
				+ ", gmanufacturer=" + gmanufacturer + ", gmodel=" + gmodel + ", gproducedate=" + gproducedate
				+ ", gguaranteedate=" + gguaranteedate + ", gstate=" + gstate + "]";
	}
	
	
}
