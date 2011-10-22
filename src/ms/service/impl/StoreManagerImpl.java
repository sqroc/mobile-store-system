package ms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import ms.dao.StoreDao;
import ms.model.Category;
import ms.model.Store;
import ms.service.StoreManager;

import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;

@Service("storeManager")
@RemotingDestination(channels = { "my-amf" })
public class StoreManagerImpl implements StoreManager {

	private StoreDao storeDao;

	public StoreDao getStoreDao() {
		return storeDao;
	}

	@Resource
	public void setStoreDao(StoreDao storeDao) {
		this.storeDao = storeDao;
	}

	@RemotingInclude
	public String save(Store store) throws Exception {
		List<Store> stores = null;
		stores = storeDao.loadByNames(store.getName());
		if (stores.size() != 0) {
			return "failed";
		} else {
			storeDao.save(store);
			return "succeed";
		}
	}

	@RemotingInclude
	public int deleletByid(int cid) throws Exception {
		// TODO Auto-generated method stub
		return storeDao.deleletByid(cid);
	}

	@RemotingInclude
	public String modify(Store store) throws Exception {
		//List<Store> stores = null;
		//stores = storeDao.loadByNames(store.getName());
		//if (stores.size() != 0) {
		//	return "failed";
		//} else {
			storeDao.modify(store);
			return "succeed";
		//}
	}

	@RemotingInclude
	public List<Store> getStores(int start, int limit) throws Exception {
		// TODO Auto-generated method stub
		return storeDao.getStores(start, limit);
	}

	@RemotingInclude
	public Store loadById(int cid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@RemotingInclude
	public Store loadByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return storeDao.loadByName(name);
	}

	@RemotingInclude
	public int getTotalNum() throws Exception {
		// TODO Auto-generated method stub
		return storeDao.getTotalNum();
	}

	@RemotingInclude
	public List<String> getNames() throws Exception {
		// TODO Auto-generated method stub
		return storeDao.getNames();
	}

}
