package com.uf.stock.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class DaoFactory {
	private static ApplicationContext context;
	static{
		context=new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	public static <T extends CommonDao> T getDao(Class<T> dao) {
		return context.getBean(dao);
	}
	
	public static <T extends CommonDao> T getDao(String daoName) {
		return (T) context.getBean(daoName);
	}
}
