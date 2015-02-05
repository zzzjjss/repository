package com.uf.liveplay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uf.liveplay.dao.UserDao;
import com.uf.liveplay.entity.User;
import com.uf.liveplay.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void registNewUser(User user)throws Exception{
		userDao.insert(user);
	}
	public boolean login(String userName,String password){
		List<User> users=userDao.findByHql("select u from User u where u.name=? and u.password=?", userName,password);
		if(users!=null&&users.size()>0){
			return true;
		}else{
			return false;
		}
	}
	public User findUserByName(String userName){
		List<User> users=userDao.findByHql("select u from User u where u.name=?", userName);
		if(users!=null&&users.size()>0){
			return users.get(0);
		}else{
			return null;
		}
	}
}
