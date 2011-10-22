package ms.service;

import java.util.List;

import ms.model.Category;
import ms.model.User;


public interface UserManager {
	public abstract boolean check(String username,String password) throws Exception;

	public abstract String add(User user) throws Exception;

	public abstract int deleletByid(int uid) throws Exception;

	public abstract String modify(User user) throws Exception;

	public List<User> getUsers(String paramter,int start ,int limit,int type) throws Exception;

	public User loadById(int uid) throws Exception;
	
	public int getTotalNum(String paramter,int type) throws Exception;
	
	public List<User> getOnlineUsers(User user) throws Exception;
	
	public User loadByName(String name) throws Exception;
	

}
