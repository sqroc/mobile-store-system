package ms.service;

import java.util.List;

import ms.model.Store;

public interface StoreManager {
	public abstract String save(Store store) throws Exception;

	public abstract int deleletByid(int cid) throws Exception;

	public abstract String modify(Store store) throws Exception;

	public List<Store> getStores(int start, int limit) throws Exception;

	public Store loadById(int cid) throws Exception;

	public Store loadByName(String name) throws Exception;

	public int getTotalNum() throws Exception;

	public List<String> getNames() throws Exception;

}
