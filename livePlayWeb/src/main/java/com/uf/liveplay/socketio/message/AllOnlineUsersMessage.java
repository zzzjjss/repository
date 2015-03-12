package com.uf.liveplay.socketio.message;

import java.util.HashSet;
import java.util.Set;

public class AllOnlineUsersMessage {
	private Set<String> userNames=new HashSet<String>();
	public Set<String> getUserNames() {
		return userNames;
	}
	public void setUserNames(Set<String> userNames) {
		this.userNames = userNames;
	}
	
}
