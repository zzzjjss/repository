package com.uf.stock.alarm;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.Callable;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.uf.stock.bean.AlarmStock;
import com.uf.stock.util.HttpUnit;

public class GetCurrentPriceTask implements Callable<Float>{
	private AlarmStock stock;
	public GetCurrentPriceTask(AlarmStock stock){
		this.stock=stock;
	}
	public Float call() {
		String stockCode="";
		if(stock.getStockCode().startsWith("60")){
			stockCode="sh"+stock.getStockCode();
		}else if(stock.getStockCode().startsWith("00")){
			stockCode="sz"+stock.getStockCode();
		}else{
			return null;
		}
		String url="http://hq.sinajs.cn/?list="+stockCode;
		HttpGet getMethod = new HttpGet(url);
		CloseableHttpResponse responese = null;
		CloseableHttpClient client=HttpUnit.createHttpClient();
		for(int j=0;j<3;j++){
			try {
				responese = client.execute(getMethod);
				HttpEntity entity = responese.getEntity();
				String response=EntityUtils.toString(entity,Charset.forName("gb2312"));
				String infos[]=response.split(",");
				if(infos!=null&&infos.length>4){
					
					System.out.println(stock.getStockCode()+"--->"+infos[3]);
					if(infos[3]!=null){
						return Float.parseFloat(infos[3]);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					responese.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
		
	}
	
}
