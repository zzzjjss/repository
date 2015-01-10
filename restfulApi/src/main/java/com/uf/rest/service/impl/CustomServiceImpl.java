package com.uf.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uf.rest.dao.ShopDao;
import com.uf.rest.entity.Shop;
import com.uf.rest.service.CustomService;

@Service("customService")
public class CustomServiceImpl implements CustomService{
	@Autowired
	private ShopDao shopDao;
	
	public ShopDao getShopDao() {
		return shopDao;
	}

	public void setShopDao(ShopDao shopDao) {
		this.shopDao = shopDao;
	}

	@Override
	public List<Shop> findNearShops(int start, int count,Double longitude,Double latitude) {
		return shopDao.findNearShops(start, count, longitude, latitude);
	}

}
