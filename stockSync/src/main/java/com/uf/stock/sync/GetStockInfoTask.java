package com.uf.stock.sync;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetStockInfoTask implements Callable<Object> {
	private Logger logger = LogManager.getLogger(GetStockInfoTask.class);
	private CloseableHttpClient client;
	private String  stockCode;
	public GetStockInfoTask(String stockCode, CloseableHttpClient client) {
		this.stockCode=stockCode;
		this.client=client;
	}

	public Object call() {
		String url = "http://cj.gw.com.cn/news/stock/"+stockCode+"/gsjs.shtml";
		HttpGet getMethod=new HttpGet(url);
		CloseableHttpResponse responese=null;
		try {
			responese = client.execute(getMethod);
			HttpEntity entity = responese.getEntity();
			BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			logger.error("GetStockInfo from webservice error:["+stockCode+ "] ", e);
		}finally{
			try {
				responese.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
}
