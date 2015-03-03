package com.uf.liveplay.service;

import java.util.Map;

import com.uf.liveplay.entity.User;

public interface UserService {
	public void registNewUser(User user)throws Exception;
	
	public boolean login(String userName,String password);
	
	public User findUserByName(String userName);
	public User findUserById(Integer userId);
	public void saveUserInfo(User user);
	public void vote(String voteItem);
	
	public Map<String, String> statisticVote();
}
