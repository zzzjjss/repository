package com.uf.rest.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONObject;

import org.junit.BeforeClass;
import org.junit.Test;

import com.uf.rest.bean.request.AddAddressRequest;
import com.uf.rest.bean.request.AddBankCardRequest;
import com.uf.rest.bean.request.AddCustomCommentRequest;
import com.uf.rest.bean.request.CreateOrderRequest;
import com.uf.rest.bean.request.CustomHomeRequest;
import com.uf.rest.bean.request.DeleteAddressRequest;
import com.uf.rest.bean.request.DeleteBankCardRequest;
import com.uf.rest.bean.request.RemoveOrderRequest;
import com.uf.rest.bean.request.GoodPriceRequest;
import com.uf.rest.bean.request.RegistUserRequest;
import com.uf.rest.bean.request.RequestGood;
import com.uf.rest.bean.request.UpdateAddressRequest;
import com.uf.rest.bean.request.UpdateBankCardRequest;
import com.uf.rest.bean.request.UpdateOrderRequest;
import com.uf.rest.bean.request.UserChangePasswordRequest;
import com.uf.rest.bean.request.UserLoginRequest;
import com.uf.rest.bean.request.UserLogoutRequest;
import com.uf.rest.bean.response.AddAddressResponse;
import com.uf.rest.bean.response.AddBankCardResponse;
import com.uf.rest.bean.response.AddCustomCommentResponse;
import com.uf.rest.bean.response.CreateOrderResponse;
import com.uf.rest.bean.response.CustomHomeResponse;
import com.uf.rest.bean.response.CustomProcessOrderCountResponse;
import com.uf.rest.bean.response.DeleteAddressResponse;
import com.uf.rest.bean.response.DeleteBankCardResponse;
import com.uf.rest.bean.response.GetClientVersionResponse;
import com.uf.rest.bean.response.QueryBankCardResponse;
import com.uf.rest.bean.response.QueryCommentResponse;
import com.uf.rest.bean.response.QueryUserAddressResponse;
import com.uf.rest.bean.response.RemoveOrderResponse;
import com.uf.rest.bean.response.GetGoodsResponse;
import com.uf.rest.bean.response.GoodPriceResponse;
import com.uf.rest.bean.response.IsUserExistResponse;
import com.uf.rest.bean.response.QueryOrderResponse;
import com.uf.rest.bean.response.UpdateAddressResponse;
import com.uf.rest.bean.response.UpdateBankCardResponse;
import com.uf.rest.bean.response.UpdateOrderResponse;
import com.uf.rest.bean.response.UserChangePasswordResponse;
import com.uf.rest.bean.response.UserLoginResponse;
import com.uf.rest.bean.response.UserLogoutResponse;
import com.uf.rest.bean.response.UserRegistResponse;

