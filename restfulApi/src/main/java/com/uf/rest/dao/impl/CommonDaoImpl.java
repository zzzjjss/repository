package com.uf.rest.dao.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

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

	

	
}
