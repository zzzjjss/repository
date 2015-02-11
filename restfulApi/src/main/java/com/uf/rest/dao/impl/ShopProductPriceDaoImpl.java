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
	public List<ShopProductPrice> findPagedShopGoodPriceInfo(Integer shopId,Integer start, Integer count){
		Query query=this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("select o from ShopProductPrice o where o.shop.id=:shopId and o.id>=:start order by o.id asc");
		query.setParameter("shopId", shopId);
		query.setParameter("start", start);
		query.setMaxResults(count);
		return query.list();
	}
	public ShopProductPrice findByShopIdAndProductId(Integer shopId,Integer productId){
		Query query=this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(" from ShopProductPrice o where o.shop.id=:shopId and o.product.id=:proId");
		query.setParameter("shopId", shopId);
		query.setParameter("proId", productId);
		List<ShopProductPrice> prices=query.list();
		if(prices!=null&&prices.size()>0)
			return prices.get(0);
		return null;
	}
	public  void deleteByShopIdAndProductId(Integer shopId,Integer productId){
		Query query=this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(" delete  ShopProductPrice o where o.shop.id=:shopId and o.product.id=:proId");
		query.setParameter("shopId", shopId);
		query.setParameter("proId", productId);
		query.executeUpdate();
	}
}
