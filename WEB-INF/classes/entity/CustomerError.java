package entity;

import java.io.Serializable;

/**
 * 用于错误信息回显
 * @author Administrator
 *
 */
public class CustomerError implements Serializable {

	private String cusernameError;// 用户名（昵称）
	private String cpasswordError;// 密码
	private String cnameError;// 真实姓名
	private String cidnumberError;// 身份证号
	private String repasswordError;

	public String getRepasswordError() {
		return repasswordError;
	}

	public void setRepasswordError(String repasswordError) {
		this.repasswordError = repasswordError;
	}

	public String getCusernameError() {
		return cusernameError;
	}

	public void setCusernameError(String cusernameError) {
		this.cusernameError = cusernameError;
	}

	public String getCpasswordError() {
		return cpasswordError;
	}

	public void setCpasswordError(String cpasswordError) {
		this.cpasswordError = cpasswordError;
	}

	public String getCnameError() {
		return cnameError;
	}

	public void setCnameError(String cnameError) {
		this.cnameError = cnameError;
	}

	public String getCidnumberError() {
		return cidnumberError;
	}

	public void setCidnumberError(String cidnumberError) {
		this.cidnumberError = cidnumberError;
	}

}
