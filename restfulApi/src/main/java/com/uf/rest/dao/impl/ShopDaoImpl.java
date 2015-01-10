package com.uf.rest.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.uf.rest.dao.ShopDao;
import com.uf.rest.entity.Shop;
@Component("shopDao")
public class ShopDaoImpl extends CommonDaoImpl<Shop> implements ShopDao{
	public List<Shop> findByHql(String hql,int fetchSize,Object... values){
		HibernateTemplate tmp=getHibernateTemplate();
		tmp.setFetchSize(fetchSize);
		return (List<Shop>)tmp.find(hql, values);
		
	}
	
	
	public List<Shop>  findNearShops(int start, int count,Double longitude,Double latitude){
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql="from Shop s order by sqrt((:longitude-s.longitude)*(:longitude-s.longitude)+(:latitude-s.latitude)*(:latitude-s.latitude)) asc";
		Query query=session.createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(count);
		query.setParameter("longitude", longitude);
		query.setParameter("latitude", latitude);
		return query.list();
	}
}
