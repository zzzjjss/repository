package com.uf.fanfan.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LoginServiceTest {
	
	@BeforeClass
	public static void preClass(){
		ApplicationContext  context=new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	@Test
	public void test() {
	
	}
	
	@Test
	public void test2() {
	
	}
}
