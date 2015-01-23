package com.uf.rest.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONObject;

import org.junit.BeforeClass;
import org.junit.Test;

import com.uf.rest.bean.request.AddGoodClassRequest;
import com.uf.rest.bean.request.AddGoodRequest;
import com.uf.rest.bean.request.AddShopRequest;
import com.uf.rest.bean.request.DeleteGoodClassRequest;
import com.uf.rest.bean.request.DeleteGoodRequest;
import com.uf.rest.bean.request.OpenCloseShopRequest;
import com.uf.rest.bean.request.RegistUserRequest;
import com.uf.rest.bean.request.ShopCoordinate;
import com.uf.rest.bean.request.ShopLocation;
import com.uf.rest.bean.request.ShopUserInfoUpdateRequest;
import com.uf.rest.bean.request.UpdateGoodClassRequest;
import com.uf.rest.bean.request.UpdateGoodRequest;
import com.uf.rest.bean.request.UpdateShopRequest;
import com.uf.rest.bean.request.UserChangePasswordRequest;
import com.uf.rest.bean.request.UserLoginRequest;
import com.uf.rest.bean.request.UserLogoutRequest;
import com.uf.rest.bean.response.AddGoodClassResponse;
import com.uf.rest.bean.response.AddGoodResponse;
import com.uf.rest.bean.response.AddShopResponse;
import com.uf.rest.bean.response.DeleteGoodClassResponse;
import com.uf.rest.bean.response.DeleteGoodResponse;
import com.uf.rest.bean.response.GetShopUserInfoResponse;
import com.uf.rest.bean.response.IsUserExistResponse;
import com.uf.rest.bean.response.OpenCloseShopResponse;
import com.uf.rest.bean.response.QueryGoodClassResponse;
import com.uf.rest.bean.response.QueryGoodResponse;
import com.uf.rest.bean.response.QueryGoodsByClassResponse;
import com.uf.rest.bean.response.QueryShopInfoResponse;
import com.uf.rest.bean.response.UpdateGoodClassResponse;
import com.uf.rest.bean.response.UpdateGoodResponse;
import com.uf.rest.bean.response.UpdateShopResponse;
import com.uf.rest.bean.response.UserChangePasswordResponse;
import com.uf.rest.bean.response.UserLoginResponse;
import com.uf.rest.bean.response.UserLogoutResponse;
import com.uf.rest.bean.response.UserRegistResponse;
import com.uf.rest.util.FileUtil;

