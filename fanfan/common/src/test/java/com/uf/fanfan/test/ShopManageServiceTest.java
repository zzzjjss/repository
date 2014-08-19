package com.uf.fanfan.test;

import java.security.MessageDigest;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.uf.fanfan.common.ShopStatus;
import com.uf.fanfan.entity.Shop;
import com.uf.fanfan.entity.ShopManager;
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
		Shop  shop=new Shop();
		shop.setAddress("longHua qinhu peace road 224");
		shop.setDescription("xiang cai ");
		shop.setName("xiao xiang ren");
		shop.setPhoneNum("0755-336879327");
		shop.setStatus(ShopStatus.NORMAL);
		service.addShop(shop);
		List<Shop> allShop=service.findAllShops();
		Assert.assertTrue(allShop.size()>0);
		for(Shop s:allShop){
			if(s.getName().equals(shop.getName())){
				service.invalidShop(s);
				Shop tmp=service.findShopById(s.getId());
				Assert.assertEquals(tmp.getStatus(), ShopStatus.OFFLINE);
				service.recoverShop(s);
				tmp=service.findShopById(s.getId());
				Assert.assertEquals(tmp.getStatus(), ShopStatus.NORMAL);
				
				ShopManager sm=new ShopManager();
				sm.setName("sm");
				sm.setPassword("123456");
				sm.setShop(tmp);
				service.addShopManager(sm);
				ShopManager sm_tmp=service.findShopManager(tmp.getId());
				Assert.assertNotNull(sm_tmp);
				try {
					service.resetShopManagerPassword(sm_tmp.getId(), "333333");
					String pwd=new String(MessageDigest.getInstance("MD5").digest("333333".getBytes()));
					sm_tmp=service.findShopManager(tmp.getId());
					Assert.assertEquals(sm_tmp.getPassword(), pwd);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				service.deleteShopManager(sm);
				
				service.deleteShop(tmp);
				tmp=service.findShopById(s.getId());
				Assert.assertNull(tmp);
			}
		}
		
	}

}
