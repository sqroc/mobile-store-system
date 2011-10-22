package ms.service;

import java.util.List;

import ms.model.Category;


public interface CategoryManager {

	public abstract String add(Category category) throws Exception;

	public abstract int deleletByid(int cid) throws Exception;

	public abstract String modify(Category category) throws Exception;

	public List<Category> getCategories(int start ,int limit) throws Exception;

	public Category loadById(int cid) throws Exception;
	
	public Category loadByName(String name) throws Exception;
	
	public int getTotalNum() throws Exception;
	
	public List<String> getCNames() throws Exception;
	
	public List Getulist();
	
}
