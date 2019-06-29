package dao;

import entity.Customer;

public interface ICustomerDao {

	/**
	 * 通过用户名查找用户
	 */
	public Customer findUserByCusername(String cusername);
	
	/**
	 * 添加顾客
	 * @param customer
	 */
	public void customerAdd(Customer customer);

	public void update(Customer customer);
}
