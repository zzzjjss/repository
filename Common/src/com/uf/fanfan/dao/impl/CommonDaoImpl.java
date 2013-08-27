package com.uf.fanfan.dao.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.uf.fanfan.dao.CommonDao;

public class CommonDaoImpl<T> implements CommonDao<T>{
	@Autowired
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@Override
	public void save(T obj) {
		hibernateTemplate.save(obj);
	}
	@Override
	public void delete(T entity) {
		hibernateTemplate.delete(entity);
	}

	@Override
	public T loadById(Class<T> entityClass, Serializable id) {
		T entity=(T)hibernateTemplate.load(entityClass, id);
		return entity;
	}
	
}
