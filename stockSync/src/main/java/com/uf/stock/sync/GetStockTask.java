package com.uf.stock.sync;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.uf.stock.bean.Stock;
import com.uf.stock.dao.DaoFactory;
import com.uf.stock.dao.StockDao;
import com.uf.stock.util.HttpUnit;

public class GetStockTask implements Callable<Boolean>{
	
	public Boolean call() throws Exception {
		StockDao dao=DaoFactory.getDao(StockDao.class);
		for(int i=600000;i<604000;i++){
			String url = "http://finance.sina.com.cn/realstock/company/sh"+i+"/nc.shtml";
			HttpGet getMethod = new HttpGet(url);
			CloseableHttpResponse responese = null;
			CloseableHttpClient client=HttpUnit.createHttpClient();
			for(int j=0;j<3;j++){
				try {
					responese = client.execute(getMethod);
					HttpEntity entity = responese.getEntity();
					String response=EntityUtils.toString(entity,Charset.forName("gb2312"));
					int begin=response.indexOf("<h1 id=\"stockName\">");
					int end=response.indexOf("</h1>", begin);
					String content=response.substring(begin, end)+"</h1>";
					Document document=Jsoup.parse(content);
					String stockInfo=document.text();
					int beginIndex=stockInfo.indexOf("(");
					String name=stockInfo.substring(0,beginIndex);
					String code=stockInfo.substring(beginIndex+1, stockInfo.length()-4);
					System.out.println(name+code);
					//System.out.println(content);
					Stock  stock=new Stock();
					stock.setCode(code);
					stock.setName(name);
					dao.insert(stock);
					break;
				} catch (Exception e) {
				} finally {
					try {
						responese.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		
		return true;
		
	}

}
