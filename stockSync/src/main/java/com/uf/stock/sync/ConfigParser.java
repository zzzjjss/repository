package com.uf.stock.sync;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.uf.stock.bean.ConfigInfo;

public class ConfigParser {
	private static com.uf.stock.bean.ConfigInfo config;
	public static synchronized void parseConfigFile(File configFile)throws Exception{
		if(config==null){
			config=ConfigInfo.getInstance();
			Properties pro=new Properties();
			pro.load(new FileInputStream(configFile));
			config.setProxyAddress(pro.getProperty("proxyAddress"));
			config.setProxyPort(Integer.parseInt(pro.getProperty("proxyPort")));
			config.setIsUseProxy(Boolean.valueOf(pro.getProperty("isUseProxy")));
		}
	}
	
	public static synchronized ConfigInfo getConfigInfo() throws Exception{
		if(config==null)
			throw new Exception("the  configInfo  is  null,please  parseConfigFile first!  ");
		return config;
	}
}
