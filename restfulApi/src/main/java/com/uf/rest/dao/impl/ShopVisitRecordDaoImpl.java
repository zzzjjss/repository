package com.uf.rest.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.uf.rest.bean.Constant;
import com.uf.rest.dao.ShopVisitRecordDao;
import com.uf.rest.entity.Order;
import com.uf.rest.entity.ShopVisitRecord;
import com.uf.rest.util.DateUtil;
@Component("shopVisitRecordDao")
public class ShopVisitRecordDaoImpl extends CommonDaoImpl<ShopVisitRecord> implements ShopVisitRecordDao{
	public Date findShopFirstVisitDate(Integer shopId){
		Query query=this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("select c from ShopVisitRecord c where   c.shop.id=:shopId order by c.date asc");
		query.setParameter("shopId", shopId);
		query.setMaxResults(1);
		List<ShopVisitRecord> orders=query.list();
		if(orders!=null&&orders.size()>0){
			return orders.get(0).getDate();
		}
		return null;
	}
	public long countShopVisitAfterDate(Integer shopId,Date date){
		Query query=this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("select count(*) from ShopVisitRecord c where  c.date>:date and c.shop.id=:shopId ");
		query.setParameter("shopId", shopId);
		query.setParameter("date", DateUtil.getDateEnd(date));
		List<Long> count=query.list();
		if(count!=null&&count.size()>0){
			return count.get(0); 
		}
		return 0;
	}
}
