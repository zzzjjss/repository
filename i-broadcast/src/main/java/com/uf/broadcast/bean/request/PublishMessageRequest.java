package com.uf.broadcast.bean.request;

public class PublishMessageRequest {
	private String session;
	private String content;
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
