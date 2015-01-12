package com.uf.rest.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.uf.rest.dao.OrderDetailDao;
import com.uf.rest.entity.OrderDetail;
@Component("orderDetailDao")
public class OrderDetailDaoImpl extends CommonDaoImpl<OrderDetail> implements OrderDetailDao{
	public void deleteByOrderId(Integer orderId){
		Query query=getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("delete from OrderDetail od where od.order.id=:orderId");
		query.setParameter("orderId", orderId);
		query.executeUpdate();
	}
}