public class CustomActionTest {
	private static String token;
	public static String ip="localhost:8080";
	@BeforeClass
	public static void setToken(){
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/custom/account/login");
		UserLoginRequest request=new UserLoginRequest();
		request.setName("hello");
		request.setPassword("hello");
		request.setP("1");
		UserLoginResponse response=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON),UserLoginResponse.class);
		if(response.getData()!=null){
			token=response.getData().getToken();
		}else{
			WebTarget target2 = client.target("http://"+ip+"/cleaner/custom/account/register");
			RegistUserRequest request2 =new RegistUserRequest(); 
			request2.setName("hello");
			request2.setP(1);
			request2.setPassword("hello");
			request2.setType(0);
			UserRegistResponse response2=target2.request(MediaType.APPLICATION_JSON).post(Entity.entity(request2, MediaType.APPLICATION_JSON),UserRegistResponse.class);
			System.out.println(JSONObject.fromObject(response2).toString());
		}
		System.out.println(JSONObject.fromObject(response).toString());
	}
	
	@Test
	public void testRegist() throws Exception{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/custom/account/register");
		RegistUserRequest request =new RegistUserRequest(); 
		request.setName("hello");
		request.setP(1);
		request.setPassword("hello");
		request.setType(0);
		UserRegistResponse response=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON),UserRegistResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	@Test
	public void testIsUserExist() throws Exception{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/custom/account/existed?name=hello");
		IsUserExistResponse response=target.request(MediaType.APPLICATION_JSON).get(IsUserExistResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
		
	}
	
	@Test
	public void testAuthenticateAndChangePsdAndLogout() throws Exception{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/custom/account/login");
		UserLoginRequest request=new UserLoginRequest();
		request.setName("hello");
		request.setPassword("hello");
		request.setP("1");
		UserLoginResponse response=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON),UserLoginResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
		
		Client client1 = ClientBuilder.newClient();
		WebTarget target1 = client1.target("http://"+ip+"/custom/account/password");
		UserChangePasswordRequest request1=new UserChangePasswordRequest();
		request1.setToken(response.getData().getToken());
		request1.setNew_psd("hello");
		request1.setOld_psd("hello");
		UserChangePasswordResponse response1=target1.request(MediaType.APPLICATION_JSON).post(Entity.entity(request1, MediaType.APPLICATION_JSON),UserChangePasswordResponse.class);
		System.out.println(JSONObject.fromObject(response1).toString());
		
		Client client2 = ClientBuilder.newClient();
		WebTarget target2 = client2.target("http://"+ip+"/custom/account/logout");
		UserLogoutRequest request3=new UserLogoutRequest();
		request3.setToken(response.getData().getToken());
		UserLogoutResponse response3=target2.request(MediaType.APPLICATION_JSON).post(Entity.entity(request3, MediaType.APPLICATION_JSON),UserLogoutResponse.class);
		System.out.println(JSONObject.fromObject(response3).toString());
		
	}
	
	
	@Test
	public void testCustomHome() throws Exception{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/custom/home?p=0&latitude=222.333&longitude=333.333&start=0&count=10&token="+token);
		
		CustomHomeResponse response=target.request(MediaType.APPLICATION_JSON).get(CustomHomeResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
		
		WebTarget target1 = client.target("http://"+ip+"/cleaner/custom/order/process?token="+token);
		CustomProcessOrderCountResponse response1=target1.request(MediaType.APPLICATION_JSON).get(CustomProcessOrderCountResponse.class);
		System.out.println(JSONObject.fromObject(response1).toString());
	}
	@Test
	public void testGoods() throws Exception{
		Client client = ClientBuilder.newClient();
		WebTarget target1 = client.target("http://"+ip+"/cleaner/custom/goods?token="+token);
		GetGoodsResponse response1=target1.request(MediaType.APPLICATION_JSON).get(GetGoodsResponse.class);
		System.out.println(JSONObject.fromObject(response1).toString());
	}
	@Test
	public void testGoodsPrice() throws Exception{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/custom/shop/goods/price");
		GoodPriceRequest request=new GoodPriceRequest();
		request.setP(0);
		request.setGood(new Integer[]{1,2});
		request.setCount(10);
		request.setStart(0);
		request.setToken(token);
		GoodPriceResponse response=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON),GoodPriceResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	
	
	@Test
	public void testCreateOrder()throws Exception{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/custom/order/add");
		CreateOrderRequest request=new CreateOrderRequest();
		request.setDeliver_address_id(1);
		request.setP(1);
		request.setPayment(1);
		request.setPick_address_id(2);
		request.setShop_id(1);
		request.setToken(token);
		RequestGood good=new RequestGood();
		good.setCount(3);
		good.setId(1);
		good.setName("yifu");
		good.setPrice(22.2f);
		RequestGood goods[]=new RequestGood[]{good};

		request.setGood(goods);
		CreateOrderResponse response=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON),CreateOrderResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	
	@Test
	public void testUpdateOrder()throws Exception{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/custom/order/update");
		UpdateOrderRequest request=new UpdateOrderRequest();
		request.setDeliver_address_id(2);
		request.setOrder_id(1);
		request.setP(1);
		request.setPayment(2);
		request.setPick_address_id(1);
		request.setState(3);
		List<RequestGood> goods=new ArrayList<RequestGood>();
		RequestGood good=new RequestGood();
		good.setCount(5);
		good.setId(2);
		goods.add(good);
		request.setGood((RequestGood[])goods.toArray());
		request.setToken(token);
		UpdateOrderResponse response=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON),UpdateOrderResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	@Test
	public void testQueryOrder()throws Exception{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/custom/order");
		QueryOrderResponse response=target.request(MediaType.APPLICATION_JSON).get(QueryOrderResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	@Test
	public void testDeleteOrder()throws Exception{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/custom/order/delete");
		RemoveOrderRequest request=new RemoveOrderRequest();
		request.setOrder_id(1);
		request.setToken(token);
		RemoveOrderResponse response=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON),RemoveOrderResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	
	@Test
	public void testAddAddress()throws Exception{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/custom/address/add");
		AddAddressRequest request= new AddAddressRequest();
		request.setAddress("addd");
		request.setCity("city");
		request.setControy("controy");
		request.setDistrict("qi");
		request.setName("jason");
		request.setP(1);
		request.setPhone("335555");
		request.setPost("3333");
		request.setProvince("pro");
		request.setToken(token);
		AddAddressResponse response=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON),AddAddressResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
		
		
	}
	@Test
	public void testUpdateAddress()throws Exception{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/custom/address/update");
		UpdateAddressRequest request= new UpdateAddressRequest();
		request.setAddress("addd");
		request.setCity("city");
		request.setControy("controy");
		request.setDistrict("qi");
		request.setName("jason");
		request.setP(1);
		request.setPhone("5555555");
		request.setPost("3333");
		request.setProvince("pro");
		request.setToken(token);
		request.setAddress_id(3);
		UpdateAddressResponse response=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON),UpdateAddressResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	@Test
	public void testQueryAddress()throws Exception{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/custom/address?start=0&count=10&token="+token);
		QueryUserAddressResponse response=target.request(MediaType.APPLICATION_JSON).get(QueryUserAddressResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	@Test
	public void testRemoveAddress()throws Exception{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/custom/address/remove");
		DeleteAddressRequest request=new DeleteAddressRequest();
		request.setAddress_id(3);
		request.setToken(token);
		request.setP(1);
		DeleteAddressResponse response=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON),DeleteAddressResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
		
	}
	
	@Test
	public void testAddCard()throws Exception{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/custom/card/add");
		AddBankCardRequest request= new AddBankCardRequest();
		request.setBank("acbc");
		request.setCard("33455555");
		request.setName("jason");
		request.setP(1);
		request.setToken(token);
		AddBankCardResponse response=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON),AddBankCardResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	@Test
	public void testUpdateBankCard()throws Exception{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/custom/card/update");
		UpdateBankCardRequest request= new UpdateBankCardRequest();
		request.setBank("acbc");
		request.setCard("33455555");
		request.setName("jason zhang");
		request.setP(1);
		request.setCard_id(1);
		request.setToken(token);
		UpdateBankCardResponse response=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON),UpdateBankCardResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	@Test
	public void testQueryCard()throws Exception{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/custom/card?start=0&count=10&token="+token);
		QueryBankCardResponse response=target.request(MediaType.APPLICATION_JSON).get(QueryBankCardResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	
	@Test
	public void testComment()throws Exception{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/custom/comment/add");
		AddCustomCommentRequest request=new AddCustomCommentRequest();
		request.setComment("commentss");
		request.setP(1);
		request.setToken(token);
		AddCustomCommentResponse response=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON),AddCustomCommentResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
		WebTarget target2 = client.target("http://"+ip+"/cleaner/custom/comment?start=0&count=10&token="+token);
		QueryCommentResponse response2=target2.request(MediaType.APPLICATION_JSON).get(QueryCommentResponse.class);
		System.out.println(JSONObject.fromObject(response2).toString());
	}
	@Test
	public void testClientVersion(){
		Client client = ClientBuilder.newClient();
		WebTarget target2 = client.target("http://"+ip+"/cleaner/custom/version/last?p=1&token="+token);
		GetClientVersionResponse response2=target2.request(MediaType.APPLICATION_JSON).get(GetClientVersionResponse.class);
		System.out.println(JSONObject.fromObject(response2).toString());
	}
	@Test
	public void testRemoveBankCard()throws Exception{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/custom/card/remove");
		DeleteBankCardRequest request=new DeleteBankCardRequest();
		request.setCard_id(1);
		request.setToken(token);
		request.setP(1);
		DeleteBankCardResponse response=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON),DeleteBankCardResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	
	
}
