package entity;

import java.util.List;

/**
 * 分类管理对象
 * @author user
 *
 */
public class Category {
	
	private Dictionary topCategory;//一级分类
	private List<Dictionary> secondCategory;//二级分类
	
	
	public Dictionary getTopCategory() {
		return topCategory;
	}
	public void setTopCategory(Dictionary topCategory) {
		this.topCategory = topCategory;
	}
	public List<Dictionary> getSecondCategory() {
		return secondCategory;
	}
	public void setSecondCategory(List<Dictionary> secondCategory) {
		this.secondCategory = secondCategory;
	}
	@Override
	public String toString() {
		return "Category [topCategory=" + topCategory + ", secondCategory=" + secondCategory + "]";
	}

}
