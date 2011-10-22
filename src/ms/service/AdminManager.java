package ms.service;

import java.util.List;

import ms.model.Admin;

public interface AdminManager {
	
	public abstract Admin check(String username,String password) throws Exception;

	public abstract String add(Admin admin) throws Exception;

	public abstract int deleletByid(int aid) throws Exception;

	public abstract String modify(Admin admin) throws Exception;

	public List<Admin> getAdmins(int start ,int limit) throws Exception;
	
	

	public Admin loadById(int aid) throws Exception;
	
	public int getTotalNum() throws Exception;
	
	public List<Admin> getOnlineAdmins(Admin admin) throws Exception;

	
}
