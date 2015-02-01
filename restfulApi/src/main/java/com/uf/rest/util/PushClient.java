package com.uf.rest.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;

public class PushClient {
	private Logger logger = LogManager.getLogger(PushClient.class);
	private String masterSecret;
	private String appKey;
	
	public String getMasterSecret() {
		return masterSecret;
	}


	public void setMasterSecret(String masterSecret) {
		this.masterSecret = masterSecret;
	}


	public String getAppKey() {
		return appKey;
	}


	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}


	public void pushMessageToAll(String target,String message){
		JPushClient jpushClient = new JPushClient(masterSecret,appKey, 3);
		PushPayload  payload=PushPayload.messageAll(message);
		PushPayload.newBuilder().setAudience(Audience.tag(target));
		try {
			PushResult result = jpushClient.sendPush(payload);
			logger.info("target:"+target+" ; push message :"+message);
		} catch (APIConnectionException e) {
			logger.error("Connection error, should retry later", e);
		} catch (APIRequestException e) {
			logger.error("Should review the error, and fix the request", e);
			logger.info("HTTP Status: " + e.getStatus());
			logger.info("Error Code: " + e.getErrorCode());
			logger.info("Error Message: " + e.getErrorMessage());
		}
	}
	public static void main(String[] args) {
		PushClient client=new PushClient();
		client.setAppKey("b40d3fce9bcc57623e973d11");
		client.setMasterSecret("f8c0836928912a95756d534d");
		client.pushMessageToAll("2","testMessage2");
	}
}
