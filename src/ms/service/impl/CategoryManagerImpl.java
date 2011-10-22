package ms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import ms.dao.CategoryDao;
import ms.model.Category;
import ms.service.CategoryManager;

import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;

@Service("categoryManager")
@RemotingDestination(channels={"my-amf"})
public class CategoryManagerImpl implements CategoryManager {

	private CategoryDao categoryDao;
	
	
	public CategoryDao getCategoryDao() {
		return categoryDao;
	}
	
	@Resource
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	@RemotingInclude
	public String add(Category category) throws Exception {
		List<Category> categorys = null;
		categorys = categoryDao.loadByNames(category.getName());
		if(categorys.size()!=0){
			return "failed";
		}else{
		categoryDao.save(category);
		return "succeed";
		}
	}
	@RemotingInclude
	public int deleletByid(int cid) throws Exception {
		// TODO Auto-generated method stub
		return categoryDao.deleletByid(cid);
	}
	@RemotingInclude
	public String modify(Category category) throws Exception {
		List<Category> categorys = null;
		categorys = categoryDao.loadByNames(category.getName());
		if(categorys.size()!=0){
			return "failed";
		}else{
		categoryDao.modify(category);
		return "succeed";
		}
	}
	@RemotingInclude
	public List<Category> getCategories(int start, int limit) throws Exception {
		// TODO Auto-generated method stub
		return categoryDao.getCategorys(start, limit);
	}
	@RemotingInclude
	public Category loadById(int cid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@RemotingInclude
	public Category loadByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return categoryDao.loadByName(name);
	}
	
	@RemotingInclude
	public int getTotalNum() throws Exception {
		// TODO Auto-generated method stub
		return categoryDao.getTotalNum();
	}
	@RemotingInclude
	public List<String> getCNames() throws Exception {
		// TODO Auto-generated method stub
		return categoryDao.getCNames();
	}
	@RemotingInclude
	public List Getulist() {
		List re=new ArrayList();
		/*List s=ucrdao.Getulist();
		for(int i=0;i<s.size();i++)
		{*/
		Map ms1=new HashMap();
		ms1.put("propertyTitle","分类1");
		ms1.put("propertyValue", 100);
		re.add(ms1);
		Map ms2=new HashMap();
		ms2.put("propertyTitle","分类2");
		ms2.put("propertyValue", 52);
		re.add(ms2);
		Map ms3=new HashMap();
		ms3.put("propertyTitle","分类4");
		ms3.put("propertyValue", 230);
		re.add(ms3);
		//}
		return re;
		}

}
