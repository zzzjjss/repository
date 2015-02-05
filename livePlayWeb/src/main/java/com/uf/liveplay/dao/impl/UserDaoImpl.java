package com.uf.liveplay.dao.impl;

import org.springframework.stereotype.Component;

import com.uf.liveplay.dao.UserDao;
import com.uf.liveplay.entity.User;
@Component("userDao")
public class UserDaoImpl extends CommonDaoImpl<User> implements UserDao{
	
}
