package ms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;

import ms.dao.StorageDao;
import ms.model.Storage;
import ms.service.StorageManager;

@Service("storageManager")
@RemotingDestination(channels={"my-amf"})
public class StorageManagerImpl implements StorageManager {
	private StorageDao storageDao;
	
	public StorageDao getStorageDao() {
		return storageDao;
	}
	@Resource
	public void setStorageDao(StorageDao storageDao) {
		this.storageDao = storageDao;
	}
	@RemotingInclude
	public String add(Storage storage) throws Exception {
		storageDao.save(storage);
		return "succeed";
	}
	@RemotingInclude
	public int deleletByid(int id) throws Exception {
		return storageDao.deleletByid(id);
	}
	@RemotingInclude
	public String modify(Storage storage) throws Exception {
		storageDao.modify(storage);
		return "succeed";
	}
	@RemotingInclude
	public List<Storage> getStorages(int start, int limit) throws Exception {
		return storageDao.getStorages(start, limit);
	}
	@RemotingInclude
	public int getTotalNum() throws Exception {
		return storageDao.getTotalNum();
	}

}
