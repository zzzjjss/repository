package com.uf.rest.service;

import com.uf.rest.entity.Shop;
import com.uf.rest.entity.ShopUser;
import com.uf.rest.exception.UserExistException;

public interface ShopService {
	public void registShopUser(ShopUser user)throws UserExistException;
	public boolean isShopUserExist(String userName)throws Exception;
	public ShopUser findShopUserByName(String userName)throws Exception;
	public void updateShopUserInfo(ShopUser user)throws Exception;
	public void updateShop(Shop shop)throws Exception;
}
