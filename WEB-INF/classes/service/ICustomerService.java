package service;

import entity.Customer;
import myException.CustomerException;

public interface ICustomerService {

	/**
	 * 用户登录功能
	 * @param customer
	 * @throws CustomerException 
	 */
	public Customer login(Customer customer);
	
	/**
	 * 顾客注册
	 * @param customer
	 */
	public void regist(Customer customer);
	
	public Customer findCustomerByCusername(String cusername);

	public void updateCustomer(Customer customer);
}
