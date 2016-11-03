package com.uf.stock.data.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.uf.stock.data.dao.CommonDao;


public class CommonRdbsDaoImpl<T>  implements CommonDao<T> {
  @Autowired
  private HibernateTemplate hibernateTemplate;

  public HibernateTemplate getHibernateTemplate() {
      return hibernateTemplate;
  }

  public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
      this.hibernateTemplate = hibernateTemplate;
  }

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
