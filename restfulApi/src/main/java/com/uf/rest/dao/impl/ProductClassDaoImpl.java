package com.uf.rest.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.uf.rest.dao.ProductClassDao;
import com.uf.rest.entity.ProductClass;
@Component("productClassDao")
public class ProductClassDaoImpl extends CommonDaoImpl<ProductClass> implements ProductClassDao{
	public List<ProductClass> findPagedProductClass(Integer start, Integer count){
		Query query=this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("select p from ProductClass p");
		query.setFirstResult(start);
		query.setMaxResults(count);
		return query.list();
	}
}
