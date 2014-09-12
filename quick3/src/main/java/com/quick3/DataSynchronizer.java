package com.quick3;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class DataSynchronizer {
	private DataDao dao=new DataDao();
	private CloseableHttpClient client;
	private SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	public DataSynchronizer(){
		HttpClientBuilder clientBuilder = HttpClients.custom().setDefaultSocketConfig(SocketConfig.custom().setSoTimeout(120 * 1000).build()).setMaxConnTotal(1000).setMaxConnPerRoute(800);
		client = clientBuilder.build();
		
	}
	public List<OpenResult> synchOneDateSeqData(Date date)throws Exception{
		String html=synchOneDateData(date);
		return parsePage(date, html);
		
	}
	
	public String synchOneDateData(Date date) throws Exception{
		String current = format.format(date);
		System.out.println("syn "+current+" data");
		HttpGet get = new HttpGet("http://caipiao.163.com/award/kuai3/?gameEn=kuai3&date=" + current);
		CloseableHttpResponse responese = client.execute(get);
		return EntityUtils.toString(responese.getEntity());
	}
	public void synchAndSaveData(Date from, Date end) {
		int fromInt = Integer.valueOf(format.format(from));
		int endInt = Integer.valueOf(format.format(end));
		GregorianCalendar calendar = new GregorianCalendar();
		do {
			try {
				calendar.setTime(from);
				String res=synchOneDateData(from);
				// System.out.println(res);
				List<OpenResult> openResults = parsePage(from, res);
				for (OpenResult result : openResults) {
					dao.insertOpenResult(result);
				}
				calendar.add(GregorianCalendar.DAY_OF_YEAR, 1);
				from=calendar.getTime();
				fromInt = Integer.valueOf(format.format(from));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (fromInt <= endInt);
	}

	public List<OpenResult> parsePage(Date date, String pageHtml) {
		List<OpenResult> result=new ArrayList<OpenResult>();
		StringTokenizer token = new StringTokenizer(pageHtml, "\r\n");
		Pattern pattern = Pattern.compile(".*<td.+class=\"start\".+data-win-number=.+data-period=.+>.+</td>.*");
		while (token.hasMoreElements()) {
			String line = token.nextToken();
			Matcher matcher = pattern.matcher(line);
			boolean ok = matcher.matches();
			if (ok) {
				int dateIndex = parseDateIndex(line);
				line = token.nextToken();
				int resultInt = countSum(line);

				// System.out.println(resultInt);
				// line=(String)token.nextElement();
				// int begin=line.indexOf(">");
				// int end=line.indexOf("</td>");
				// String result=line.substring(begin+1, end);
				// if(!Integer.valueOf(result.trim()).equals(resultInt)){
				// System.out.println(result.trim()+"--->"+resultInt);
				// }

				OpenResult openResult = new OpenResult();
				openResult.setOpendate(date);
				openResult.setDateIndex(dateIndex);
				openResult.setResult(resultInt);
				result.add(openResult);
			}
		}
		return result;
	}
	private int parseDateIndex(String line){
		int begin=line.indexOf(">");
		int  end=line.indexOf("</td>");
		String dateIndex=line.substring(begin+1, end).trim();
		return Integer.valueOf(dateIndex);
	}
	private int countSum(String line){
		int begin=line.indexOf(">");
		int  end=line.indexOf("</td>");
		String tmp=line.substring(begin+1, end);
		StringTokenizer  token=new StringTokenizer(tmp," ");
		//Set<String> elsHash=new HashSet<String>(); 
		List<String> els=new ArrayList<String>(); 
		while(token.hasMoreElements()){
			String el=token.nextToken();
			els.add(el);
			//elsHash.add(el);
		}
//		if(elsHash.size()==1){
//			if(els.contains("2")){
//				return 32;
//			}else if(els.contains("3")){
//				return 33;
//			}else if(els.contains("4")){
//				return 34;
//			}else if(els.contains("5")){
//				return 35;
//			}
//		}
		int result=0;
		for(String e:els){
			int value=Integer.valueOf(e);
			result=result+value;
		}
		return result;
	}
}
