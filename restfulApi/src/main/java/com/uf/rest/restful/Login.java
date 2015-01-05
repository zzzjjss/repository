package com.uf.rest.restful;

import java.util.UUID;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONObject;

import com.uf.rest.bean.AuthenticateResponseData;
import com.uf.rest.bean.IsUserExistResponseData;
import com.uf.rest.bean.RegistResponseData;
import com.uf.rest.bean.Response;
import com.uf.rest.bean.ResponseError;
import com.uf.rest.bean.Session;
import com.uf.rest.entity.User;
import com.uf.rest.service.ServiceFactory;
import com.uf.rest.service.UserService;
import com.uf.rest.util.CacheUtil;
@Singleton
@Path("/login")
public class Login {
	private UserService service=ServiceFactory.getService(UserService.class);
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/authenticate")
    public String authenticate(@FormParam("name") String name,@FormParam("password") String password,@FormParam("platform") String platform) {
		Response  response=new Response();
		try{
			User user=service.findUserByName(name);
			if(user!=null&&user.getPassword()!=null&&user.getPassword().equals(password)){
				UUID  id=UUID.randomUUID();
				String uid=id.toString();
				Session session=new Session();
				session.addAttribute("user", user);
				CacheUtil.saveObj(uid, session);
				AuthenticateResponseData data=new AuthenticateResponseData();
				data.setToken(uid);
				response.setData(data);
				response.setSuccess(true);
			}
		}catch(Exception e){
			e.printStackTrace();
			com.uf.rest.bean.ResponseError error=new com.uf.rest.bean.ResponseError();
			error.setCode("002");
			error.setMsg(e.getMessage());
			response.setError(error);
			response.setSuccess(false);
		}
		JSONObject obj=JSONObject.fromObject(response);
        return obj.toString();
    }
	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@POST
	@Path("/logout")
    public String logout(@FormParam("token") String token,@FormParam("platform") String platform) {
		CacheUtil.removeObj(token);
		Response  response=new Response();
		response.setSuccess(true);
		JSONObject obj=JSONObject.fromObject(response);
		return obj.toString();
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@POST
	@Path("/changePassword")
    public String changePassword(@FormParam("token") String token,@FormParam("old_psd") String oldPsd,@FormParam("new_psd") String newPsd,@FormParam("platform") String platform) {
		Session session=(Session)CacheUtil.getObj(token);
		Response  response=new Response();
		try{
			if(session!=null){
				User user=(User)session.getAttribute("user");
				if(user!=null&&user.getPassword().equals(oldPsd)){
					user.setPassword(newPsd);
					service.updateUserInfo(user);
					response.setSuccess(true);
				}else{
					response.setSuccess(false);
					ResponseError error=new ResponseError();
					error.setCode("100");
					error.setMsg("user doesn't exist or  old psd is wrong!");
					response.setError(error);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			ResponseError error=new ResponseError();
			error.setCode("101");
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
	@Path("/regist")
    public String regist(@FormParam("name") String name,@FormParam("password") String password,@FormParam("type") String type,@FormParam("platform") String platform) {
		User user=new User();
		user.setName(name);
		user.setPassword(password);
		user.setPlatform(Integer.parseInt(type));
		user.setRegistType(Integer.parseInt(platform));
		Response  response=new Response();
		try {
			service.registUser(user);
			UUID  id=UUID.randomUUID();
			String uid=id.toString();
			Session session=new Session();
			session.addAttribute("user", user);
			CacheUtil.saveObj(uid, session);
			response.setSuccess(true);
			RegistResponseData data=new RegistResponseData();
			data.setToken(uid);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			com.uf.rest.bean.ResponseError error=new com.uf.rest.bean.ResponseError();
			error.setCode("001");
			error.setMsg(e.getMessage());
			response.setError(error);
		}
		JSONObject obj=JSONObject.fromObject(response);
		return obj.toString();
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@POST
	@Path("/isUserExist")
    public String isUserExist(@FormParam("name") String name,@FormParam("platform") String platform) {
		Response  response=new Response();
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
			error.setCode("002");
			error.setMsg(e.getMessage());
			response.setError(error);
		}
		JSONObject obj=JSONObject.fromObject(response);
		return obj.toString();
	}
	
	
}
