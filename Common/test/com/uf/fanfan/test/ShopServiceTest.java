package com.uf.fanfan.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.uf.fanfan.entity.Shop;
import com.uf.fanfan.service.ShopManagerService;

public class ShopServiceTest {
	static ApplicationContext  context=new ClassPathXmlApplicationContext("applicationContext.xml");
	@BeforeClass
	public static void beforClass(){
		
	}
	@Test
	public void test() {
		ShopManagerService service=(ShopManagerService)context.getBean("shopManagerService");
		Shop shop=service.findShopById(0);
		System.out.println(shop.getName());
		
	}
}
