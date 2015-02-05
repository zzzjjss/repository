package com.uf.liveplay.service;

import com.uf.liveplay.entity.User;

public interface UserService {
	public void registNewUser(User user)throws Exception;
	
	public boolean login(String userName,String password);
	
	public User findUserByName(String userName);
}
