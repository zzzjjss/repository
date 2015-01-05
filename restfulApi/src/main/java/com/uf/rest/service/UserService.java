package com.uf.rest.service;

import com.uf.rest.entity.User;

public interface UserService {
	public void registUser(User user)throws Exception;
	public boolean isUserExist(String userName)throws Exception;
	public User findUserByName(String userName)throws Exception;
	public void updateUserInfo(User user)throws Exception;
}
