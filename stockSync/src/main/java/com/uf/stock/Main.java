package com.uf.stock;

import java.io.File;

import com.uf.stock.sync.ConfigParser;
import com.uf.stock.sync.Synchronizer;

public class Main {
	public static void main(String[] args) throws Exception {
		String basePath = System.getProperty("basePath");
		System.setProperty("log4j.configurationFile", basePath + "/conf/log4j2.xml");
		System.out.println(basePath);
		File configFile = new File(basePath + "/conf/config.conf");
		ConfigParser.parseConfigFile(configFile);
		Synchronizer syn = new Synchronizer();
		syn.syncTestRunResult();

	}

}
