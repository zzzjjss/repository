package com.uf.liveplay.websocket;

import java.util.ArrayList;
import java.util.List;

public class AllOnlineUsers {
	private String messageType;
	private List<String> userNames=new ArrayList<String>();
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public List<String> getUserNames() {
		return userNames;
	}
	public void setUserNames(List<String> userNames) {
		this.userNames = userNames;
	}
	
}
