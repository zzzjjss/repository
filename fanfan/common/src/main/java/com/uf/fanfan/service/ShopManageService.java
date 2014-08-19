package com.uf.fanfan.service;

import java.util.List;

import com.uf.fanfan.entity.Shop;
import com.uf.fanfan.entity.ShopManager;

public interface ShopManageService {
	public Shop findShopById(Integer id);
	public void addShop(Shop  shop);
	public void invalidShop(Shop  shop);
	public void recoverShop(Shop  shop);
	public void deleteShop(Shop  shop);
	public  List<Shop> findAllShops();
	public void modifyShop(Shop shop);
	public void addShopManager(ShopManager shopManager);
	public void deleteShopManager(ShopManager shopManager);
	public void resetShopManagerPassword(int  shopManagerId,String newPassword)throws Exception;
	public ShopManager findShopManager(int shopId);
}
