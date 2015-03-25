package com.uf.liveplay.service;

import java.util.List;
import java.util.Map;

import com.uf.liveplay.entity.PublicMessage;
import com.uf.liveplay.entity.Servicer;
import com.uf.liveplay.entity.Teacher;
import com.uf.liveplay.entity.User;

public interface UserService {
	public void registNewUser(User user)throws Exception;
	
	public boolean login(String userName,String password);
	
	public User findUserByName(String userName);
	public User findUserById(Integer userId);
	public void resetUserPassword(Integer userId,String password);
	public void saveUserInfo(User user);
	public void vote(String voteItem);
	
	public Map<String, String> statisticVote();
	public List<User> findAllCommonUser();
	
	public boolean servicerLogin(String userName,String password);
	public void saveServicerInfo(Servicer servicer);
	public Servicer findServicerByName(String userName);
	public Servicer findServicerById(Integer userId);
	public void deleteUserById(Integer userId);
	
	public boolean teacherLogin(String userName,String password);
	public void saveTeacherInfo(Teacher servicer);
	public Teacher findTeacherByName(String userName);
	public Teacher findTeacherById(Integer userId);
	
	public void savePublicMessage(PublicMessage message);
	public PublicMessage findMessageByKey(String key);
}
