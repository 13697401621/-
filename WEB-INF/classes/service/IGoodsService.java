package service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import entity.Goods;
import entity.PageBean;

@Transactional
public interface IGoodsService<T> {


	/**
	 * 根据局商品id查找商品
	 * 
	 * @param gid
	 * @return
	 */
	public Goods findGoodsById(String gid);

	/**
	 * 保存商品
	 * 
	 * @param goods
	 */
	public void addGoods(Goods goods);

	public void editGoods(Goods goods);

	public void delete(Goods goods);

	public PageBean<T> findGoodsByPageBean(DetachedCriteria criteria,Integer currPage,Integer pageSize);

	/**
	 * 随机产生商品到前台主页
	 * 
	 * @param criteria
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	public List<Goods> findGoodsRandom();

	/**
	 * 查找全部商品不分类
	 * 
	 * @return
	 */
	public List<Goods> findAll();

}
