package com.uf.rest.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.uf.rest.dao.ShopWithDrawRecordDao;
import com.uf.rest.entity.ShopWithDrawRecord;

@Component("shopWithDrawRecordDao")
public class ShopWithDrawRecordDaoImpl  extends CommonDaoImpl<ShopWithDrawRecord> implements ShopWithDrawRecordDao{
	public List<ShopWithDrawRecord> findPagedWithdraw(Integer shopId,Integer start,Integer count){
		//Query query=this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(" from ShopWithDrawRecord o where o.shop.id=:shopId order by o.withdrawTime desc");
		Query query=this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(" from ShopWithDrawRecord o where o.shop.id=:shopId and o.id>=:start order by o.id asc");
		query.setParameter("shopId", shopId);
		query.setParameter("start", start);
		query.setMaxResults(count);
		return query.list();
	}
}
