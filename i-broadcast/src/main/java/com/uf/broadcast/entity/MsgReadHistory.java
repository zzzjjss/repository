package com.uf.broadcast.entity;

import java.util.Date;

public class MsgReadHistory {
	private Long id;
	private User readerUser;
	private Message message;
	private Date readTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getReaderUser() {
		return readerUser;
	}
	public void setReaderUser(User readerUser) {
		this.readerUser = readerUser;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public Date getReadTime() {
		return readTime;
	}
	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}
	
}
