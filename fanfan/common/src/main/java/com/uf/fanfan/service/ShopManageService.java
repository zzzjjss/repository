package com.uf.fanfan.service;

import java.util.List;

import com.uf.fanfan.entity.Shop;

public interface ShopManageService {
	public Shop findShopById(Integer id);
	public void addShop(Shop  shop);
	public void invalidShop(Shop  shop);
	public void recoverShop(Shop  shop);
	public void deleteShop(Shop  shop);
	public  List<Shop> findAllShops();
	public void modifyShop(Shop shop);
	
}
