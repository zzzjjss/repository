package com.uf.fanfan.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.uf.fanfan.dao.ShopManagerDao;
import com.uf.fanfan.entity.ShopManager;

@Component("shopManagerDao")
public class ShopManagerDaoImpl extends CommonDaoImpl<ShopManager> implements ShopManagerDao{

	
	public void addShopManager(ShopManager shopManager){
		this.insert(shopManager);
	}
	public  void updatePassword(Integer id,String newPassword){
		String hql="update  ShopManager s set s.password=:password where  s.id=:id";
		Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createQuery(hql);
		query.setInteger("id", id);
		query.setString("password", newPassword);
		query.executeUpdate();
	}
	
	public List<ShopManager> findByShopId(int  shopId){
		String hql="select s from  ShopManager s where  s.shop.id=:id";
		Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createQuery(hql);
		query.setInteger("id", shopId);
		return (List<ShopManager>)query.list();
		
	}

}
