package ms.service.impl;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import ms.dao.CategoryDao;
import ms.dao.InOutDetailDao;
import ms.dao.ItemDao;
import ms.model.Category;
import ms.model.InOutDetail;
import ms.model.Item;
import ms.service.InOutDetailManager;

import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;

@Service("inOutDetailManager")
@RemotingDestination(channels = { "my-amf" })
public class InOutDetailManagerImpl implements InOutDetailManager {

	private InOutDetailDao inOutDetailDao;
	private ItemDao itemDao;
	private CategoryDao categoryDao;

	public InOutDetailDao getInOutDetailDao() {
		return inOutDetailDao;
	}

	@Resource
	public void setInOutDetailDao(InOutDetailDao inOutDetailDao) {
		this.inOutDetailDao = inOutDetailDao;
	}

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	@Resource
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public ItemDao getItemDao() {
		return itemDao;
	}

	@Resource
	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	@RemotingInclude
	public List GetlistbyCategory(String date11, String date22) {
	
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date date1 = formatter.parse(date11, pos);
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		pos = new ParsePosition(0);
		Date date2 = formatter.parse(date22, pos);
		List re = new ArrayList();
		List<Category> Categorys = categoryDao.getAllCategorys();
		for (int i = 0; i < Categorys.size(); i++) {
		
			int j = 0;
			j = inOutDetailDao.getInOutDetailsByDateAndCategory(date1, date2,
					Categorys.get(i).getCid());
		
			Map ms1 = new HashMap();
			ms1.put("propertyTitle", Categorys.get(i).getName());
			ms1.put("propertyValue", j);
			re.add(ms1);
		}
		return re;
	}
	@RemotingInclude
	public List GetlistbyItem(String date11, String date22, String name,
			int type) {
		List re = new ArrayList();
		List<Item> items = itemDao.getItemsByName(name, 0, 1);
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
				int j = inOutDetailDao.getInOutDetailsByDateAndItem(date1,
						date2, items.get(0).getItem_id());
				Map ms1 = new HashMap();
				ms1.put("propertyTitle", i + "");
				ms1.put("propertyValue", j);
				re.add(ms1);
			}
		}
		if (type == 2) {
			int date3 = Integer.parseInt(date11);
			for (int i = 1; i <= 8; i = i + 1) {
				String date5 = date3 + "-0"+i+"-01 00:00:00";
				String date6 = date3 + "-0"+(i+1)+"-01 00:00:00";
				SimpleDateFormat formatter = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				ParsePosition pos = new ParsePosition(0);
				Date date1 = formatter.parse(date5, pos);
				formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				pos = new ParsePosition(0);
				Date date2 = formatter.parse(date6, pos);
				int j = inOutDetailDao.getInOutDetailsByDateAndItem(date1,
						date2, items.get(0).getItem_id());
				Map ms1 = new HashMap();
				ms1.put("propertyTitle", i + "");
				ms1.put("propertyValue", j);
				re.add(ms1);
			}
			
			
			String date5 = date3 + "-09-01 00:00:00";
			String date6 = date3 + "-10-01 00:00:00";
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			ParsePosition pos = new ParsePosition(0);
			Date date1 = formatter.parse(date5, pos);
			formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pos = new ParsePosition(0);
			Date date2 = formatter.parse(date6, pos);
			int j = inOutDetailDao.getInOutDetailsByDateAndItem(date1,
					date2, items.get(0).getItem_id());
			Map ms1 = new HashMap();
			ms1.put("propertyTitle", "9");
			ms1.put("propertyValue", j);
			re.add(ms1);
			
			
			
			

			date5 = date3 + "-10-01 00:00:00";
			date6 = date3 + "-11-01 00:00:00";
			formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			pos = new ParsePosition(0);
			date1 = formatter.parse(date5, pos);
			formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pos = new ParsePosition(0);
			date2 = formatter.parse(date6, pos);
			j = inOutDetailDao.getInOutDetailsByDateAndItem(date1,
					date2, items.get(0).getItem_id());
			ms1 = new HashMap();
			ms1.put("propertyTitle", "10");
			ms1.put("propertyValue", j);
			re.add(ms1);
			
			date5 = date3 + "-11-01 00:00:00";
			date6 = date3 + "-12-01 00:00:00";
			formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			pos = new ParsePosition(0);
			date1 = formatter.parse(date5, pos);
			formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pos = new ParsePosition(0);
			date2 = formatter.parse(date6, pos);
			j = inOutDetailDao.getInOutDetailsByDateAndItem(date1,
					date2, items.get(0).getItem_id());
			ms1 = new HashMap();
			ms1.put("propertyTitle", "11");
			ms1.put("propertyValue", j);
			re.add(ms1);
			
			date5 = date3 + "-12-01 00:00:00";
			date6 = date3 + "-12-31 23:59:59";
			formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			pos = new ParsePosition(0);
			date1 = formatter.parse(date5, pos);
			formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pos = new ParsePosition(0);
			date2 = formatter.parse(date6, pos);
			j = inOutDetailDao.getInOutDetailsByDateAndItem(date1,
					date2, items.get(0).getItem_id());
			ms1 = new HashMap();
			ms1.put("propertyTitle", "12");
			ms1.put("propertyValue", j);
			re.add(ms1);
			
		}
		return re;
	}
	@RemotingInclude
	public List GetlistDetailbyItem(String date11, String date22, String name) {
		List<Item> items = itemDao.getItemsByName(name, 0, 1);
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date date1 = formatter.parse(date11, pos);
		formatter = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		pos = new ParsePosition(0);
		Date date2 = formatter.parse(date22, pos);
		return inOutDetailDao.getInOutDetailsByDate(date1, date2, items.get(0).getItem_id());
	}

	public void save(InOutDetail inOutDetail) {
		inOutDetailDao.save(inOutDetail);
		
	}

	public int deleletByid(int id) {
		// TODO Auto-generated method stub
		return inOutDetailDao.deleletByid(id);
	}

	public void modify(InOutDetail inOutDetail) {
		inOutDetailDao.modify(inOutDetail);
		
	}

	public List<InOutDetail> getInOutDetails(int start, int limit) {
		// TODO Auto-generated method stub
		return inOutDetailDao.getInOutDetails(start, limit);
	}

	public int getTotalNum() {
		// TODO Auto-generated method stub
		return inOutDetailDao.getTotalNum();
	}

}
