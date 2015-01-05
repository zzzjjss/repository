package com.uf.rest.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.uf.rest.dao.UserDao;
import com.uf.rest.entity.User;

@Component("userDao")
public class UserDaoImpl extends CommonDaoImpl<User> implements UserDao{
	public User findByName(String userName){
		String hql="select  u from User u  where u.name=?";
		List<User> users =(List<User>) getHibernateTemplate().find(hql,userName);
		if(users!=null&&users.size()>0){
			return users.get(0);
		}
		return null;
	}
}
