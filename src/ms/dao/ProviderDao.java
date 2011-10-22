package ms.dao;

import java.util.List;

import ms.model.Provider;

public interface ProviderDao {
	public void save(Provider provider);

	public int deleletByid(int id);

	public void modify(Provider provider);

	public Provider loadByAid(int pid);

	public List<Provider> getProviders(int start, int limit);
	
	public List<Provider> getProviders(String name,int start, int limit);

	public int getTotalNum();
	
	public int getTotalNumByName(String name);

	public List<String> getPNames();
	
	public Provider loadByName(String pname) ;
	
	public List<Provider> loadByNames(String pname) ;
}
