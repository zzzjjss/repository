package com.uf.fanfan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uf.fanfan.dao.ShopDao;
import com.uf.fanfan.entity.Shop;
import com.uf.fanfan.service.ShopManagerService;

@Service("shopManagerService")
public class ShopManagerServiceImpl implements ShopManagerService {
	@Autowired
	private ShopDao shopDao;
	
	@Override
	public Shop findShopById(Integer id) {
		Shop shop=shopDao.loadById(Shop.class, 0);
		return shop;
	}

}
