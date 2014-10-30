package com.uf.stock.util;

import org.apache.http.HttpHost;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import com.uf.stock.bean.ConfigInfo;

public class HttpUnit {
	public static  CloseableHttpClient createHttpClient(){
		HttpClientBuilder clientBuilder=HttpClients.custom().setDefaultSocketConfig(SocketConfig.custom().setSoTimeout(120*1000)
				.build()).setMaxConnTotal(1000).setMaxConnPerRoute(800);
		if (ConfigInfo.getInstance().isUseProxy()) {
			clientBuilder.setProxy(new HttpHost(ConfigInfo.getInstance().getProxyAddress(), ConfigInfo.getInstance().getProxyPort()));
		}
		return clientBuilder.build();
	}
	public static CloseableHttpClient createNotimeoutHttpClient(){
		HttpClientBuilder clientBuilder=HttpClients.custom();
		if (ConfigInfo.getInstance().isUseProxy()) {
			clientBuilder.setProxy(new HttpHost(ConfigInfo.getInstance().getProxyAddress(), ConfigInfo.getInstance().getProxyPort()));
		}
		return clientBuilder.build();
	}
	
}
