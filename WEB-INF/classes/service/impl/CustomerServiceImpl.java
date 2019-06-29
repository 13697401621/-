package service.impl;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dao.ICustomerDao;
import entity.Customer;
import service.ICustomerService;

@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
public class CustomerServiceImpl implements ICustomerService {
	
	private ICustomerDao customerDao;

	public void setCustomerDao(ICustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	
	/**
	 * 用户登录
	 */
	@Override
	public Customer login(Customer form) {
		return customerDao.findUserByCusername(form.getCusername());
	}

	/**
	 * 添加顾客注册())
	 */
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void regist(Customer customer) {
		customerDao.customerAdd(customer);
	}


	@Override
	public Customer findCustomerByCusername(String cusername) {
		return customerDao.findUserByCusername(cusername);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void updateCustomer(Customer customer) {
		customerDao.update(customer);
	}

}
