package entity;

public class Dictionary {

	private String dicid;
	private String dicname;
	private String code;
	private String value;
	
	public String getDicid() {
		return dicid;
	}
	public void setDicid(String dicid) {
		this.dicid = dicid;
	}
	public String getDicname() {
		return dicname;
	}
	public void setDicname(String dicname) {
		this.dicname = dicname;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "Dictionary [dicid=" + dicid + ", dicname=" + dicname + ", code=" + code + ", value=" + value + "]";
	}
	
	
}
