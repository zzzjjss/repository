package com.uf.stock.sync;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Iterator;
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
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.uf.stock.dao.CompanyInfoDao;
import com.uf.stock.util.HttpUnit;

public class GetStockTradeInfoTask implements Callable<Object>{
	private String stockId;
	private int year;
	private int jidu;
	
	public GetStockTradeInfoTask(String stockId,int year,int jidu)throws IllegalArgumentException{
		if(year<2000|| jidu>4 ||jidu<1){
			throw new IllegalArgumentException();
		}
		this.stockId=stockId;
		this.year=year;
		this.jidu=jidu;
	}
	
	public Object call() throws Exception {
		String url = "http://vip.stock.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/"+stockId+".phtml?year="+year+"&jidu="+jidu;
		HttpGet getMethod = new HttpGet(url);
		CloseableHttpResponse responese = null;
		CloseableHttpClient client=HttpUnit.createHttpClient();
		for(int i=0;i<3;i++){
			try {
				responese = client.execute(getMethod);
				HttpEntity entity = responese.getEntity();
				byte content[]=EntityUtils.toByteArray(entity);
//				System.out.println(new String(content));
				String html=new String(content);
				Pattern pattern = Pattern.compile("<table .*");
				Matcher m = pattern.matcher(html);
				while(m.find()){
					System.out.println(m.group());
				}
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
		return true;
		
		
		
		
//		Document doc = Jsoup.connect(url).get();
//		Elements divs = doc.select("#FundHoldSharesTable tr td div");
//		Iterator<Element> items=divs.iterator();
//		while(items.hasNext()){
//			Element element=items.next();
//			System.out.println(element.data());
//		}
		//return true;
		
	}

}
