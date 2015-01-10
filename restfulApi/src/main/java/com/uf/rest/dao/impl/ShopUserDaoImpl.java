package com.uf.rest.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.uf.rest.dao.ShopUserDao;
import com.uf.rest.entity.ShopUser;
@Component("shopUserDao")
public class ShopUserDaoImpl extends CommonDaoImpl<ShopUser> implements ShopUserDao{
	public ShopUser findByName(String userName){
		String hql="select  u from ShopUser u  where u.userName=?";
		List<ShopUser> users =(List<ShopUser>) getHibernateTemplate().find(hql,userName);
		if(users!=null&&users.size()>0){
			return users.get(0);
		}
		return null;
	}
}
