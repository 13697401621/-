package dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import dao.ICustomerDao;
import entity.Customer;

public class CustomerDaoImpl extends HibernateDaoSupport implements ICustomerDao {

	/**
	 * 通过用户名查找用户
	 */
	@Override
	public Customer findUserByCusername(String cusername) {
		String sql = "from Customer where cusername=?0";
		List<Customer> customers = (List<Customer>) getHibernateTemplate().find(sql, cusername);
		if (customers.size() > 0) {
			return customers.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 添加顾客
	 * 
	 * @param customer
	 */
	public void customerAdd(Customer customer) {
		System.out.println(customer);
		getHibernateTemplate().save(customer);
	}

	@Override
	public void update(Customer customer) {
		getHibernateTemplate().update(customer);
	}
}
