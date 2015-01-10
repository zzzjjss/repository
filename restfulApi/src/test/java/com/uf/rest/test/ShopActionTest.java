package com.uf.rest.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.junit.Test;

import com.uf.rest.bean.TestBean;

public class ShopActionTest {

	@Test
	public void test() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/restfulApi/restful/test/jsonTest");
		TestBean bean=new TestBean(); 
		bean.setA("aaaa");
		bean.setB("bbbb");
		bean.setImg("dddddddddddddddddddd".getBytes());
		target.request(MediaType.APPLICATION_JSON).post(Entity.entity(bean, MediaType.APPLICATION_JSON));
	}

}
