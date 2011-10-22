package ms.service;

import java.util.List;

import ms.model.Storage;

public interface StorageManager {

	public abstract String add(Storage storage) throws Exception;

	public abstract int deleletByid(int id) throws Exception;

	public abstract String modify(Storage storage) throws Exception;

	public List<Storage> getStorages(int start ,int limit) throws Exception;

	public int getTotalNum() throws Exception;
}
