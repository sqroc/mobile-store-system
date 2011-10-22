package ms.service;

import java.util.List;

import ms.model.Provider;


public interface ProviderManager {

	public abstract String add(Provider provider) throws Exception;

	public abstract int deleletByid(int pid) throws Exception;

	public abstract String modify(Provider provider) throws Exception;

	public List<Provider> getProviders(String paramter,int start ,int limit,int type) throws Exception;

	public Provider loadById(int pid) throws Exception;
	
	public Provider loadByName(String pname) throws Exception;
	
	public int getTotalNum(String paramter,int type) throws Exception;
	
	public List<String> getPNames();

}
