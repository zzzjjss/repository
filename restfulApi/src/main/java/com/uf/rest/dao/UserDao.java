package com.uf.rest.dao;

import com.uf.rest.entity.User;


public interface UserDao extends CommonDao<User>{
	public User findByName(String userName);
}
