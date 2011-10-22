package ms.dao;

import java.util.List;

import ms.model.Category;


public interface CategoryDao {
	public void save(Category category);

	public int deleletByid(int id);

	public void modify(Category category);
	
	public List<Category> getCategorys(int start, int limit);
	
	public List<Category> getAllCategorys();
	
	public List<String> getCNames();
	
	public int getTotalNum();

	public Category loadByName(String name) throws Exception;
	
	public List<Category> loadByNames(String name) throws Exception;
}
