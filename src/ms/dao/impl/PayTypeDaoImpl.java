package ms.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ms.dao.PayTypeDao;
import ms.model.Admin;
import ms.model.Category;
import ms.model.PayType;

@Repository("PayTypeDao")
public class PayTypeDaoImpl implements PayTypeDao {
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	public void save(PayType payType) {
		hibernateTemplate.save(payType);

	}

	public int deleletByid(int id) {
		PayType payType = new PayType();
		payType.setPtid(id);
		try {
			hibernateTemplate.delete(payType);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	public void modify(PayType payType) {
		hibernateTemplate.update(payType);

	}

	public List<PayType> getPayTypes(final int start, final int limit) {
		List<PayType> payTypes = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from PayType");
						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<PayType> list = (List<PayType>) query.list();
						return list;
					}
				});
        
		
		return payTypes;
	}

	public List<PayType> getAllPayTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> getNames() {
		String queryString = "select name from PayType as payType";
		List<String> CNames = hibernateTemplate.find(queryString);

		return CNames;
	}

	public int getTotalNum() {
		String hql = "select count(*) from PayType as payType";
		return ((Long) getHibernateTemplate().iterate(hql).next()).intValue();
	}

	public PayType loadByName(String name) throws Exception {
		String queryString = "from  PayType payType where payType.name='" + name
				+ "'";

		List<PayType> PayTypes = hibernateTemplate.find(queryString);

		return PayTypes.get(0);
	}

	public List<PayType> loadByNames(String name) throws Exception {
		List<PayType> payTypes = null;
		String sql = "from PayType as payType where payType.name = '"
				+ name + "'";
		payTypes = hibernateTemplate.find(sql);
		return payTypes;
	}

}
