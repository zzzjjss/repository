package com.uf.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uf.rest.dao.UserDao;
import com.uf.rest.entity.User;
import com.uf.rest.exception.UserExistException;
import com.uf.rest.service.UserService;

@Service("userService")
public class UserServiceImpl implements  UserService{
	@Autowired
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public void registUser(User user) throws UserExistException {
		if(userDao.findByName(user.getName())!=null){
			throw new UserExistException("user has  existed!");
		}
		userDao.insert(user);
	}

	@Override
	public boolean isUserExist(String userName) throws Exception {
		User user=userDao.findByName(userName);
		return user!=null;
	}
	@Override
	public User findUserByName(String userName)throws Exception{
		return userDao.findByName(userName);
		
	}
	public void updateUserInfo(User user)throws Exception{
		userDao.update(user);
	}
}
