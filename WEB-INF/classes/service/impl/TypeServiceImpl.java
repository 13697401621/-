package service.impl;

import java.util.List;
import java.util.Random;

import org.springframework.transaction.annotation.Transactional;
import dao.TypeDao;
import entity.Dictionary;
import myutils.EshopUtil;
import service.TypeService;

@Transactional
public class TypeServiceImpl implements TypeService {

	private TypeDao typeDao;

	public void setTypeDao(TypeDao typeDao) {
		this.typeDao = typeDao;
	}

	@Override
	public List<Dictionary> findTypeByDicname(String dicname) {
		System.out.println(typeDao);
		return typeDao.findTypeByDicname(dicname);
	}
	
	@Override
	public List<Dictionary> findTypeByCode(String code) {
		System.out.println(typeDao);
		return typeDao.findTypeByCode(code);
	}
	@Override
	public Dictionary findTypeByDicid(String dicid) {
		System.out.println(typeDao);
		return typeDao.findTypeByDicid(dicid);
	}
	
	@Override
	public List<Dictionary> findAll() {

		return typeDao.findAll();
	}
	@Override
	public void update(Dictionary dictionary) {
		
		typeDao.update(dictionary);
		
	}

	/**
	 * 设置一级菜单的属性值并添加
	 */
	@Override
	public void addTopCategory(Dictionary dictionary) {
		dictionary.setDicname("topcategory");
		Random random = new Random();
		dictionary.setDicid(random.nextInt()+"");
		System.out.println(dictionary);
		typeDao.addTopCategory(dictionary);
		
	}
	/**
	 * 设置二级菜单的属性值并添加
	 */
	@Override
	public void addSecondCategory(Dictionary dictionary) {
		dictionary.setDicname("gtype");
		Random random = new Random();
		dictionary.setDicid(EshopUtil.createUUID());
		System.out.println(dictionary);
		typeDao.addTopCategory(dictionary);
		
	}

	@Override
	public void deleteCategory(String dicid) {
		typeDao.deleteCategory(dicid);
		
	}

	@Override
	public Dictionary findCategoryByValue(String gtype) {
		
		return typeDao.findCategoryByValue(gtype);
	}

}
