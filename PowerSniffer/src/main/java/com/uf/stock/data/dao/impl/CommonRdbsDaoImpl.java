package com.uf.stock.data.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.uf.stock.data.dao.CommonDao;


public class CommonRdbsDaoImpl<T> implements CommonDao<T> {
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

  public void saveOrUpdate(T obj) {
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

  public List<T> findAll(Class<T> entity) {
    return getHibernateTemplate().loadAll(entity);
  }

  public List<T> findByHql(String hql, Object... paramValues) {
    return (List<T>) hibernateTemplate.find(hql, paramValues);
  }

  public Integer executeUpdateHql(final String hql, final Object... paramValues) {
    return hibernateTemplate.execute(new HibernateCallback<Integer>() {
      public Integer doInHibernate(Session session) throws HibernateException {
        Query query = session.createQuery(hql);
        for (int i = 0; i < paramValues.length; i++) {
          Object value = paramValues[i];
          if (value instanceof Integer) {
            query.setInteger(i, (Integer) value);
          } else if (value instanceof Float) {
            query.setFloat(i, (Float) value);
          } else {
            query.setParameter(i, value);
          }
        }
        return query.executeUpdate();
      }
    });
  }
}
