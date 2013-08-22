package com.uf.fanfan.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserServiceTest {
	static ApplicationContext  context=new ClassPathXmlApplicationContext("applicationContext.xml");
	@BeforeClass
	public static void beforClass(){
		
	}
	@Test
	public void test() {
	}
}
