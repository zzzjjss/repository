package com.uf.liveplay.websocket;

import java.util.HashSet;
import java.util.Set;

public class AllOnlineUsersMessage {
	private String messageType;
	private Set<String> userNames=new HashSet<String>();
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public Set<String> getUserNames() {
		return userNames;
	}
	public void setUserNames(Set<String> userNames) {
		this.userNames = userNames;
	}
	
}
