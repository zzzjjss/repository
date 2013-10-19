package com.uf.fanfan.dao;

import com.uf.fanfan.entity.ShopManager;

public interface ShopManagerDao  extends CommonDao<ShopManager>{
	//@Query("select t from ShopManager t where t.name=:name")
	public ShopManager findByName(String name);
}
