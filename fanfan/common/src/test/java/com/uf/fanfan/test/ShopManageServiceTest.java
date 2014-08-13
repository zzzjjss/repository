package com.uf.fanfan.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.uf.fanfan.service.ShopManageService;

public class ShopManageServiceTest {
	private static ShopManageService service;
	@BeforeClass
	public static void beforClass(){
		ApplicationContext  context=new ClassPathXmlApplicationContext("applicationContext.xml");
		service=(ShopManageService)context.getBean("shopManagerService");
	}
	@Test
	public void test() {
		
		service.findAllShops();
	}

}
