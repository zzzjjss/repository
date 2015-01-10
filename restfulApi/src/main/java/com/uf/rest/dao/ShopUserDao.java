package com.uf.rest.dao;

import com.uf.rest.entity.ShopUser;

public interface ShopUserDao extends CommonDao<ShopUser>{
	public ShopUser findByName(String userName);
}
