package dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import dao.ManagerDao;
import entity.Manager;

public class ManagerDaoImpl extends HibernateDaoSupport implements ManagerDao {

	@SuppressWarnings("deprecation")
	@Override
	public Manager loginManager(Manager manager) {

//		DetachedCriteria criteria = new DetachedCriteria("select * from t_manager where");
		HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
		@SuppressWarnings("unchecked")
		List<Manager> list = (List<Manager>) hibernateTemplate.find(
				"from Manager where musername=?0 and mpassword=?1 and mtype=?2", manager.getMusername(),
				manager.getMpassword(), manager.getMtype());
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	// 添加管理者实现类：
	@Override
	public void saveManager(Manager manager) {
		// HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
		getHibernateTemplate().save(manager);

	}

	@Override
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Manager> findAllUsers() {
		return (List<Manager>) this.getHibernateTemplate().find("from Manager");
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Manager> queryByUsername(String musername) {
		return (List<Manager>) this.getHibernateTemplate().find("from Manager u where u.musername like ?0",
				"%" + musername + "%");

	}

	@Override
	public String update(Manager manager) {

		try {
			this.getHibernateTemplate().update(manager);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());

		}
		return null;
	}

	@Override
	public void delete(String musername) {
		Manager manager = getManager(musername);
		getHibernateTemplate().delete(manager);
	}

	@Override
	public Manager getManager(String musername) {

		return this.getHibernateTemplate().load(Manager.class, musername);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public Manager queryUpUsers(String musername) {
		List<Manager> list = (List<Manager>) this.getHibernateTemplate().find("from Manager u where u.musername= ?0",musername);
		if(list.size()>0)return list.get(0);
		return null;
	}

}
