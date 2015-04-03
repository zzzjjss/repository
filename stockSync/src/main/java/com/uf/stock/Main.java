package com.uf.stock;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.uf.stock.sync.ConfigParser;
import com.uf.stock.sync.Synchronizer;

public class Main {
	public static void main(String[] args) throws Exception {
		//String basePath = System.getProperty("basePath");
		//System.setProperty("log4j.configurationFile", basePath + "/conf/log4j2.xml");
	//	System.out.println(basePath);
		//File configFile = new File(basePath + "/conf/config.conf");
		//ConfigParser.parseConfigFile(configFile);
		Synchronizer syn = new Synchronizer();
		//syn.syncStockInfo();
		syn.syncStockTradeInfo();
//		String html="ababa<table id=\"FundHoldSharesTable\">hhelleo</table>fjdksfkskfjksdf";
//		Pattern pattern = Pattern.compile("<table id=\"FundHoldSharesTable\">.*</table>");
//		Matcher m = pattern.matcher(html);
//		while(m.find()){
//			System.out.println(m.group());
//		}
		
		
	}

}
