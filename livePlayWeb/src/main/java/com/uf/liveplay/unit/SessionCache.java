package com.uf.liveplay.unit;

import java.util.concurrent.ConcurrentHashMap;

import com.uf.liveplay.entity.User;

public class SessionCache {
	private static ConcurrentHashMap<String, User> sessionStore=new ConcurrentHashMap<String, User>();
	private static ConcurrentHashMap<Integer, String> userId_Session=new ConcurrentHashMap<Integer, String>();
	public static void addUser(String sessionId,User user){
		sessionStore.put(sessionId, user);
		if(user!=null&&user.getId()!=null){
			userId_Session.put(user.getId(), sessionId);
		}
		
	}
	
	public  static void removeUser(String sessionId){
		User user=sessionStore.remove(sessionId);
		if(user!=null&&user.getId()!=null){
			userId_Session.remove(user.getId());
		}
	}
	
	public  static User findUser(String sessionId){
		if(sessionId!=null){
			return sessionStore.get(sessionId);
		}
		return null;
		
	}
	
	public static String findUserSessionIdByUserId(Integer userId){
		return userId_Session.get(userId);
	}
}
