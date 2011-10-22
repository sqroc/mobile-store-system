package ms.dao;

import java.util.List;

import ms.model.Admin;

public interface AdminDao {
	public void save(Admin admin);

	public int deleletByid(int id);

	public void modify(Admin admin);

	public Admin check(Admin admin);

	public Admin loadByAid(int aid);
	
	public List<Admin> loadByName(String name);

	public List<Admin> getAdmins(int start, int limit);

	public int getTotalNum();

	public List<Admin> getOnlineAdmins(Admin admin);

}
