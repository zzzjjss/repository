package com.uf.fanfan.dao;

import java.util.List;

import com.uf.fanfan.entity.ShopManager;

public interface ShopManagerDao  extends CommonDao<ShopManager>{
	
	public void addShopManager(ShopManager shopManager);
	public  void updatePassword(Integer id,String newPassword);
	public List<ShopManager> findByShopId(int  shopId);
}
