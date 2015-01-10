package com.uf.rest.restful;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONObject;

import com.uf.rest.bean.Constant;
import com.uf.rest.bean.ResponseError;
import com.uf.rest.bean.Session;
import com.uf.rest.bean.request.CustomHomeRequest;
import com.uf.rest.bean.request.RegistUserRequest;
import com.uf.rest.bean.request.UserChangePasswordRequest;
import com.uf.rest.bean.request.UserLoginRequest;
import com.uf.rest.bean.request.UserLogoutRequest;
import com.uf.rest.bean.response.CustomHomeResponse;
import com.uf.rest.bean.response.CustomHomeResponseData;
import com.uf.rest.bean.response.IsUserExistResponse;
import com.uf.rest.bean.response.IsUserExistResponseData;
import com.uf.rest.bean.response.ResponseLocation;
import com.uf.rest.bean.response.ResponseShop;
import com.uf.rest.bean.response.UserChangePasswordResponse;
import com.uf.rest.bean.response.UserLoginResponse;
import com.uf.rest.bean.response.UserLoginResponseData;
import com.uf.rest.bean.response.UserLogoutResponse;
import com.uf.rest.bean.response.UserRegistResponse;
import com.uf.rest.bean.response.UserRegistResponseData;
import com.uf.rest.entity.Shop;
import com.uf.rest.entity.User;
import com.uf.rest.exception.UserExistException;
import com.uf.rest.service.CustomService;
import com.uf.rest.service.ServiceFactory;
import com.uf.rest.service.UserService;
import com.uf.rest.util.CacheUtil;
import com.uf.rest.util.FileUtil;
@Singleton
@Path("/custom")
public class CustomAction {
	private UserService service=ServiceFactory.getService(UserService.class);
	private CustomService customService=ServiceFactory.getService(CustomService.class);
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account/login")
    public String accountLogin(UserLoginRequest request) {
		UserLoginResponse  response=new UserLoginResponse();
		try{
			User user=service.findUserByName(request.getName());
			if(user!=null&&user.getPassword()!=null&&user.getPassword().equals(request.getPassword())){
				UUID  id=UUID.randomUUID();
				String uid=id.toString();
				Session session=new Session();
				session.addAttribute("user", user);
				CacheUtil.saveObj(uid, session);
				UserLoginResponseData data=new UserLoginResponseData();
				data.setToken(uid);
				response.setData(data);
				response.setSuccess(true);
			}else if(user==null){
				ResponseError error=new ResponseError();
				error.setCode(Constant.USER_NOT_EXIST_CODE);
				error.setMsg("user not exist!");
				response.setError(error);
			}else{
				ResponseError error=new ResponseError();
				error.setCode(Constant.PST_WRONG_CODE);
				error.setMsg("password is wrong!");
				response.setError(error);
			}
		}catch(Exception e){
			e.printStackTrace();
			com.uf.rest.bean.ResponseError error=new com.uf.rest.bean.ResponseError();
			error.setCode(Constant.SYSTEM_EXCEPTION_CODE);
			error.setMsg(e.getMessage());
			response.setError(error);
			response.setSuccess(false);
		}
		JSONObject obj=JSONObject.fromObject(response);
        return obj.toString();
    }
	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("/account/logout")
    public String logout(UserLogoutRequest request) {
		CacheUtil.removeObj(request.getToken());
		UserLogoutResponse  response=new UserLogoutResponse();
		response.setSuccess(true);
		JSONObject obj=JSONObject.fromObject(response);
		return obj.toString();
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("/account/password")
    public String changePassword(UserChangePasswordRequest request) {
		Session session=(Session)CacheUtil.getObj(request.getToken());
		UserChangePasswordResponse  response=new UserChangePasswordResponse();
		try{
			if(session!=null){
				User user=(User)session.getAttribute("user");
				if(user!=null&&user.getPassword().equals(request.getOld_psd())){
					user.setPassword(request.getNew_psd());
					service.updateUserInfo(user);
					response.setSuccess(true);
				}else{
					response.setSuccess(false);
					ResponseError error=new ResponseError();
					if(user==null){
						error.setCode(Constant.USER_NOT_EXIST_CODE);
						error.setMsg("user doesn't exist ");
					}else{
						error.setCode(Constant.OLD_PST_WRONG_CODE);
						error.setMsg("old password is wrong! ");
					}
					response.setError(error);
				}
			}else{
				ResponseError error=new ResponseError();
				error.setCode(Constant.USER_NOT_LOGIN_CODE);
				error.setMsg("user not login,don't allow to change password!");
				response.setError(error);
			}
		}catch(Exception e){
			e.printStackTrace();
			ResponseError error=new ResponseError();
			error.setCode(Constant.SYSTEM_EXCEPTION_CODE);
			error.setMsg(e.getMessage());
			response.setSuccess(false);
			response.setError(error);
		}
		JSONObject obj=JSONObject.fromObject(response);
		return obj.toString();
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("/account/register")
    public String regist(RegistUserRequest request) {
		User user=new User();
		user.setName(request.getName());
		user.setPassword(request.getPassword());
		user.setPlatform(request.getP());
		user.setRegistType(request.getType());
		UserRegistResponse  response=new UserRegistResponse();
		try {
			service.registUser(user);
			UUID  id=UUID.randomUUID();
			String uid=id.toString();
			Session session=new Session();
			session.addAttribute("user", user);
			CacheUtil.saveObj(uid, session);
			response.setSuccess(true);
			UserRegistResponseData data=new UserRegistResponseData();
			data.setToken(uid);
			response.setData(data);
		} catch(UserExistException e){
			response.setSuccess(false);
			ResponseError error=new ResponseError();
			error.setCode(Constant.USER_EXIST_CODE);
			error.setMsg(e.getMessage());
			response.setError(error);
		}catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			ResponseError error=new ResponseError();
			error.setCode(Constant.SYSTEM_EXCEPTION_CODE);
			error.setMsg(e.getMessage());
			response.setError(error);
		}
		JSONObject obj=JSONObject.fromObject(response);
		return obj.toString();
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@GET
	@Path("/account/existed")
    public String isUserExist(@QueryParam("name") String name) {
		IsUserExistResponse  response=new IsUserExistResponse();
		try {
			boolean isExist=service.isUserExist(name);
			response.setSuccess(true);
			IsUserExistResponseData data=new IsUserExistResponseData();
			data.setExisted(isExist);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			com.uf.rest.bean.ResponseError error=new com.uf.rest.bean.ResponseError();
			error.setCode(Constant.SYSTEM_EXCEPTION_CODE);
			error.setMsg(e.getMessage());
			response.setError(error);
		}
		JSONObject obj=JSONObject.fromObject(response);
		return obj.toString();
	}
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("/home")
    public String home(CustomHomeRequest request) {
		CustomHomeResponse response=new CustomHomeResponse();
		try {
			List<Shop> shops=customService.findNearShops(request.getStart(), request.getCount(), request.getLongitude(), request.getLatitude());	
			CustomHomeResponseData data= new CustomHomeResponseData();
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			List<ResponseShop> responseShops=new ArrayList<ResponseShop>();
			if(shops!=null&&shops.size()>0){
				for(Shop shop:shops){
					ResponseShop responseShop=new ResponseShop();
					responseShop.setIcon(FileUtil.getFileContent(shop.getShopPhotoPath()));
					responseShop.setId(shop.getId());
					ResponseLocation location=new ResponseLocation();
					location.setCountry(shop.getCountry());
					location.setAddress(shop.getAddress());
					location.setCity(shop.getCity());
					location.setProvince(shop.getProvince());
					responseShop.setLocation(location);
					responseShop.setMark(shop.getMark().toString());
					responseShop.setName(shop.getName());
					responseShop.setPhone(shop.getContactStyle().split(","));
					responseShop.setTime(format.format(shop.getCreateTime()));
					responseShop.setType("type");
					responseShops.add(responseShop);
				}
				data.setShop(responseShops);
				data.setCount(shops.size());
				data.setNext_cursor(request.getStart()+request.getCount());
				response.setData(data);
				response.setSuccess(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			com.uf.rest.bean.ResponseError error=new com.uf.rest.bean.ResponseError();
			error.setCode(Constant.SYSTEM_EXCEPTION_CODE);
			error.setMsg(e.getMessage());
			response.setError(error);
		}
		JSONObject obj=JSONObject.fromObject(response);
		return obj.toString();
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@GET
	@Path("/order/process")
	public String orderProcess(@QueryParam("token")String token,@QueryParam("p")String p){
		
		
		return null;
	}
	
	
	
}
