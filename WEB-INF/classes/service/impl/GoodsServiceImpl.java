package service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import dao.IGoodsDao;
import entity.Goods;
import entity.PageBean;
import service.IGoodsService;

@Transactional
public class GoodsServiceImpl implements IGoodsService {

	private IGoodsDao goodsDao;

	public void setGoodsDao(IGoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	@Override
	public Goods findGoodsById(String gid) {
		return goodsDao.findGoodsById(gid);
	}

	@Override
	public void addGoods(Goods goods) {

		goodsDao.addGoods(goods);

	}

	@Override
	public void editGoods(Goods goods) {
		goodsDao.editGoods(goods);
	}

	@Override
	public void delete(Goods goods) {
		goodsDao.deleteGoods(goods);

	}

	@Override
	public PageBean<Goods> findGoodsByPageBean(DetachedCriteria criteria, Integer currPage, Integer pageSize) {

		PageBean<Goods> pageBean = new PageBean<>();
		pageBean.setCurrPage(currPage);
		pageBean.setPageSize(pageSize);
		criteria.setProjection(Projections.rowCount());
		Integer totalCount = goodsDao.findCount(criteria);
		pageBean.setTotalCount(totalCount);
		Double totalPage = Math.ceil((double) totalCount / pageSize);
		pageBean.setTotalPage(totalPage.intValue());
		Integer begin = (currPage - 1) * pageSize;
		List<Goods> list = goodsDao.findGoodsByPageBean(criteria, begin, pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public List<Goods> findGoodsRandom() {

		List<Goods> list = new ArrayList<>();
		list = goodsDao.findAllGoods();
		Map<String, Goods> maps = new HashMap<String, Goods>();
		/*
		 * pageBean.setCurrPage(currPage); pageBean.setPageSize(pageSize);
		 */
		Goods goods = new Goods();
		/* String gid; */
		// 随机产生字符串
		// UUID uuid = UUID.randomUUID();
		// String s = UUID.randomUUID().toString();
		/* List<Customer> customers = new ArrayList<Customer>(); */
		for (int i = 0; i < 12; i++) {
			int random = (int) (Math.random() * list.size());
			goods = list.get(random);
			maps.put(goods.getGid(), goods);
		}

		List<Goods> list2 = new ArrayList<>();
		for (Goods good : maps.values()) {
			list2.add(good);
		}

		return list2;
	}

	@Override
	public List<Goods> findAll() {

		return null;
	}

}
