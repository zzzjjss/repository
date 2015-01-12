package com.uf.rest.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.uf.rest.bean.Constant;
import com.uf.rest.dao.OrderDao;
import com.uf.rest.entity.Order;
@Component("orderDao")
public class OrderDaoImpl extends CommonDaoImpl<Order> implements OrderDao{
	public int findUserProcessingOrderCount(int userId){
		Query query=this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("select count(*) from Order o where o.user.id=:userId and o.orderState="+Constant.ORDER_STATE_PROCESSING);
		query.setParameter("userId",userId);
		return ((Number)query.uniqueResult()).intValue();
	}
	public List<Order>  findPagedOrdersByState(Integer userId,Integer state,Integer start,Integer count){
		Query query=this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(" from Order o where o.user.id=:userId and o.orderState=:state");
		query.setParameter("userId", userId);
		query.setParameter("state", state);
		query.setFirstResult(start);
		query.setMaxResults(count);
		return query.list();
	}
}
