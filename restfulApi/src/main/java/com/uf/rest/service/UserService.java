package com.uf.rest.service;

import com.uf.rest.entity.User;
import com.uf.rest.exception.UserExistException;

public interface UserService {
	public void registUser(User user)throws UserExistException;
	public boolean isUserExist(String userName)throws Exception;
	public User findUserByName(String userName)throws Exception;
	public void updateUserInfo(User user)throws Exception;
}
