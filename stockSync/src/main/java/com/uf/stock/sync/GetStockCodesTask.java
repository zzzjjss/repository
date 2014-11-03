package com.uf.stock.sync;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.uf.stock.dao.CompanyInfoDao;
import com.uf.stock.util.HttpUnit;

public class GetStockCodesTask implements Callable<Object>{
	private CloseableHttpClient client;
	private Logger logger = LogManager.getLogger(GetStockCodesTask.class);
	public GetStockCodesTask(CloseableHttpClient client){
		this.client=client;
	}
	public Object call() throws Exception {
		String url = "http://quote.eastmoney.com/stocklist.html";
		HttpGet getMethod=new HttpGet(url);
		CloseableHttpResponse responese=null;
		try {
			responese = client.execute(getMethod);
			HttpEntity entity = responese.getEntity();
			BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
			String line = null;
			int i=0;
			while ((line = br.readLine()) != null) {
				i++;
				if(i>=251&&i<=6673){
					Pattern pattern=Pattern.compile(".*<li><a target=[\"]_blank[\"] href=[\"]http://quote[.]eastmoney[.]com.*</a></li>.*");
					Matcher match=pattern.matcher(line);
					if(match.find()){
						int end=line.indexOf(".html");
						String code=line.substring(68, end);
						System.out.println(code);
						String nameAndCode=line.substring(83, line.length()-9);
						int a=nameAndCode.indexOf("(");
						String name=nameAndCode.substring(0, a);
						CompanyInfoDao  dao=new CompanyInfoDao();
						//dao.addStockCode(code);
					}
					
				}
			}
		} catch (Exception e) {
			logger.error("GetStockCodesTask from webservice error:", e);
		}finally{
			try {
				responese.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
public static void main(String[] args) {
	GetStockCodesTask task=new GetStockCodesTask(HttpUnit.createHttpClient());
	try {
		task.call();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
