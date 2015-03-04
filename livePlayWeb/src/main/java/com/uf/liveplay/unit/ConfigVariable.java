package com.uf.liveplay.unit;

public class ConfigVariable {
	private String rtmpServerAddress;
	private String webSocketAddress;
	private int unknowUserListenTimeMinute;
	private int unknowUserListenIntervalHour;
	
	
	
	public int getUnknowUserListenIntervalHour() {
		return unknowUserListenIntervalHour;
	}
	public void setUnknowUserListenIntervalHour(int unknowUserListenIntervalHour) {
		this.unknowUserListenIntervalHour = unknowUserListenIntervalHour;
	}
	public int getUnknowUserListenTimeMinute() {
		return unknowUserListenTimeMinute;
	}
	public void setUnknowUserListenTimeMinute(int unknowUserListenTimeMinute) {
		this.unknowUserListenTimeMinute = unknowUserListenTimeMinute;
	}
	public String getRtmpServerAddress() {
		return rtmpServerAddress;
	}
	public void setRtmpServerAddress(String rtmpServerAddress) {
		this.rtmpServerAddress = rtmpServerAddress;
	}
	public String getWebSocketAddress() {
		return webSocketAddress;
	}
	public void setWebSocketAddress(String webSocketAddress) {
		this.webSocketAddress = webSocketAddress;
	}
	
	
	



}
