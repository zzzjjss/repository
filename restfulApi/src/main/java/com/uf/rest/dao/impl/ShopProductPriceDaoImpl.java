package com.uf.rest.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.uf.rest.dao.ShopProductPriceDao;
import com.uf.rest.entity.ShopProductPrice;
@Component("shopProductPriceDao")
public class ShopProductPriceDaoImpl  extends CommonDaoImpl<ShopProductPrice> implements ShopProductPriceDao{
	public List<ShopProductPrice> findShopProductPriceByGoodIds(List<Integer> goodIds,Integer shopId){
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql="from ShopProductPrice s where s.product.id in (:ids) and s.shop.id=:shopId"; 
		Query query=session.createQuery(hql);
		query.setParameterList("ids",goodIds);
		query.setParameter("shopId", shopId);
		return query.list();
	}
}
