package com.uf.fanfan.dao.impl;

import org.springframework.stereotype.Component;

import com.uf.fanfan.dao.ShopManagerDao;
import com.uf.fanfan.entity.ShopManager;

@Component("shopManagerDao")
public class ShopManagerDaoImpl extends CommonDaoImpl<ShopManager> implements ShopManagerDao{

	
	public void addShopManager(ShopManager shopManager){
		this.insert(shopManager);
	}
	

}
