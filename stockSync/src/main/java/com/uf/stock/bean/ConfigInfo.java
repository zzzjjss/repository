package com.uf.stock.bean;

public class ConfigInfo {
	private boolean isUseProxy=false;
	private String proxyAddress;
	private int proxyPort;
	private static ConfigInfo instance=new ConfigInfo();
	private ConfigInfo(){
		
	}
	public static ConfigInfo  getInstance(){
		return instance;
	}
	public boolean isUseProxy() {
		return isUseProxy;
	}
	public void setIsUseProxy(boolean isUseProxy) {
		this.isUseProxy = isUseProxy;
	}
	public String getProxyAddress() {
		return proxyAddress;
	}
	public void setProxyAddress(String proxyAddress) {
		this.proxyAddress = proxyAddress;
	}
	public int getProxyPort() {
		return proxyPort;
	}
	public void setProxyPort(int proxyPort) {
		this.proxyPort = proxyPort;
	}
	
}
