package ms.service;

import java.util.List;

import ms.model.Cartinfo;


public interface CartinfoManager {

	public abstract String add(Cartinfo cartinfo) throws Exception;

	public abstract int deleletByid(int id) throws Exception;

	public int deleletByUid(int id);
	
	public abstract String modify(Cartinfo cartinfo) throws Exception;

	public List<Cartinfo> getCartinfos(int start ,int limit,int uid) throws Exception;

	public List<Cartinfo> loadbyuid(int uid) throws Exception;

}
