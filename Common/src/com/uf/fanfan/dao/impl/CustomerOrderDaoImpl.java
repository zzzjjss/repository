package com.uf.fanfan.dao.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.uf.fanfan.dao.CustomerOrderDao;
import com.uf.fanfan.entity.Agent;
import com.uf.fanfan.entity.CustomerOrder;
import com.uf.fanfan.entity.Shop;
import com.uf.fanfan.util.DateUtil;

public class CustomerOrderDaoImpl  extends CommonDaoImpl<CustomerOrder> implements CustomerOrderDao{

	@Override
	public List<CustomerOrder> findCustomerOrdersBetweenArriveTime(Date begin,
			Date end, int customerId) {
		String hql="select c from CustomerOrder c where c.arrivetime>=:begin and c.arrivetime<=:end and c.customer.id=:cusId";
		String param[]=new String[]{"begin","end","cusId"};
		Object value[]=new Object[]{begin,end,customerId};
		return getHibernateTemplate().findByNamedParam(hql, param, value);
	}

	@Override
	public List<CustomerOrder> getInProcessingOrderByProductid(Integer productid) {
		String hql="select c from CustomerOrder c ,OrderDetail d where d.id in elements(c.orderDetails) and d.productid=:productid";
		return getHibernateTemplate().findByNamedParam(hql, "productid", productid);
	}

	@Override
	public List<CustomerOrder> getOneDayOrdersInShop(Shop shop, Date date) {
		String hql1="select p.id from Product p where p.shop.id=:shopid";
		List<Integer> productIds=getHibernateTemplate().findByNamedParam(hql1, "shopid", shop.getId());
		String hql="select c from CustomerOrder c ,OrderDetail d where d.id in elements(c.orderDetails) and d.productid in (:listIds) and c.arrivetime=:arriveDate";
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createQuery(hql);
		query.setParameter("arriveDate", date);
		query.setParameterList("listIds", productIds);
		return query.list();
	}

	@Override
	public List<CustomerOrder> getShopOnedayOrdersInAgent(Shop shop ,Agent agent,Date date){
		String hql1="select p.id from Product p where p.shop.id=:shopid";
		List<Integer> productIds=getHibernateTemplate().findByNamedParam(hql1, "shopid", shop.getId());
		String hql="select c from CustomerOrder c ,OrderDetail d where d.id in elements(c.orderDetails) and d.productid in (:listIds) and c.arrivetime=:arriveDate and c.customer.agent.id=:agentId";
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createQuery(hql);
		query.setParameter("arriveDate", date);
		query.setParameterList("listIds", productIds);
		query.setParameter("agentId", agent.getId());
		return query.list();
	}
	
	public List<CustomerOrder> getCustomerOnedayOrders(Integer customerId,Date date){
		Date begin=DateUtil.getDateBegin(date);
		Date end=DateUtil.getDateEnd(date);
		String hql="select  c from CustomerOrder c where c.customer.id=:cusId and c.arrivetime>=:begin and c.arrivetime<=:end ";
		return getHibernateTemplate().findByNamedParam(hql, new String[]{"cusId","begin","end"}, new Object[]{customerId,begin,end});

	}

}
