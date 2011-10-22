package ms.dao;

import java.util.List;

import ms.model.Cartinfo;



public interface CartinfoDao {

	public void save(Cartinfo cartinfo);

	public int deleletByid(int id);

	public int deleletByUid(int id);
	
	public void modify(Cartinfo cartinfo);
	
	public List<Cartinfo> getCartinfos(int start, int limit,int cid);
	
	public List<Cartinfo> loadbyuid (int uid);

}
