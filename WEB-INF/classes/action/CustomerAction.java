package action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import entity.Cart2;
import entity.Customer;
import entity.CustomerError;
import myutils.CartUtil;
import service.ICustomerService;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

	private Customer customer = new Customer();
	private CustomerError custError = null;
	private ICustomerService customerService;
	
	@Override
	public Customer getModel() {
		return customer;
	}
	
	/**
	 * 退出系统
	 * @return
	 */
	public String quit() {
		// 获取session并销毁
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
	
	/**
	 * 跳转到登录页面
	 * @return
	 */
	public String loginUI() {
		// 销毁浏览器保存的session
		ServletActionContext.getRequest().getSession().invalidate();
		return "loginUI";
	}
	
	/**
	 * 顾客登录功能
	 * @return
	 */
	public String login() {
		Customer cu = customerService.login(customer);
		if(cu == null) {
			this.addActionError("无该用户！！");
			return "loginError";
		} 
		if(!cu.getCpassword().equals(customer.getCpassword())) {
			this.addActionError("账号密码或者类型选择错误！！");
			return "loginError";
		}
		ServletActionContext.getRequest().getSession().setAttribute("customer", cu);
		Cart2 cart = CartUtil.orderToCart2(cu.getCusername());
		if(cart == null) {
			cart = new Cart2();
			cart.setOrder(null);
		}
		cart.setCustomer(cu);
		Map<String,String> msgMap = (Map<String, String>) ServletActionContext.getServletContext().getAttribute("msgMap");
		if(msgMap==null) {
			msgMap = new HashMap<>();
		}
		msgMap.put(cu.getCusername(), "");
		ServletActionContext.getServletContext().setAttribute("msgMap", msgMap);
		ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		return "login";
	}
	
	/**
	 * 用户注册
	 * @return
	 */
	public String regist() {
		System.out.println(customer);
		if( !checkForm() ) {
			ActionContext.getContext().getValueStack().push(customer);
			return "registError";
		}
		customer.setCstate(null);
		ServletActionContext.getRequest().getSession().setAttribute("customer", customer);
		customerService.regist(customer);
		return "regist";
	}
	
	/**
	 * 获取修改页面
	 * 
	 * @return
	 */
	public String editUI() {
		customer = (Customer) ServletActionContext.getRequest().getSession().getAttribute("customer");
		ActionContext.getContext().getValueStack().push(customer);
		return "editUI";
	}

	/**
	 * 修改顾客信息
	 * @return
	 */
	public String edit() {
		// 获取session里面的额customer对象
		Customer cu =  (Customer) ServletActionContext.getRequest().getSession().getAttribute("customer");
		/*
		 *获取修改的信息 
		 */
		cu.setCgender(customer.getCgender());
		cu.setCemail(customer.getCemail());
		cu.setCphone(customer.getCphone());
		cu.setCaddress(customer.getCaddress());
		// 数据库中修改
		customerService.updateCustomer(cu);
		// 将修改改后的数据重新放入到session中
		ServletActionContext.getRequest().getSession().setAttribute("customer", cu);
		return "edit";
	}
	
	/**
	 * 修改顾客密码
	 * @return
	 */
	public String editPwd() {
		Customer cu =  (Customer) ServletActionContext.getRequest().getSession().getAttribute("customer");
		String oldPwd = customer.getCstate();// 借用Customer中的属性
		String newPwd = customer.getCpassword();
		String rePwd = customer.getCphone();// 借用Customer中的属性
		boolean flag = true;
		custError = new CustomerError();
		
		System.out.println("customer="+customer);
		System.out.println("session="+cu);
		/*
		 * 表单校验 
		 */
		if(!oldPwd.equals(cu.getCpassword())) {
			custError.setCpasswordError("原密码错误");
			flag = false;
			return "editPwdError";
		}
		if(newPwd == null || newPwd.trim().isEmpty()) {
			custError.setCpasswordError("密码长度应为6~16");
			flag = false;
		} else if(newPwd.length()<6 || newPwd.length()>16) {
			custError.setCpasswordError("密码长度应为6~16");
			flag = false;
		}
		if(!rePwd.equals(newPwd)) {
			custError.setCpasswordError("密码输入不一致");
			flag = false;
		}
		if(!flag) {
			return "editPwdError";
		}
		
		// 修改要求无误，同步到数据库
		cu.setCpassword(newPwd);
		customerService.updateCustomer(cu);
		ServletActionContext.getRequest().getSession().setAttribute("customer", cu);
		return "editPwd";
	}
	
	

	//-------------------------getter and setter 方法------------------------------------------
	public void setCustomerService(ICustomerService customerService) {
		this.customerService = customerService;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CustomerError getCustError() {
		return custError;
	}

	public void setCustError(CustomerError custError) {
		this.custError = custError;
	}
	
	//-------------------------其他方法------------------------------------------
	
	/**
	 * 表单检验，
	 * @param customer2
	 * @return 返回false表示有错误，返回true表示没有错误
	 */
	private boolean checkForm() {
		
		boolean flag = true;
		String cidnumber = customer.getCidnumber();
		String cname = customer.getCname();
		String cusername = customer.getCusername();
		String cpassword = customer.getCpassword();
		String cstate = customer.getCstate();
		custError = new CustomerError();
		
		if(cidnumber == null || cidnumber.trim().isEmpty()) {
			custError.setCidnumberError("身份证号不能为空");
			flag = false;
		}
		
		if(cname == null || cname.trim().isEmpty()) {
			custError.setCnameError("真实姓名不能为空");
			flag = false;
		} else if(cname.length() <2 || cname.length() >15){
			custError.setCnameError("真事姓名长度应为2~15");
			flag = false;
		}
		
		if(cusername == null || cusername.trim().isEmpty()) {
			custError.setCusernameError("用户名不能为空");
			flag = false;
		} else if(cusername.length() <2 || cusername.length() >15){
			custError.setCusernameError("用户名长度应为2~15");
			flag = false;
		} else if(customerService.findCustomerByCusername(cusername) != null) {
			custError.setCusernameError("用户名已存在");
			flag = false;
		}
		
		if(cpassword == null || cpassword.trim().isEmpty()) {
			custError.setCpasswordError("密码不能为空");
			flag = false;
		} else if(cpassword.length() <6 || cpassword.length() >16){
			custError.setCpasswordError("密码长度应为6~16");
			flag = false;
		}
		
		if(!cstate.equals(cpassword)) {
			custError.setRepasswordError("密码输入不一致");
			flag = false;
		}
		return flag;
	}
}
