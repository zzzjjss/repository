package com.uf.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uf.rest.dao.ShopDao;
import com.uf.rest.dao.ShopUserDao;
import com.uf.rest.entity.Shop;
import com.uf.rest.entity.ShopUser;
import com.uf.rest.exception.UserExistException;
import com.uf.rest.service.ShopService;

@Service("shopService")
public class ShopServiceImpl implements ShopService{
	@Autowired
	private ShopUserDao userDao;
	@Autowired
	private ShopDao shopDao;
	public ShopUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(ShopUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void registShopUser(ShopUser user) throws UserExistException {
		if(userDao.findByName(user.getUserName())!=null){
			throw new UserExistException("shopUser has  existed!");
		}
		userDao.insert(user);
	}

	@Override
	public boolean isShopUserExist(String userName) throws Exception {
		ShopUser user=userDao.findByName(userName);
		return user!=null;
	}

	@Override
	public ShopUser findShopUserByName(String userName) throws Exception {
		return userDao.findByName(userName);
	}

	@Override
	public void updateShopUserInfo(ShopUser user) throws Exception {
		userDao.update(user);
		
	}
	public void updateShop(Shop shop)throws Exception{
		shopDao.update(shop);
	}

}
