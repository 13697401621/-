package service;

import java.util.List;

import entity.Manager;

public interface ManagerService {

	public Manager loginManager(Manager manager);

	public void saveManager(Manager manager); // 添加管理者

	/**
	 * 查询全部员工信息
	 * 
	 * @return
	 */
	public List<Manager> findAllUsers();

	/**
	 * 获取员工列表
	 * 
	 * @param musername
	 * @return
	 */
	public List<Manager> queryUsers(String musername);

	/**
	 * 为修改的查询
	 * 
	 * @param musername
	 * @return
	 */
	public Manager queryUpUsers(String musername);

	/**
	 * 修改员工信息
	 * 
	 * @param manager
	 */
	public boolean updateManager(Manager manager);

	/**
	 * 删除员工信息
	 * 
	 * @param manager
	 */
	public void deleteManager(String musername);

	/**
	 * 获取指定musername的员工
	 * 
	 * @param musername
	 * @return
	 */
	public Manager getManager(String musername);

}
