package dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import entity.Goods;

public interface IGoodsDao {

	/**
	 * 根据商品分类查找
	 * 
	 * @param gtype
	 */
	public List<Goods> findGoodsByType(String gtype);

	/**
	 * 根据id查找商品
	 * 
	 * @param gid
	 * @return
	 */
	public Goods findGoodsById(String gid);

	public void addGoods(Goods goods);

	public void editGoods(Goods goods);

	public void deleteGoods(Goods goods);

	/**
	 * 根据条件查询
	 * 
	 * @param criteria
	 * @return
	 */
	public List<Goods> findGoodsByCriteria(DetachedCriteria criteria);

	/**
	 * 分页操作，查出总的记录数
	 * 
	 * @param criteria
	 * @return
	 */
	public Integer findCount(DetachedCriteria criteria);

	/**
	 * 分页查找商品
	 * 
	 * @param criteria
	 * @param begin
	 * @param pageSize
	 * @return
	 */
	public List<Goods> findGoodsByPageBean(DetachedCriteria criteria, Integer begin, Integer pageSize);

	/**
	 * 随机查找商品到前台页面
	 * 
	 * @param criteria
	 * @param begin
	 * @param pageSize
	 * @return
	 */

	/**
	 * 查找全部商品不分页的。。
	 * 
	 * @return
	 */
	public List<Goods> findAllGoods();

}
