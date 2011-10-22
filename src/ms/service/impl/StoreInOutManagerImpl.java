package ms.service.impl;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import ms.dao.DrawDetailDao;
import ms.dao.InOutDetailDao;
import ms.dao.ItemDao;
import ms.dao.PorderDetailDao;
import ms.dao.StoreInOutDao;
import ms.model.DrawDetail;
import ms.model.InOutDetail;
import ms.model.Item;
import ms.model.PorderDetail;
import ms.model.StoreInOut;
import ms.service.StoreInOutManager;

import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;

@Service("storeInOutManager")
@RemotingDestination(channels = { "my-amf" })
public class StoreInOutManagerImpl implements StoreInOutManager {

	private StoreInOutDao storeInOutDao;
	private PorderDetailDao porderDetailDao;
	private InOutDetailDao inOutDetailDao;
	private DrawDetailDao drawDetailDao;
	private ItemDao itemDao;

	public StoreInOutDao getStoreInOutDao() {
		return storeInOutDao;
	}

	@Resource
	public void setStoreInOutDao(StoreInOutDao storeInOutDao) {
		this.storeInOutDao = storeInOutDao;
	}

	public PorderDetailDao getPorderDetailDao() {
		return porderDetailDao;
	}

	@Resource
	public void setPorderDetailDao(PorderDetailDao porderDetailDao) {
		this.porderDetailDao = porderDetailDao;
	}

	public InOutDetailDao getInOutDetailDao() {
		return inOutDetailDao;
	}

	@Resource
	public void setInOutDetailDao(InOutDetailDao inOutDetailDao) {
		this.inOutDetailDao = inOutDetailDao;
	}

	public DrawDetailDao getDrawDetailDao() {
		return drawDetailDao;
	}

	@Resource
	public void setDrawDetailDao(DrawDetailDao drawDetailDao) {
		this.drawDetailDao = drawDetailDao;
	}

	public ItemDao getItemDao() {
		return itemDao;
	}
	@Resource
	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	@RemotingInclude
	public List GetlistbyMoneyandDate(String date11, String date22, int type) {
		List re = new ArrayList();
		if (type == 1) {
			int date3 = Integer.parseInt(date11);
			int date4 = Integer.parseInt(date22);
			for (int i = date3; i <= date4; i = i + 1) {
				String date5 = i + "-01-01 00:00:00";
				String date6 = (i + 1) + "-01-01 00:00:00";
				SimpleDateFormat formatter = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				ParsePosition pos = new ParsePosition(0);
				Date date1 = formatter.parse(date5, pos);
				formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				pos = new ParsePosition(0);
				Date date2 = formatter.parse(date6, pos);
				double j = storeInOutDao.getTotalMByDate(date1, date2);
				Map ms1 = new HashMap();
				ms1.put("propertyTitle", i + "");
				ms1.put("propertyValue", j);
				re.add(ms1);
			}
		}
		return re;
	}

	@RemotingInclude
	public List<StoreInOut> getStoreInOutByDate(String date1, String date2) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date date11 = formatter.parse(date1, pos);
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		pos = new ParsePosition(0);
		Date date22 = formatter.parse(date2, pos);
		return storeInOutDao.getStoreInOutByDate(date11, date22);
	}

	@RemotingInclude
	


	public String save(StoreInOut storeInOut) {

		storeInOutDao.save(storeInOut);

	
		

		int type = storeInOut.getSatid();
		String code = storeInOut.getCode();
		if (type == 0) // 进仓
		{
			List<PorderDetail> porderDetails = porderDetailDao.loadByNums(code);
			for (int i = 0; i < porderDetails.size(); i++) {
				PorderDetail porderDetail = porderDetails.get(i);
				InOutDetail inOutDetail = new InOutDetail();
				inOutDetail.setDate(new Date());
				inOutDetail.setSatid(0);
				inOutDetail.setInOut(storeInOut);
				inOutDetail.setItem(porderDetail.getItem());
				inOutDetail.setPrice(porderDetail.getPrice());
				inOutDetail.setQuantity(porderDetail.getQuantity());
				inOutDetailDao.save(inOutDetail);
				Item item = itemDao.loadById(inOutDetail.getItem().getItem_id());
				item.setStore_num((item.getStore_num() + porderDetail.getQuantity()));
				itemDao.modify(item);
			}

		}
		if (type == 1) // 出仓
		{
			List<DrawDetail> drawDetails = drawDetailDao
					.getDrawDetailsByNum(code);
			for (int i = 0; i < drawDetails.size(); i++) {
				DrawDetail drawDetail = drawDetails.get(i);
				InOutDetail inOutDetail = new InOutDetail();
				inOutDetail.setDate(new Date());
				inOutDetail.setSatid(1);
				inOutDetail.setInOut(storeInOut);
				inOutDetail.setItem(drawDetail.getItem());
				inOutDetail.setPrice(drawDetail.getAprice());
				inOutDetail.setQuantity(drawDetail.getQuantity());
				inOutDetailDao.save(inOutDetail);
				Item item = itemDao.loadById(inOutDetail.getItem().getItem_id());
				item.setStore_num((item.getStore_num() - drawDetail.getQuantity()));
				itemDao.modify(item);
			}
		}
		return "succeed";

	}
	@RemotingInclude
	public int deleletByid(int id) {
		// TODO Auto-generated method stub
		return storeInOutDao.deleletByid(id);
	}
	@RemotingInclude
	public void modify(StoreInOut storeInOut) {
		storeInOutDao.modify(storeInOut);

	}
	@RemotingInclude
	public List<StoreInOut> getStoreInOuts(int start, int limit) {
		// TODO Auto-generated method stub
		return storeInOutDao.getStoreInOuts(start, limit);
	}
	@RemotingInclude
	public List<StoreInOut> getAllStoreInOuts() {
		// TODO Auto-generated method stub
		return storeInOutDao.getAllStoreInOuts();
	}
	@RemotingInclude
	public int getTotalNum() {
		// TODO Auto-generated method stub
		return storeInOutDao.getTotalNum();
	}
	@RemotingInclude
	public int getTotalNumByDate(String date11, String date22) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date date1 = formatter.parse(date11, pos);
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		pos = new ParsePosition(0);
		Date date2 = formatter.parse(date22, pos);
		return storeInOutDao.getTotalNumByDate(date1, date2);
	}
	@RemotingInclude
	public List<StoreInOut> getStoreInOutByDate(String date11, String date22,
			int start, int limit) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date date1 = formatter.parse(date11, pos);
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		pos = new ParsePosition(0);
		Date date2 = formatter.parse(date22, pos);
		return storeInOutDao.getStoreInOutByDate(date1, date2, start, limit);
	}

}
