package com.uf.liveplay.socketio.message;

public class ShutupMessage {
	private String messageType="shutup";
	
	
	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	
}
