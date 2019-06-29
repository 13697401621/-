package action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import entity.Manager;
import service.ManagerService;
import service.TypeService;

public class ManagerAction extends ActionSupport implements ModelDriven<Manager> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String musername;
	private Manager manager = new Manager();
	private ManagerService managerService;
	private List<Manager> managers;
	private String searchText;

	public void setManagerService(ManagerService managerService) {
		this.managerService = managerService;
	}

	@Override
	public Manager getModel() {

		return manager;
	}

	public String loginManager() {
		Manager resultManager = null;
		resultManager = managerService.loginManager(manager);
		if (resultManager == null) {
			this.addActionError("账号密码或者类型选择错误！！");
			return "login";
		}
		ServletActionContext.getRequest().getSession().setAttribute("manager", resultManager);

		return "index";
	}

	/**
	 * 使用ModelDriven拦截器方法存储添加的信息 // 实现添加员工信息的方法
	 * 
	 * @return
	 */
	public String saveManager() {
		System.out.println(manager);

		managerService.saveManager(manager);

		return queryManager();
	}

	private TypeService typeService;

	public void setTypeService(TypeService typeService) {
		this.typeService = typeService;
	}

	public String addManager() {
		ServletActionContext.getRequest().getSession().setAttribute("addmanager", typeService.findAll());

		return "query";
	}

	// 查询全部员工信息方法
	public String queryManager() {

		// request.put("listmanager", typeService.findAll());
		searchText = getParam("queryText");
		managers = managerService.queryUsers(searchText);

		return "query";
	}

	// 预修改员工信息方法
	Manager manager2;

	public String prepupManager() {
		musername = getParam("musername");

		manager2 = managerService.queryUpUsers(musername);

		return "prepup";
	}

	// 修改员工信息方法
	public String updateManager() {
		managerService.updateManager(manager);

		return queryManager();
	}

	// 删除员工信息方法
	public String deleteManager() {
		try {
			String param = getParam("musername");
			managerService.deleteManager(param);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return queryManager();
	}

	protected String getParam(String key) {
		return ServletActionContext.getRequest().getParameter(key);
	}

	// 放到页面里面
	// private Map<String, Object> request;

	// ------------!--------getter和setter方法
	/**
	 * public void setRequest(Map<String, Object> arg0) { this.request = arg0; }
	 */

	public String getMusername() {
		return musername;
	}

	public void setMusername(String musername) {
		this.musername = musername;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public List<Manager> getManagers() {
		return managers;
	}

	public void setManagers(List<Manager> managers) {
		this.managers = managers;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public Manager getManager2() {
		return manager2;
	}

	public void setManager2(Manager manager2) {
		this.manager2 = manager2;
	}

}
