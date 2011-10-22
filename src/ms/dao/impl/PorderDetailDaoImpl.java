package ms.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ms.dao.PorderDetailDao;
import ms.model.Porder;
import ms.model.PorderDetail;

@Repository("PorderDetailDao")
public class PorderDetailDaoImpl implements PorderDetailDao {

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void save(PorderDetail porderDetail) {
		hibernateTemplate.save(porderDetail);

	}

	public int deleletByid(int id) {
		PorderDetail porderDetail = new PorderDetail();
		porderDetail.setPordid(id);
		try {
			hibernateTemplate.delete(porderDetail);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	public void modify(PorderDetail porderDetail) {
		hibernateTemplate.update(porderDetail);

	}

	public List<PorderDetail> getPorderDetails(final int start, final int limit) {
		List<PorderDetail> porderDetails = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from PorderDetail");

						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<PorderDetail> list = (List<PorderDetail>) query
								.list();
						return list;
					}
				});

		return porderDetails;
	}
	
	public List<PorderDetail> getPorderDetailsByNum(String num) {
	
		
		String queryString = "from  PorderDetail porderDetail where porderDetail.code='"
				+ num + "'";

		List<PorderDetail> porderDetails = hibernateTemplate.find(queryString);

		return porderDetails;
		
		
		
		
	}


	public List<PorderDetail> getPorderDetailsByNum(final String num,
			final int start, final int limit) {
		List<PorderDetail> porderDetails = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from PorderDetail as porderDetail where porderDetail.code='"
										+ num + "'");

						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<PorderDetail> list = (List<PorderDetail>) query
								.list();
						return list;
					}
				});

		return porderDetails;
	}

	public List<PorderDetail> getPorderDetailsByDate(final Date date1,
			final Date date2, final int start, final int limit) {
		List<PorderDetail> porderDetails = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from PorderDetail as porderDetail where porderDetail.billDate<=:date2 and porderDetail.billDate>=:date1");
						query.setDate("date2", date2);
						query.setDate("date1", date1);
						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<PorderDetail> list = (List<PorderDetail>) query
								.list();
						return list;
					}
				});

		return porderDetails;
	}

	public int getTotalNum() {
		String hql = "select count(*) from PorderDetail as porderDetail ";
		return ((Long) getHibernateTemplate().iterate(hql).next()).intValue();
	}

	public int getTotalNumByNum(String num) {
		String hql = "select count(*) from PorderDetail as porderDetail where porderDetail.code='"
				+ num + "'";
		return ((Long) getHibernateTemplate().iterate(hql).next()).intValue();
	}

	public int getTotalNumByDate(final Date date1, final Date date2) {
		List<Long> nums = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("select count(*) from PorderDetail porderDetail where porderDetail.billDate<=:date2 and porderDetail.billDate>=:date1");
						query.setDate("date2", date2);
						query.setDate("date1", date1);

						List<Long> nums = (List<Long>) query.list();
						return nums;
					}
				});

		return nums.get(0).intValue();
	}

	public PorderDetail loadByNum(String num) {
		String queryString = "from  PorderDetail porderDetail where porderDetail.code='"
				+ num + "'";

		List<PorderDetail> porderDetails = hibernateTemplate.find(queryString);

		return porderDetails.get(0);
	}

	public List<PorderDetail> loadByNums(String num) {
		String queryString = "from  PorderDetail porderDetail where porderDetail.code='"
				+ num + "'";

		List<PorderDetail> porderDetails = hibernateTemplate.find(queryString);

		return porderDetails;
	}

}
