package com.uf.fanfan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uf.fanfan.common.ShopStatus;
import com.uf.fanfan.dao.AgentDao;
import com.uf.fanfan.dao.CustomerOrderDao;
import com.uf.fanfan.dao.ProductDao;
import com.uf.fanfan.dao.ShopDao;
import com.uf.fanfan.entity.Shop;
import com.uf.fanfan.service.ShopManageService;

@Service("shopManagerService")
public class ShopManageServiceImpl implements ShopManageService {
	@Autowired
	private ShopDao shopDao;
	
	@Override
	public Shop findShopById(Integer id) {
		Shop shop=shopDao.loadById(Shop.class, id);
		return shop;
	}
	
	
	public ShopDao getShopDao() {
		return shopDao;
	}
	public void setShopDao(ShopDao shopDao) {
		this.shopDao = shopDao;
	}
	


	@Override
	public void addShop(Shop shop) {
		shopDao.insert(shop);
	}


	@Override
	public void invalidShop(Shop shop) {
		shop.setStatus(ShopStatus.OFFLINE);
		shopDao.update(shop);
	}


	@Override
	public void recoverShop(Shop shop) {
		shop.setStatus(ShopStatus.NORMAL);
		shopDao.update(shop);
	}


	@Override
	public void deleteShop(Shop shop) {
		shopDao.delete(shop);
		
	}


	@Override
	public List<Shop> findAllShops() {
		return shopDao.findAllShop();
	}


	@Override
	public void modifyShop(Shop shop) {
		shopDao.update(shop);
		
	}
	
}
