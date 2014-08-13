package com.uf.fanfan.dao;

import java.util.List;

import com.uf.fanfan.entity.Shop;


public interface ShopDao   extends CommonDao<Shop>{
	public List<Shop> findAllShop();
}
