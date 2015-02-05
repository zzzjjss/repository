package com.uf.liveplay.unit;

import java.util.concurrent.ConcurrentHashMap;

import com.uf.liveplay.entity.User;

public class SessionCache {
	private static ConcurrentHashMap<String, User> sessionStore=new ConcurrentHashMap<String, User>();
	public static void addUser(String sessionId,User user){
		sessionStore.put(sessionId, user);
	}
	
	public  static void removeUser(String sessionId){
		sessionStore.remove(sessionId);
	}
	
	public  static User findUser(String sessionId){
		if(sessionId!=null){
			return sessionStore.get(sessionId);
		}
		return null;
		
	}
}
