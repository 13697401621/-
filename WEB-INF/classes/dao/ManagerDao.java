package dao;

import java.util.List;

import entity.Manager;

public interface ManagerDao {

	/**
	 * 管理员登录接口
	 * 
	 * @param manager
	 * @return
	 */
	public Manager loginManager(Manager manager);

	/**
	 * 添加管理者，分为管理员、客服人员和采购员三种
	 * 
	 * @param manager
	 */
	public void saveManager(Manager manager);

	/**
	 * 通过musername来查找员工信息
	 * 
	 * @param musername
	 * @return
	 */
	public List<Manager> queryByUsername(String musername);

	/**
	 * 查询全部员工信息
	 * 
	 * @return
	 */
	public List<Manager> findAllUsers();

	/**
	 * 修改员工信息接口
	 * 
	 * @param manager
	 */
	public String update(Manager manager);

	/**
	 * 删除员工接口
	 * 
	 * @param musername
	 */
	public void delete(String musername);

	public Manager getManager(String musername);

	/**
	 * 为修改的查询
	 * 
	 * @param musername
	 * @return
	 */
	public Manager queryUpUsers(String musername);

}
