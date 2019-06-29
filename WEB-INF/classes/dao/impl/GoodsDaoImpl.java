package dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import dao.IGoodsDao;
import entity.Goods;

public class GoodsDaoImpl extends HibernateDaoSupport implements IGoodsDao {

	/**
	 * 根据商品分类查找
	 * 
	 */
	@Override
	public List<Goods> findGoodsByType(String gtype) {
		String sql = "from Goods where gstate=1 and gtype=?0";
		List<Goods> list = (List<Goods>) getHibernateTemplate().find(sql, gtype);
		System.out.println(list);
		return list;
	}

	/**
	 * 根据商品id查找商品
	 */
	@Override
	public Goods findGoodsById(String gid) {
		return getHibernateTemplate().get(Goods.class, gid);
	}

	@Override
	public void addGoods(Goods goods) {

		getHibernateTemplate().save(goods);

	}

	@Override
	public void editGoods(Goods goods) {
		this.getHibernateTemplate().update(goods);
	}

	@Override
	public void deleteGoods(Goods goods) {

		this.getHibernateTemplate().delete(goods);
	}

	@Override
	public List<Goods> findGoodsByCriteria(DetachedCriteria criteria) {

		List<Goods> list = (List<Goods>) this.getHibernateTemplate().findByCriteria(criteria);
		if (list.size() > 0)
			return list;
		return null;
	}

	@Override
	public Integer findCount(DetachedCriteria criteria) {

		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(criteria);
		if (list.size() > 0)
			return list.get(0).intValue();
		return null;
	}

	@Override
	public List<Goods> findGoodsByPageBean(DetachedCriteria criteria, Integer begin, Integer pageSize) {
		criteria.setProjection(null);
		List<Goods> list = (List<Goods>) this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
		return list;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Goods> findAllGoods() {
		@SuppressWarnings("unchecked")
		List<Goods> list = (List<Goods>) this.getHibernateTemplate().find("from Goods where gstate=1");

		return list;
	}

}
