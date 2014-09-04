package com.quick3;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class SynchronizeData {
	DataDao dao=new DataDao();
	
	public void synchData(Date from ,Date end){
		
		try {
			SimpleDateFormat  format=new SimpleDateFormat("yyyyMMdd");
			int fromInt=Integer.valueOf(format.format(from));
			int endInt=Integer.valueOf(format.format(end));
			HttpClientBuilder clientBuilder=HttpClients.custom().setDefaultSocketConfig(SocketConfig.custom().setSoTimeout(120*1000)
					.build()).setMaxConnTotal(1000).setMaxConnPerRoute(800);
			CloseableHttpClient client=clientBuilder.build();
			GregorianCalendar calendar=new GregorianCalendar(); 
			do{
				calendar.setTime(from);
				calendar.add(GregorianCalendar.DAY_OF_YEAR, 1);
				String current=format.format(calendar.getTime());
				System.out.println(current);
				HttpGet get=new HttpGet("http://caipiao.163.com/award/kuai3/?gameEn=kuai3&date="+current);
				CloseableHttpResponse responese=client.execute(get);
				String res=EntityUtils.toString(responese.getEntity());
				//System.out.println(res);
				parsePage(calendar.getTime(),res);
				from=calendar.getTime();
				fromInt=Integer.valueOf(format.format(from));
			}while(fromInt<=endInt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void parsePage(Date date,String pageHtml){
		StringTokenizer  token=new StringTokenizer(pageHtml, "\r\n");
		Pattern pattern=Pattern.compile(".*<td class=\"award-winNum\">.+</td>.*");
		while(token.hasMoreElements()){
			String line=token.nextToken();
			Matcher matcher=pattern.matcher(line);
			boolean ok=matcher.matches();
			
			if(ok){
				//System.out.println(line);
				int resultInt=countSum(line);
				//System.out.println(resultInt);
//				line=(String)token.nextElement();
//				int begin=line.indexOf(">");
//				int  end=line.indexOf("</td>");
//				String result=line.substring(begin+1, end);
//				if(!Integer.valueOf(result.trim()).equals(resultInt)){
//					System.out.println(result.trim()+"--->"+resultInt);
//				}
				OpenResult  openResult=new OpenResult();
				openResult.setOpendate(date);
				openResult.setResult(resultInt);
				dao.insertOpenResult(openResult);
				System.out.println(resultInt);
			}
		}
	}
	private int countSum(String line){
		int begin=line.indexOf(">");
		int  end=line.indexOf("</td>");
		String tmp=line.substring(begin+1, end);
		StringTokenizer  token=new StringTokenizer(tmp," ");
		Set<String> elsHash=new HashSet<String>(); 
		List<String> els=new ArrayList<String>(); 
		while(token.hasMoreElements()){
			String el=token.nextToken();
			els.add(el);
			elsHash.add(el);
		}
		if(elsHash.size()==1){
			if(els.contains("2")){
				return 32;
			}else if(els.contains("3")){
				return 33;
			}else if(els.contains("4")){
				return 34;
			}else if(els.contains("5")){
				return 35;
			}
		}
		int result=0;
		for(String e:els){
			int value=Integer.valueOf(e);
			result=result+value;
		}
		return result;
	}
	public static void main(String[] args) {
		SynchronizeData syn=new SynchronizeData();
		SimpleDateFormat  format=new SimpleDateFormat("yyyyMMdd");
		try {
			syn.synchData(format.parse("20121201"), format.parse("20140903"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
