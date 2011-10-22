package ms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import ms.dao.ProviderDao;
import ms.model.Admin;
import ms.model.Provider;
import ms.service.ProviderManager;

import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;

@Service("providerManager")
@RemotingDestination(channels={"my-amf"})
public class ProviderManagerImpl implements ProviderManager {
	private ProviderDao providerDao;
	
	
	public ProviderDao getProviderDao() {
		return providerDao;
	}
	@Resource
	public void setProviderDao(ProviderDao providerDao) {
		this.providerDao = providerDao;
	}
	@RemotingInclude
	public String add(Provider provider) throws Exception {
		List<Provider> providers = null;
		providers = providerDao.loadByNames(provider.getName());
		if(providers.size()!=0){
			return "failed";
		}else{
		providerDao.save(provider);
		return "succeed";
		}
	}
	@RemotingInclude
	public int deleletByid(int pid) throws Exception {
		
		return providerDao.deleletByid(pid);
	}
	@RemotingInclude
	public String modify(Provider provider) throws Exception {
		
		providerDao.modify(provider);
		return "succeed";
		
	}
	@RemotingInclude
	public List<Provider> getProviders(String paramter,int start ,int limit,int type) throws Exception {
		if(type==1){
			return providerDao.getProviders(paramter, start, limit);
		}else{
			return providerDao.getProviders(start, limit);
		}
		
	}
	@RemotingInclude
	public Provider loadById(int pid) throws Exception {
		// TODO Auto-generated method stub
		return providerDao.loadByAid(pid);
	}
	@RemotingInclude
	public int getTotalNum(String paramter,int type) throws Exception {
		if(type==1){
			return providerDao.getTotalNumByName(paramter);
		}else{
			return providerDao.getTotalNum();
		}
		
	}
	@RemotingInclude
	public List<String> getPNames() {
		// TODO Auto-generated method stub
		return providerDao.getPNames();
	}

	public Provider loadByName(String pname) 
	{
		
		return providerDao.loadByName(pname);
	}
}
