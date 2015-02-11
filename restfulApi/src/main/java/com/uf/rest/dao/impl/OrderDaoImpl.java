package com.uf.rest.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.uf.rest.bean.Constant;
import com.uf.rest.dao.OrderDao;
import com.uf.rest.entity.Order;
import com.uf.rest.util.DateUtil;
@Component("orderDao")
public class OrderDaoImpl extends CommonDaoImpl<Order> implements OrderDao{
	public int findUserProcessingOrderCount(int userId){
		Query query=this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("select count(*) from Order o where o.user.id=:userId and o.orderState="+Constant.ORDER_STATE_PROCESSING);
		query.setParameter("userId",userId);
		return ((Number)query.uniqueResult()).intValue();
	}
	public List<Order>  findPagedOrdersByState(Integer userId,Integer state,Integer start,Integer count){
		Query query=this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(" from Order o where o.user.id=:userId and o.orderState=:state and o.id>=:start order by o.id asc");
		query.setParameter("userId", userId);
		query.setParameter("state", state);
		query.setParameter("start", start);
		query.setMaxResults(count);
		return query.list();
	}
	public List<Order> findPagedShopOrderByOrderState(Integer shopId,Integer orderState,Integer start,Integer count){
		Query query=this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(" from Order o where o.shop.id=:shopId and o.orderState=:state and o.id>=:start order by o.id asc");
		query.setParameter("shopId", shopId);
		query.setParameter("state", orderState);
		query.setParameter("start", start);
		query.setMaxResults(count);
		return query.list();
	}
	
	public List<Order> findOneDayOrdersByOrderState(Integer shopId,Date date ,Integer orderState){
		String hql="select c from Order c where c.createTime>=:begin and c.createTime<=:end and c.shop.id=:shopId and c.orderState=:state";
		String param[]=new String[]{"begin","end","shopId","state"};
		
		Object value[]=new Object[]{DateUtil.getDateBegin(date),DateUtil.getDateEnd(date),shopId,orderState};
		return (List<Order>) getHibernateTemplate().findByNamedParam(hql, param, value);
	}
	public List<Order> findOneDaySucessOrders(Integer shopId,Date date ){
		String hql="select c from Order c where c.createTime>=:begin and c.createTime<=:end and c.shop.id=:shopId and c.orderState>=:state";
		String param[]=new String[]{"begin","end","shopId","state"};
		
		Object value[]=new Object[]{DateUtil.getDateBegin(date),DateUtil.getDateEnd(date),shopId,Constant.ORDER_STATE_PROCESSING};
		return (List<Order>) getHibernateTemplate().findByNamedParam(hql, param, value);
	}
	public Date findShopFirstSuccessOrderDate(Integer shopId){
		Query query=this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("select c from Order c where  c.shop.id=:shopId and ((c.paymentType="+Constant.ORDER_PAYTYPE_CASH+" and c.orderState>="+Constant.ORDER_STATE_PROCESSING+") "
				+ "	or (c.paymentType!="+Constant.ORDER_PAYTYPE_CASH+" and c.orderState>="+Constant.ORDER_STATE_WAITGET+"))  order by c.createTime asc");
		query.setParameter("shopId", shopId);
		query.setMaxResults(1);
		List<Order> orders=query.list();
		if(orders!=null&&orders.size()>0){
			return orders.get(0).getCreateTime();
		}
		return null;
	}
	public long countShopSucessOrderAfterDate(Integer shopId,Date date){
		Query query=this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("select count(*) from Order c where  c.createTime>:date and c.shop.id=:shopId and ((c.paymentType="+Constant.ORDER_PAYTYPE_CASH+" and c.orderState>="+Constant.ORDER_STATE_PROCESSING+") "
				+ "	or (c.paymentType!="+Constant.ORDER_PAYTYPE_CASH+" and c.orderState>="+Constant.ORDER_STATE_WAITGET+"))  order by c.createTime asc");
		query.setParameter("shopId", shopId);
		query.setParameter("date", DateUtil.getDateEnd(date));
		List<Long> count=query.list();
		if(count!=null&&count.size()>0){
			return count.get(0); 
		}
		return 0;
	}
	public List<Order> findSuccessShopOrder(Integer shopId,Date start,Date end){
		String hql="select c from Order c where c.createTime>=:begin and c.createTime<=:end and c.shop.id=:shopId and ((c.paymentType="+Constant.ORDER_PAYTYPE_CASH+" and c.orderState>="+Constant.ORDER_STATE_PROCESSING+") "
				+ "	or (c.paymentType!="+Constant.ORDER_PAYTYPE_CASH+" and c.orderState>="+Constant.ORDER_STATE_WAITGET+")) ";
		String param[]=new String[]{"begin","end","shopId"};
		Object value[]=new Object[]{DateUtil.getDateBegin(start),DateUtil.getDateEnd(end),shopId};
		return (List<Order>) getHibernateTemplate().findByNamedParam(hql, param, value);
	}
	public List<Order> findAllSucessShopOrder(Integer shopId){
		String hql="select c from Order c where  c.shop.id=:shopId and ((c.paymentType="+Constant.ORDER_PAYTYPE_CASH+" and c.orderState>="+Constant.ORDER_STATE_PROCESSING+") "
				+ "	or (c.paymentType!="+Constant.ORDER_PAYTYPE_CASH+" and c.orderState>="+Constant.ORDER_STATE_WAITGET+")) ";
		String param[]=new String[]{"shopId"};
		Object value[]=new Object[]{shopId};
		return (List<Order>) getHibernateTemplate().findByNamedParam(hql, param, value);
	}
	public List<Order> findShopOrderByOrderState(Integer shopId,Integer orderState){
		String hql="select c from Order c where  c.shop.id=:shopId and c.orderState=:state";
		String param[]=new String[]{"shopId","state"};
		Object value[]=new Object[]{shopId,orderState};
		return (List<Order>) getHibernateTemplate().findByNamedParam(hql, param, value);
	}
}
