package com.uf.liveplay.websocket;

public class UserOnlineMessage {
	private String messageType;
	private String userName;

	
	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
