package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import entity.Category;
import entity.Dictionary;
import entity.Goods;
import entity.PageBean;
import service.IGoodsService;
import service.TypeService;

public class GoodsAction extends ActionSupport implements ModelDriven<Goods> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Goods goods = new Goods();
	private List<Goods> goodsList;
	private IGoodsService goodsService;
	private TypeService typeService;
	private Integer currPage = 1;
	private Integer pageSize = 12;
	private PageBean<Goods> pageBean;
	private PageBean<Goods> pageBean2;

	@Override
	public Goods getModel() {
		return goods;
	}

	@SuppressWarnings({ "unused", "unchecked" })
	// 前台主页查找显示：
	public String findAllGoodsHome() {
		goodsList = goodsService.findGoodsRandom();

		return "findAllGoodsHome";
	}

	/**
	 * 查看商品详情
	 * 
	 * @return
	 */
	public String desc() {
		goods = goodsService.findGoodsById(goods.getGid());
		ActionContext.getContext().getValueStack().push(goods);
		return "desc";
	}

	/**
	 * 添加商品
	 * 
	 * @return
	 */
	private File image;// 文件
	private String imageFileName;// 文件名
	private String imageFileContentType;// 文件扩展名

	public String addGoods() {
		if (image != null) {
			try {
				String gtopcategory = goods.getGtopcategory();
				Dictionary dictionary = typeService.findTypeByDicid(gtopcategory);
				gtopcategory = dictionary.getValue();// 找到一级菜单的名字
				String gtype = goods.getGtype();// 找到二级菜单的名字
				// 根据菜单寻找存放图片的路径
				String picturepath = "myphoto" + "/" + gtopcategory + "/" + gtype + "/";
				// 设置图片路径
				goods.setGpicturepath(picturepath + imageFileName);
				String filepath = ServletActionContext.getServletContext().getRealPath("/") + picturepath;
				File file = new File(filepath);
				// 建立图片路径
				if (!file.exists()) {
					file.mkdirs();
				}
				System.out.println(filepath + imageFileName);
				FileInputStream fis = new FileInputStream(image);
				FileOutputStream fos = new FileOutputStream(filepath + imageFileName);
				IOUtils.copy(fis, fos);
				// 保存图片
				fos.flush();
				fis.close();
				fos.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		// 保存商品到数据库
		goodsService.addGoods(goods);
		return SUCCESS;

	}

	/**
	 * 修改商品前置工作
	 * 
	 * @return
	 */
	public String editGoodsPre() {
		goods = goodsService.findGoodsById(goods.getGid());
		System.out.println(goods);
		List<Category> categoryList = new ArrayList<>();
		List<Dictionary> list = typeService.findTypeByDicname("topcategory");
		// 遍历找到的一级分类
		for (Dictionary dictionary : list) {
			Category category = new Category();
			// 把一级分类赋给category的topCategory
			category.setTopCategory(dictionary);
			// 再根据一级分类，找到对应的二级分类，赋给category的二级分类list
			List<Dictionary> list2 = typeService.findTypeByCode(dictionary.getDicid());
			category.setSecondCategory(list2);
			categoryList.add(category);
		}

		ServletActionContext.getRequest().setAttribute("categoryList", categoryList);
		return "editGoodsPre";
	}

	public String editGoods() throws Exception {
		System.out.println(goods);
		if (image != null) {
			// 找到一级菜单的名字
			String gtopcategory = typeService.findTypeByDicid(goods.getGtopcategory()).getValue();
			String gtype = goods.getGtype();// 找到二级菜单的名字
			// 根据菜单寻找存放图片的路径
			String picturepath = "myphoto" + "/" + gtopcategory + "/" + gtype + "/";
			// 设置图片路径
			goods.setGpicturepath(picturepath + imageFileName);
			String filepath = ServletActionContext.getServletContext().getRealPath("/") + picturepath;
			File file = new File(filepath);
			// 建立图片路径
			if (!file.exists()) {
				file.mkdirs();
			}
			System.out.println(filepath + imageFileName);
			FileInputStream fis = new FileInputStream(image);
			FileOutputStream fos = new FileOutputStream(filepath + imageFileName);
			IOUtils.copy(fis, fos);
			// 保存图片
			fos.flush();
			fis.close();
			fos.close();
		}
		goodsService.editGoods(goods);

		return "editGoods";
	}

	public String deleteGoods() {
		goodsService.delete(goods);

		return "deleteGoods";
	}

	/**
	 * 分页查找
	 * 
	 * @param currPage,pageSize
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public String findGoodsByPageBean() {
		if (currPage == null) {
			currPage = 1;
		}
		if (pageSize == null) {
			pageSize = 12;
		}
		DetachedCriteria criteria = DetachedCriteria.forClass(Goods.class);
		if(goods.getGtype()!=null) {
			criteria.add(Restrictions.eq("gtype", goods.getGtype()));
		}
		if(goods.getGname()!=null)
			criteria.add(Restrictions.like("gname", "%"+goods.getGname()+"%"));
		if(goods.getGmanufacturer()!=null)
			criteria.add(Restrictions.like("gmanufacturer", "%"+goods.getGmanufacturer()+"%"));
		if(goods.getGmodel()!=null)
			criteria.add(Restrictions.like("gmodel", "%"+goods.getGmodel()+"%"));
		if(goods.getGdescription()!=null)
			criteria.add(Restrictions.like("gdescription","%"+ goods.getGdescription()+"%"));
		pageBean = goodsService.findGoodsByPageBean(criteria,currPage,pageSize);
		System.out.println(pageBean);
		return "findGoodsByPageBean";
	}

	@SuppressWarnings("unchecked")
	public String bkfindGoodsByPageBean() {
		if (currPage == null) {
			currPage = 1;
		}
		if (pageSize == null) {
			pageSize = 12;
		}
		DetachedCriteria criteria = DetachedCriteria.forClass(Goods.class);
		if(goods.getGtype()!=null) {
			criteria.add(Restrictions.eq("gtype", goods.getGtype()));
		}
		if(goods.getGname()!=null)
			criteria.add(Restrictions.like("gname", "%"+goods.getGname()+"%"));
		if(goods.getGmanufacturer()!=null)
			criteria.add(Restrictions.like("gmanufacturer", "%"+goods.getGmanufacturer()+"%"));
		if(goods.getGmodel()!=null)
			criteria.add(Restrictions.like("gmodel", "%"+goods.getGmodel()+"%"));
		if(goods.getGdescription()!=null)
			criteria.add(Restrictions.like("gdescription","%"+ goods.getGdescription()+"%"));
		pageBean = goodsService.findGoodsByPageBean(criteria,currPage,pageSize);
		System.out.println(pageBean);
		return "bkfindGoodsByPageBean";
	}

	// -------------------------getter and setter
	// 方法------------------------------------------
	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}

	public void setGoodsService(IGoodsService goodsService) {
		this.goodsService = goodsService;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getImageFileContentType() {
		return imageFileContentType;
	}

	public void setImageFileContentType(String imageFileContentType) {
		this.imageFileContentType = imageFileContentType;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Goods getGoods() {
		return goods;
	}

	public TypeService getTypeService() {
		return typeService;
	}

	public void setTypeService(TypeService typeService) {
		this.typeService = typeService;
	}

	public Integer getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@SuppressWarnings("rawtypes")
	public PageBean getPageBean() {
		return pageBean;
	}

	@SuppressWarnings("unchecked")
	public void setPageBean(@SuppressWarnings("rawtypes") PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public PageBean<Goods> getPageBean2() {
		return pageBean2;
	}

	public void setPageBean2(PageBean<Goods> pageBean2) {
		this.pageBean2 = pageBean2;
	}

}
