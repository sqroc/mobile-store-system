package ms.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.eclipse.jdt.internal.compiler.ast.ReturnStatement;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ms.dao.CategoryDao;
import ms.model.Admin;
import ms.model.Category;

@Repository("CategoryDao")
public class CategoryDaoImpl implements CategoryDao {

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void save(Category category) {
		hibernateTemplate.save(category);

	}

	public int deleletByid(int id) {
		Category categorytemp = new Category();
		categorytemp.setCid(id);
		try {
			hibernateTemplate.delete(categorytemp);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
		return 1;
	}

	public void modify(Category category) {
		hibernateTemplate.update(category);

	}

	public List<Category> getCategorys(final int start, final int limit) {
		List<Category> categories = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from Category");
						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<Category> list = (List<Category>) query.list();
						return list;
					}
				});
        
		
		return categories;
	}

	public List<String> getCNames() {

		String queryString = "select name from Category as category";
		List<String> CNames = hibernateTemplate.find(queryString);

		return CNames;
	}

	public Category loadByName(String name) throws Exception {
		String queryString = "from  Category cate where cate.name='" + name
				+ "'";

		List<Category> Categorys = hibernateTemplate.find(queryString);

		return Categorys.get(0);
	}

	public int getTotalNum() {
		String hql = "select count(*) from Category as category";
		return ((Long) getHibernateTemplate().iterate(hql).next()).intValue();
	}

	public List<Category> loadByNames(String name) throws Exception {
		String queryString = "from  Category cate where cate.name='" + name
				+ "'";

		List<Category> Categorys = hibernateTemplate.find(queryString);

		return Categorys;
	}

	public List<Category> getAllCategorys() {
		String queryString = "from  Category ";

		List<Category> Categorys = hibernateTemplate.find(queryString);

		return Categorys;
	}

}
