package com.uf.fanfan.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.uf.fanfan.dao.ShopDao;
import com.uf.fanfan.entity.Shop;
@Component("shopDao")
public class ShopDaoImpl  extends CommonDaoImpl<Shop> implements ShopDao{
	public List<Shop> findAllShop(){
		String hql="select  c from Shop c  ";
		return (List<Shop>) getHibernateTemplate().find(hql);

	}
}
