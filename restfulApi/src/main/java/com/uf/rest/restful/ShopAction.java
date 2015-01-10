package com.uf.rest.restful;

import java.util.Date;
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
import com.uf.rest.bean.request.RegistUserRequest;
import com.uf.rest.bean.request.ShopUserInfoUpdateRequest;
import com.uf.rest.bean.request.UserChangePasswordRequest;
import com.uf.rest.bean.request.UserLoginRequest;
import com.uf.rest.bean.request.UserLogoutRequest;
import com.uf.rest.bean.response.GetShopUserInfoResponse;
import com.uf.rest.bean.response.GetShopUserInfoResponseData;
import com.uf.rest.bean.response.IsUserExistResponse;
import com.uf.rest.bean.response.IsUserExistResponseData;
import com.uf.rest.bean.response.UserChangePasswordResponse;
import com.uf.rest.bean.response.UserLoginResponse;
import com.uf.rest.bean.response.UserLoginResponseData;
import com.uf.rest.bean.response.UserLogoutResponse;
import com.uf.rest.bean.response.UserRegistResponse;
import com.uf.rest.bean.response.UserRegistResponseData;
import com.uf.rest.entity.ShopUser;
import com.uf.rest.exception.UserExistException;
import com.uf.rest.service.ServiceFactory;
import com.uf.rest.service.ShopService;
import com.uf.rest.util.CacheUtil;

@Singleton
@Path("/shop")
public class ShopAction {
	private ShopService service=ServiceFactory.getService(ShopService.class);
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("/account/register")
	public String accountRegiste(RegistUserRequest request){
		ShopUser user=new ShopUser();
		user.setUserName(request.getName());
		user.setPassword(request.getPassword());
		user.setCreateDate(new Date());
		user.setRegistType(request.getType());
		user.setPlatform(request.getP());
		UserRegistResponse  response=new UserRegistResponse();
		try {
			service.registShopUser(user);
			UUID  id=UUID.randomUUID();
			String uid=id.toString();
			Session session=new Session();
			session.addAttribute("shopUser", user);
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
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("/account/info/update")
	public String accountInfoUpdate(ShopUserInfoUpdateRequest request){
		Session session=(Session)CacheUtil.getObj(request.getToken());
		UserChangePasswordResponse  response=new UserChangePasswordResponse();
		if(request.getCard_image()==null||request.getId_card()==null||request.getName()==null||request.getPassword()==null){
			ResponseError error=new ResponseError();
			error.setCode(Constant.VALUE_NOT_EXIST);
			error.setMsg("request value is not set  ");
			response.setError(error);
			response.setSuccess(false);
			JSONObject obj=JSONObject.fromObject(response);
			return obj.toString();
		}
		
		try{
			if(session!=null){
				ShopUser user=(ShopUser)session.getAttribute("shopUser");
				if(user!=null){
					user.setIdCard(request.getId_card());
					user.setPassword(request.getPassword());
					user.setRealName(request.getName());
					//upload the image to cloud
					service.updateShopUserInfo(user);
					response.setSuccess(true);
				}else{
					response.setSuccess(false);
					ResponseError error=new ResponseError();
					error.setCode(Constant.USER_NOT_EXIST_CODE);
					error.setMsg("shop user doesn't exist ");
					response.setError(error);
				}
			}else{
				ResponseError error=new ResponseError();
				error.setCode(Constant.USER_NOT_LOGIN_CODE);
				error.setMsg("shop user not login,don't allow to update info!");
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
	@GET
	@Path("/account/info")
	public String accountInfo(@QueryParam("token")String token,@QueryParam("p")String p){
		Session session=(Session)CacheUtil.getObj(token);
		GetShopUserInfoResponse  response=new GetShopUserInfoResponse();
		try{
			if(session!=null){
				ShopUser user=(ShopUser)session.getAttribute("shopUser");
				if(user!=null){
					user=service.findShopUserByName(user.getUserName());
					GetShopUserInfoResponseData data= new GetShopUserInfoResponseData();
					data.setCard_image(user.getIdCardPhotoPath());
					data.setId_card(user.getIdCard());
					data.setName(user.getUserName());
					response.setData(data);
					response.setSuccess(true);
				}else{
					response.setSuccess(false);
					ResponseError error=new ResponseError();
					error.setCode(Constant.USER_NOT_EXIST_CODE);
					error.setMsg("shop user doesn't exist ");
					response.setError(error);
				}
			}else{
				ResponseError error=new ResponseError();
				error.setCode(Constant.USER_NOT_LOGIN_CODE);
				error.setMsg("shop user not login,don't allow to update info!");
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
	@GET
	@Path("/account/existed")
	public String accountExisted(@QueryParam("name") String userName){
		IsUserExistResponse  response=new IsUserExistResponse();
		try {
			boolean isExist=service.isShopUserExist(userName);
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
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account/login")
	public String accountLogin(UserLoginRequest request){
		UserLoginResponse  response=new UserLoginResponse();
		try{
			ShopUser user=service.findShopUserByName(request.getName());
			if(user!=null&&user.getPassword()!=null&&user.getPassword().equals(request.getPassword())){
				UUID  id=UUID.randomUUID();
				String uid=id.toString();
				Session session=new Session();
				session.addAttribute("shopUser", user);
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
	public String accountLogout(UserLogoutRequest request){
		CacheUtil.removeObj(request.getToken());
		UserLogoutResponse  response=new UserLogoutResponse();
		response.setSuccess(true);
		JSONObject obj=JSONObject.fromObject(response);
		return obj.toString();
	}
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@POST
	@Path("/account/password")
	public String accountPassword(UserChangePasswordRequest request){
		Session session=(Session)CacheUtil.getObj(request.getToken());
		UserChangePasswordResponse  response=new UserChangePasswordResponse();
		try{
			if(session!=null){
				ShopUser user=(ShopUser)session.getAttribute("shopUser");
				if(user!=null&&user.getPassword().equals(request.getOld_psd())){
					user.setPassword(request.getNew_psd());
					service.updateShopUserInfo(user);
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
				error.setMsg("shop user not login,don't allow to change password!");
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
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@POST
	@Path("/open")
	public String open(){
		return null;
	}
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@POST
	@Path("/info/add")
	public String infoAdd(){
		
		return null;
	}
	
	public String infoUpdate(){
		return null;
	}
	public String info(){
		return null;
	}
	public String classAdd(){
		return null;
	}
	public String classDelete(){
		return null;
	}
	public String classUpdate(){
		return null;
	}
	public String classQuery(){
		return null;
	}
	public String goodsAdd(){
		return null;
	}
	public String goodsDelete(){
		return null;
	}
	public String goodsUpdate(){
		return null;
	}
	public String goods(){
		return null;
	}
	public String goodsClass(){
		return null;
	}
}
