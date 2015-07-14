package com.uf.broadcast.dao.impl;

import org.springframework.stereotype.Component;

import com.uf.broadcast.dao.UserDao;
import com.uf.broadcast.entity.User;
@Component("userDao")
public class UserDaoImpl extends CommonDaoImpl<User> implements UserDao{
}
