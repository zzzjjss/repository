package com.uf.rest.test;

import static org.junit.Assert.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONObject;

import org.junit.BeforeClass;
import org.junit.Test;

import com.uf.rest.bean.Constant;
import com.uf.rest.bean.request.UpdateOrderStateRequest;
import com.uf.rest.bean.request.UserLoginRequest;
import com.uf.rest.bean.response.DrawDetailResponse;
import com.uf.rest.bean.response.GetClientVersionResponse;
import com.uf.rest.bean.response.QueryGoodResponse;
import com.uf.rest.bean.response.QueryGoodsByClassResponse;
import com.uf.rest.bean.response.SellHomeResponse;
import com.uf.rest.bean.response.SellIncomeResponse;
import com.uf.rest.bean.response.SellOrderDealReponse;
import com.uf.rest.bean.response.SellOrderResponse;
import com.uf.rest.bean.response.SellVisitResponse;
import com.uf.rest.bean.response.ShopHomeResponse;
import com.uf.rest.bean.response.ShopIncomeResponse;
import com.uf.rest.bean.response.ShopOrderResponse;
import com.uf.rest.bean.response.UpdateOrderStateResponse;
import com.uf.rest.bean.response.UserLoginResponse;

public class ShopStatisticActionTest {
	public static String ip="localhost:8080";
	public static String token;
	@BeforeClass
	public static void setToken(){
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/shop/account/login");
		UserLoginRequest request=new UserLoginRequest();
		request.setName("helloShop");
		request.setPassword("helloShop");
		request.setP("1");
		UserLoginResponse response=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON),UserLoginResponse.class);
		if(response.getData()!=null){
			token=response.getData().getToken();
		}
		System.out.println(JSONObject.fromObject(response).toString());
	}
	@Test
	public void testShopHome() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/shop/home?p=1&token="+token);
		ShopHomeResponse response=target.request(MediaType.APPLICATION_JSON).get(ShopHomeResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	@Test
	public void testShopIncome() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/shop/income?p=1&token="+token);
		ShopIncomeResponse response=target.request(MediaType.APPLICATION_JSON).get(ShopIncomeResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	@Test
	public void testShopIncomeWithDraw() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/shop/income/withdraw?p=1&start=0&count=10&token="+token);
		DrawDetailResponse response=target.request(MediaType.APPLICATION_JSON).get(DrawDetailResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	
	
	@Test
	public void testShopOrderGet() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/shop/order/get?p=1&start=0&count=10&token="+token);
		ShopOrderResponse response=target.request(MediaType.APPLICATION_JSON).get(ShopOrderResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	@Test
	public void testShopOrderProcess() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/shop/order/process?p=1&start=0&count=10&token="+token);
		ShopOrderResponse response=target.request(MediaType.APPLICATION_JSON).get(ShopOrderResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	@Test
	public void testShopOrderFinish() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/shop/order/finish?p=1&start=0&count=10&token="+token);
		ShopOrderResponse response=target.request(MediaType.APPLICATION_JSON).get(ShopOrderResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	@Test
	public void testChangeOrderState() {
		Client client = ClientBuilder.newClient();
		UpdateOrderStateRequest request=new UpdateOrderStateRequest();
		request.setOrder_id(new Integer[]{2,3});
		request.setToken(token);
		request.setP(1);
		request.setState(Constant.ORDER_STATE_WAITSENT);
		WebTarget target = client.target("http://"+ip+"/cleaner/shop/order/update/state");
		UpdateOrderStateResponse response=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON), UpdateOrderStateResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	
	@Test
	public void testSellHome() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/shop/sell/home?p=1&start=2015-01-02&count=200&token="+token);
		SellHomeResponse response=target.request(MediaType.APPLICATION_JSON).get(SellHomeResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	@Test
	public void testSellIncome() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/shop/sell/income?p=1&start=2015-01-22&count=20&token="+token);
		SellIncomeResponse response=target.request(MediaType.APPLICATION_JSON).get(SellIncomeResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	@Test
	public void testSellOrder() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/shop/sell/order?p=1&start=0&count=20&token="+token);
		SellOrderResponse response=target.request(MediaType.APPLICATION_JSON).get(SellOrderResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	@Test
	public void testSellVisit() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/shop/sell/visit?p=1&start=0&count=2&token="+token);
		SellVisitResponse response=target.request(MediaType.APPLICATION_JSON).get(SellVisitResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	@Test
	public void testOrderDeal() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/shop/sell/order/deal?p=1&start=0&count=2&token="+token);
		SellOrderDealReponse response=target.request(MediaType.APPLICATION_JSON).get(SellOrderDealReponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	@Test
	public void testVersionLast() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/shop/version/last?p=1&token="+token);
		GetClientVersionResponse response=target.request(MediaType.APPLICATION_JSON).get(GetClientVersionResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
}
