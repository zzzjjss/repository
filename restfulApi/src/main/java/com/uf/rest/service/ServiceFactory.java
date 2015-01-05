package com.uf.rest.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceFactory {
	private static ApplicationContext  context;
	static {
		context=new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	 public  static <T>  T getService(Class<T> t){
		return context.getBean(t);
	} 
}
