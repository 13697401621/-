package dao;

import java.util.List;

import entity.Dictionary;

public interface TypeDao {

	public List<Dictionary> findTypeByCode(String dicname);

	public List<Dictionary> findAll();

	public List<Dictionary> findTypeByDicname(String dicname);

	public Dictionary findTypeByDicid(String dicid);

	public void addTopCategory(Dictionary dictionary);
	public void addSecondCategory(Dictionary dictionary);

	public void deleteCategory(String dicid);

	public void update(Dictionary dictionary);

	public Dictionary findCategoryByValue(String gtype);

}
