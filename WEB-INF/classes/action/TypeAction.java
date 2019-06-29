package action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import entity.Category;
import entity.Dictionary;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import service.TypeService;

/**
 * 
 * @author user 查询分类的action
 */
public class TypeAction extends ActionSupport implements ModelDriven<Dictionary>{


	private Dictionary dictionary = new Dictionary();
	
	private TypeService typeService;
	private List<Category> categoryList = new ArrayList<>();
	
	public String findTypeByDicname() throws IOException {
		System.out.println(dictionary.getDicname());
		List<Dictionary> list = typeService.findTypeByDicname(dictionary.getDicname());
		/**
		 * 转成json数据
		 */
//		JsonConfig jc = new JsonConfig();
//		jc.setExcludes(new String[] { "dicid", "dicname" });
		JSONArray jsonArray = JSONArray.fromObject(list);
		System.out.println(jsonArray.toString());
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());

		return NONE;
	}
	public String findTypeByCode() throws IOException {
		System.out.println(dictionary.getCode());
		List<Dictionary> list = typeService.findTypeByCode(dictionary.getCode());
		/**
		 * 转成json数据
		 */
//		JsonConfig jc = new JsonConfig();
//		jc.setExcludes(new String[] { "dicid", "dicname" });
		JSONArray jsonArray = JSONArray.fromObject(list);
		System.out.println(jsonArray.toString());
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		
		return NONE;
	}
	/**
	 * 找到所有的分类
	 * @return
	 */
	public String findAllCategory() {
		
		dictionary.setDicname("topcategory");
		List<Dictionary> list = typeService.findTypeByDicname(dictionary.getDicname());
		//遍历找到的一级分类
		for (Dictionary dictionary : list) {
			Category category = new Category();
			//把一级分类赋给category的topCategory
			category.setTopCategory(dictionary);
			//再根据一级分类，找到对应的二级分类，赋给category的二级分类list
			List<Dictionary> list2 = typeService.findTypeByCode(dictionary.getDicid());
			category.setSecondCategory(list2);
			categoryList.add(category);
		}
		System.out.println(categoryList);
		return "findAllCategory";
	}
	/**
	 * 添加一级菜单
	 * @return
	 */
	public String addTopCategory() {
		typeService.addTopCategory(dictionary);
		return "addTopCategory";
	}
	/**
	 * 添加二级菜单
	 * @return
	 */
	public String addSecondCategory() {
		typeService.addSecondCategory(dictionary);
		return "addSecondCategory";
	}
	/**
	 * 删除一级分类或者二级分类
	 * @return
	 */
	public String deleteCategory() {
		String dicid = ServletActionContext.getRequest().getParameter("dicid");
		typeService.deleteCategory(dicid);
		return "deleteCategory";
	}
	public String editCategoryPre() {
		String dicid = ServletActionContext.getRequest().getParameter("dicid");
		dictionary = typeService.findTypeByDicid(dicid);
		ServletActionContext.getRequest().setAttribute("dictionary", dictionary);
		return "editCategoryPre";
	}
	public String editCategory() {
		
		typeService.update(dictionary);
		return "editCategory";
	}
	public String findAll() throws IOException {
		List<Dictionary> list = typeService.findAll();
		/**
		 * 转成json数据
		 */
		JsonConfig jc = new JsonConfig();
		jc.setExcludes(new String[] { "dicid", "dicname" });
		JSONArray jsonArray = JSONArray.fromObject(list, jc);
		System.out.println(jsonArray.toString());
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());

		return NONE;
	}
//-------------------------------------------------------------------------------------------

	

	public List<Category> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}
	public void setTypeService(TypeService typeService) {
		this.typeService = typeService;
	}
	public Dictionary getDictionary() {
		return dictionary;
	}
	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}
	@Override
	public Dictionary getModel() {
		return dictionary;
	}

}
