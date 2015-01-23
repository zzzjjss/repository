package com.uf.rest.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.uf.rest.dao.ShopWithDrawRecordDao;
import com.uf.rest.entity.ShopWithDrawRecord;

@Component("shopWithDrawRecordDao")
public class ShopWithDrawRecordDaoImpl  extends CommonDaoImpl<ShopWithDrawRecord> implements ShopWithDrawRecordDao{
	public List<ShopWithDrawRecord> findPagedWithdraw(Integer shopId,Integer start,Integer count){
		Query query=this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(" from ShopWithDrawRecord o where o.shop.id=:shopId order by o.withdrawTime desc");
		query.setParameter("shopId", shopId);
		query.setFirstResult(start);
		query.setMaxResults(count);
		return query.list();
	}
}
