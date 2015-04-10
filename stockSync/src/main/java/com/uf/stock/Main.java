package com.uf.stock;

import com.uf.stock.alarm.MySchedule;

public class Main {
	public static void main(String[] args) throws Exception {
		//String basePath = System.getProperty("basePath");
		//System.setProperty("log4j.configurationFile", basePath + "/conf/log4j2.xml");
	//	System.out.println(basePath);
		//File configFile = new File(basePath + "/conf/config.conf");
		//ConfigParser.parseConfigFile(configFile);
		//Synchronizer syn = new Synchronizer();
		//syn.synStock();
		//syn.syncStockTradeInfo();
		
		//Analyser anlyse=new Analyser();
		//anlyse.highestAnalyse();
		MySchedule sch=new MySchedule();
		sch.startSchedule();
	}

}
