package ms.dao;

import java.util.List;

import ms.model.Category;
import ms.model.User;


public interface UserDao {
	public void save(User user);

	public int deleletByid(int id);

	public void modify(User user);

	public User check(User user);

	public User loadByUid(int uid);

	public List<User> getUsers(int start, int limit);
	
	public List<User> getUsersByName(String name,int start, int limit);

	public int getTotalNum();
	
	public int getTotalNumByName(String name);
	
	public User loadByName(String name) throws Exception;
	
	public List<User> loadByNames(String name) throws Exception;

}
