package com.uf.fanfan.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.uf.fanfan.common.PageQueryResult;
import com.uf.fanfan.entity.Product;
import com.uf.fanfan.entity.Shop;
import com.uf.fanfan.service.ShopManagerService;

public class ShopServiceTest {
	static ApplicationContext  context=new ClassPathXmlApplicationContext("applicationContext.xml");
	@BeforeClass
	public static void beforClass(){
		
	}
	@Test
	public void test() {
		int shopId=0;
		ShopManagerService service=(ShopManagerService)context.getBean("shopManagerService");
		Shop shop=service.findShopById(shopId);
		System.out.println(shop.getName());
		PageQueryResult<Product> products=service.getPageProductsInShopByShopId(30, 1, shopId);
		System.out.println(products.getPageData().size());
	}
}
