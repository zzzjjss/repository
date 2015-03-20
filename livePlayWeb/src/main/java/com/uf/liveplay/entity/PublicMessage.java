package com.uf.liveplay.entity;

public class PublicMessage {
	public final static String PUBLIC_NEWS="public_news";
	public final static String PUBLIC_NOTICE="public_notice";
	
	private Integer id;
	private String messageType;
	private String messageContent;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	
}
