package com.uf.rest.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.uf.rest.dao.OrderAddressDao;
import com.uf.rest.entity.OrderAddress;
@Component("orderAddressDao")
public class OrderAddressDaoImpl extends CommonDaoImpl<OrderAddress> implements OrderAddressDao{
	
	public List<OrderAddress> findPagedUserOrderAddress(Integer userId,Integer start,Integer count){
		Query query=this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(" from OrderAddress o where o.user.id=:userId and o.id>=:start order by o.id asc");
		query.setParameter("userId", userId);
		query.setParameter("start", start);
		query.setMaxResults(count);
		return query.list();
	}
}
