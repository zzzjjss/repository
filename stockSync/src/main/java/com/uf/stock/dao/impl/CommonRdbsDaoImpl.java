package com.uf.stock.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.uf.stock.dao.CommonDao;

public class CommonRdbsDaoImpl<T> extends HibernateDaoSupport implements CommonDao<T> {
	

	public void insert(T obj) {
		getHibernateTemplate().save(obj);
	}
	public void saveOrUpdate(T obj){
		getHibernateTemplate().saveOrUpdate(obj);
	}
	
	public void delete(T obj) {
		getHibernateTemplate().delete(obj);
	}

	public void update(T obj) {
		getHibernateTemplate().update(obj);
	}

	public T findById(Class<T> entity, Serializable id) {
		return getHibernateTemplate().get(entity, id);
	}
	public List<T> findAll(Class<T> entity){
		return getHibernateTemplate().loadAll(entity);
	}
}