public class ShopActionTest {
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
	public void testRegister() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/shop/account/register");
		RegistUserRequest request=new RegistUserRequest();
		request.setName("helloShop");
		request.setP(1);
		request.setPassword("helloShop");
		request.setType(1);
		UserRegistResponse response=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON),UserRegistResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	@Test
	public void testUpdateShopUserInfo() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/shop/account/info/update");
		ShopUserInfoUpdateRequest request=new ShopUserInfoUpdateRequest();
		request.setName("helloShop");
		request.setP(1);
		request.setPassword("helloShop");
		request.setCard_image(FileUtil.getFileContent("c:/jason/test.jpg"));
		request.setId_card("360423191838383777");
		request.setName("jasonzhag");
		request.setToken(token);
		UserChangePasswordResponse response=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON),UserChangePasswordResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	@Test
	public void testGetAccountInfo() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/shop/account/info?token="+token);
		GetShopUserInfoResponse response=target.request(MediaType.APPLICATION_JSON).get(GetShopUserInfoResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	@Test
	public void testaccountExisted() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/shop/account/existed?name=helloShop");
		IsUserExistResponse response=target.request(MediaType.APPLICATION_JSON).get(IsUserExistResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	@Test
	public void testLogOut() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/shop/account/logout");
		UserLogoutRequest request=new UserLogoutRequest();
		request.setToken(token);
		UserLogoutResponse response=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON),UserLogoutResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	@Test
	public void testChangePassword() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/shop/account/password");
		UserChangePasswordRequest request=new UserChangePasswordRequest();
		request.setToken(token);
		request.setNew_psd("helloShop");
		request.setOld_psd("helloShop");
		UserChangePasswordResponse response=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON),UserChangePasswordResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	@Test
	public void testAddShop() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/shop/info/add");
		AddShopRequest request=new AddShopRequest();
		request.setToken(token);
		request.setIcon(FileUtil.getFileContent("c:/jason/test.jpg"));
		request.setName("helloShop");
		request.setP(1);
		request.setPhone(new String[]{"122222","344444"});
		ShopLocation lo=new ShopLocation();
		lo.setAddress("fttttian");
		lo.setCity("shenz");
		lo.setCountry("china");
		lo.setProvince("gd");
		request.setLocation(lo);
		ShopCoordinate coo=new ShopCoordinate();
		coo.setLatitude(333.4);
		coo.setLongitude(444.55);
		request.setCoordinate(coo);
		AddShopResponse response=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON),AddShopResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	@Test
	public void testUpdateShop() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/shop/info/update");
		UpdateShopRequest request=new UpdateShopRequest();
		request.setToken(token);
		request.setIcon(FileUtil.getFileContent("f:/test.jpg"));
		request.setName("helloShop");
		request.setP(1);
		request.setPhone(new String[]{"122222","888888"});
		ShopLocation lo=new ShopLocation();
		lo.setAddress("fttttian");
		lo.setCity("shenz");
		lo.setCountry("chinaaaa");
		lo.setProvince("gd");
		request.setLocation(lo);
		ShopCoordinate coo=new ShopCoordinate();
		coo.setLatitude(3334.4);
		coo.setLongitude(444.55);
		request.setCoordinate(coo);
		UpdateShopResponse response=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON),UpdateShopResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	@Test
	public void testQueryShop() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/shop/info?token="+token);
		QueryShopInfoResponse response=target.request(MediaType.APPLICATION_JSON).get(QueryShopInfoResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	
	
	@Test
	public void testOpenShop() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/shop/open");
		OpenCloseShopRequest request=new OpenCloseShopRequest();
		request.setToken(token);
		request.setOpen(true);
		OpenCloseShopResponse response=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON),OpenCloseShopResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	
	
	@Test
	public void testAddClass() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/shop/class/add");
		AddGoodClassRequest request=new AddGoodClassRequest();
		request.setToken(token);
		request.setIs_public(true);
		request.setName("testClass");
		AddGoodClassResponse response=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON),AddGoodClassResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	@Test
	public void testUpdateClass() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/shop/class/update");
		UpdateGoodClassRequest request=new UpdateGoodClassRequest();
		request.setId(2);
		request.setToken(token);
		request.setIs_public(true);
		request.setName("testClass2");
		UpdateGoodClassResponse response=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON),UpdateGoodClassResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	@Test
	public void testDeleteClass() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/shop/class/delete");
		DeleteGoodClassRequest request=new DeleteGoodClassRequest();
		request.setId(2);
		request.setToken(token);
		DeleteGoodClassResponse response=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON),DeleteGoodClassResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	@Test
	public void testQueryClass() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/shop/class?start=0&count=10&token="+token);
		QueryGoodClassResponse response=target.request(MediaType.APPLICATION_JSON).get(QueryGoodClassResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	@Test
	public void testAddProduct() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/shop/goods/add");
		AddGoodRequest request=new AddGoodRequest();
		request.setToken(token);
		request.setIs_public(true);
		request.setClass_id(1);
		request.setName("xishangyi");
		request.setP(1);
		request.setPrice(12.0f);
		request.setSell(true);
		AddGoodResponse response=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON),AddGoodResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	@Test
	public void testUpdateProduct() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/shop/goods/update");
		UpdateGoodRequest request=new UpdateGoodRequest();
		request.setToken(token);
		request.setIs_public(false);
		request.setClass_id(1);
		request.setName("xishangyi");
		request.setP(1);
		request.setPrice(12.34f);
		request.setSell(true);
		request.setId(1);
		UpdateGoodResponse response=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON),UpdateGoodResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	@Test
	public void testQueryGoods() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/shop/goods?start=0&count=10&token="+token);
		QueryGoodResponse response=target.request(MediaType.APPLICATION_JSON).get(QueryGoodResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	@Test
	public void testQueryClassGoods() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/shop/goods/class?start=0&count=10&token="+token);
		QueryGoodsByClassResponse response=target.request(MediaType.APPLICATION_JSON).get(QueryGoodsByClassResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	@Test
	public void testDeleteGoods() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/shop/goods/delete");
		DeleteGoodRequest request=new DeleteGoodRequest();
		request.setId(1);
		request.setIs_public(true);
		request.setToken(token);
		DeleteGoodResponse response=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON),DeleteGoodResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
}
