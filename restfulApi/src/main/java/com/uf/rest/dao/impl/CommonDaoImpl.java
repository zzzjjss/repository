package com.uf.rest.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.uf.rest.bean.PageQueryResult;
import com.uf.rest.dao.CommonDao;

public class CommonDaoImpl<T> implements CommonDao<T> {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public void insert(T obj) {
		hibernateTemplate.save(obj);
	}
	
	@Override
	public void delete(T entity) {
		hibernateTemplate.delete(entity);
	}
	@Override
	public void update(T obj){
		hibernateTemplate.update(obj);
	}
	@Override
	public T findById(Class<T> entityClass, Serializable id) {
		T entity = (T) hibernateTemplate.get(entityClass, id);
		return entity;
	}

	protected PageQueryResult<T> queryPageEntity(final int pageSize,final int pageIndex, final String hql,final Map<String, Object> paramValue) {
		final String countHql = parseCountHql(hql);
		final PageQueryResult<T> result = new PageQueryResult<T>();
		hibernateTemplate.execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query countQuery = session.createQuery(countHql);
				Query query = session.createQuery(hql);
				if (paramValue != null && paramValue.size() > 0) {
					for (String param : paramValue.keySet()) {
						query.setParameter(param, paramValue.get(param));
						countQuery.setParameter(param, paramValue.get(param));
					}
				}
				Long totalRow = (Long) countQuery.list().get(0);
				int totalPage = countTotalPage(totalRow.intValue(), pageSize);
				query.setMaxResults(pageSize);
				query.setFirstResult(pageSize * (pageIndex - 1));
				result.setPageData(query.list());
				result.setPageIndex(pageIndex);
				result.setPageSize(pageSize);
				result.setTotalPage(totalPage);
				result.setTotalRecord(totalRow.intValue());
				return null;
			}
		});
		return result;
	}

	private int countTotalPage(int totalRow, int pageSize) {
		int totalPage = totalRow / pageSize;
		int tmp = totalRow % pageSize;
		if (tmp > 0)
			totalPage++;
		return totalPage;
	}

	private String parseCountHql(String hql) {
		int index = hql.indexOf(" from ");
		String afterFrom = hql.substring(index);
		return "select count(*) " + afterFrom;
	}
}
