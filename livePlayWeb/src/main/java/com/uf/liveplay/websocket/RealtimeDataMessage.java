package com.uf.liveplay.websocket;

public class RealtimeDataMessage {
	private String messageType="realtimeData";
	private String message;
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
