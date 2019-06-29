package dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import dao.TypeDao;
import entity.Dictionary;

public class TypeDaoImpl extends HibernateDaoSupport implements TypeDao {

	@SuppressWarnings("deprecation")
	@Override
	public List<Dictionary> findTypeByDicname(String dicname) {

		@SuppressWarnings("unchecked")
		List<Dictionary> list = (List<Dictionary>) this.getHibernateTemplate()
				.find("from Dictionary where dicname = ?0", dicname);

		return list;
	}
	@Override
	public List<Dictionary> findTypeByCode(String code) {
		
		@SuppressWarnings("unchecked")
		List<Dictionary> list = (List<Dictionary>) this.getHibernateTemplate()
		.find("from Dictionary where code = ?0", code);
		
		return list;
	}
	@Override
	public Dictionary findTypeByDicid(String dicid) {
		
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<Dictionary> list = (List<Dictionary>) this.getHibernateTemplate()
		.find("from Dictionary where dicid = ?0", dicid);
		if(list.size()>0)return list.get(0);
		return null;
	}
	@Override
	public void update(Dictionary dictionary) {
		
		this.getHibernateTemplate().update(dictionary);
		
	}
	@SuppressWarnings("deprecation")
	@Override
	public List<Dictionary> findAll() {

		@SuppressWarnings("unchecked")
		List<Dictionary> list = (List<Dictionary>) this.getHibernateTemplate().find("from Dictionary");

		return list;
	}
	@Override
	public void addTopCategory(Dictionary dictionary) {
		
		this.getHibernateTemplate().save(dictionary);
		
	}
	@Override
	public void addSecondCategory(Dictionary dictionary) {
		
		this.getHibernateTemplate().save(dictionary);
		
	}
	@Override
	public void deleteCategory(String dicid) {
		Session openSession = this.getSessionFactory().openSession();
		Transaction t = openSession.beginTransaction();
		Dictionary dictionary = openSession.get(Dictionary.class, dicid);
		/**
		 * 如果是一级菜单，则先删除掉拥有的二级菜单
		 */
		if(dictionary.getDicname().equals("topcategory")) {
			String hql = "delete from Dictionary where code=?0";
			SQLQuery query = openSession.createSQLQuery(hql);
			query.setParameter(0, dictionary.getCode());
		}
		openSession.delete(dictionary);
		t.commit();
		openSession.close();
	}
	@Override
	public Dictionary findCategoryByValue(String gtype) {
		List<Dictionary> list = (List<Dictionary>) this.getHibernateTemplate().find("from Dictionaty where value=?0", gtype);
		if(list.size()>0)return list.get(0);
		return null;
	}

}
