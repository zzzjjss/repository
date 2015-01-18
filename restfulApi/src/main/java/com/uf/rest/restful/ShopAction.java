package com.uf.rest.restful;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.uf.rest.bean.response.AddGoodClassResponseData;
import com.uf.rest.bean.response.AddGoodResponse;
import com.uf.rest.bean.response.AddGoodResponseData;
import com.uf.rest.bean.response.AddShopResponse;
import com.uf.rest.bean.response.AddShopResponseData;
import com.uf.rest.bean.response.DeleteGoodClassResponse;
import com.uf.rest.bean.response.DeleteGoodResponse;
import com.uf.rest.bean.response.GetShopUserInfoResponse;
import com.uf.rest.bean.response.GetShopUserInfoResponseData;
import com.uf.rest.bean.response.IsUserExistResponse;
import com.uf.rest.bean.response.IsUserExistResponseData;
import com.uf.rest.bean.response.OpenCloseShopResponse;
import com.uf.rest.bean.response.QueryGoodClassResponse;
import com.uf.rest.bean.response.QueryGoodClassResponseData;
import com.uf.rest.bean.response.QueryGoodResponse;
import com.uf.rest.bean.response.QueryGoodResponseData;
import com.uf.rest.bean.response.QueryGoodsByClassResponse;
import com.uf.rest.bean.response.QueryGoodsByClassResponseData;
import com.uf.rest.bean.response.QueryShopInfoResponse;
import com.uf.rest.bean.response.QueryShopInfoResponseData;
import com.uf.rest.bean.response.ResponseCoordinate;
import com.uf.rest.bean.response.ResponseGood;
import com.uf.rest.bean.response.ResponseGoodClass;
import com.uf.rest.bean.response.ResponseLocation;
import com.uf.rest.bean.response.ResponseQueryByClassGood;
import com.uf.rest.bean.response.ResponseQueryByClassGoods;
import com.uf.rest.bean.response.ResponseQueryGood;
import com.uf.rest.bean.response.ResponseShopClassGoods;
import com.uf.rest.bean.response.UpdateGoodClassResponse;
import com.uf.rest.bean.response.UpdateGoodResponse;
import com.uf.rest.bean.response.UpdateShopResponse;
import com.uf.rest.bean.response.UserChangePasswordResponse;
import com.uf.rest.bean.response.UserLoginResponse;
import com.uf.rest.bean.response.UserLoginResponseData;
import com.uf.rest.bean.response.UserLogoutResponse;
import com.uf.rest.bean.response.UserRegistResponse;
import com.uf.rest.bean.response.UserRegistResponseData;
import com.uf.rest.entity.Product;
import com.uf.rest.entity.ProductClass;
import com.uf.rest.entity.Shop;
import com.uf.rest.entity.ShopProductPrice;
import com.uf.rest.entity.ShopUser;
import com.uf.rest.exception.UserExistException;
import com.uf.rest.service.ServiceFactory;
import com.uf.rest.service.ShopService;
import com.uf.rest.util.CacheUtil;
import com.uf.rest.util.FileUtil;

@Singleton
@Path("/shop")
public class ShopAction {
	private ShopService service=ServiceFactory.getService(ShopService.class);
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("/account/register")
	public String accountRegiste(RegistUserRequest request){
		UserRegistResponse  response=new UserRegistResponse();
		try {
			ShopUser user=new ShopUser();
			user.setUserName(request.getName());
			user.setPassword(request.getPassword());
			user.setCreateDate(new Date());
			user.setRegistType(request.getType());
			user.setPlatform(request.getP());
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
			ShopUser shopUser=getShopUserByToken(request.getToken());
			if(shopUser!=null){
				shopUser.setIdCard(request.getId_card());
				shopUser.setPassword(request.getPassword());
				shopUser.setRealName(request.getName());
				String filePath=Constant.TEMP_PATH+"shopUserIdImag/"+shopUser.getId()+".jpg";
				FileUtil.writeContentToFile(request.getCard_image(), filePath);
				shopUser.setIdCardPhotoPath(filePath);
				shopUser.setPlatform(request.getP());
				service.updateShopUserInfo(shopUser);
				response.setSuccess(true);
			}else{
				ResponseError error=new ResponseError();
				error.setCode(Constant.USER_NOT_LOGIN_CODE);
				error.setMsg("shop user not login!");
				response.setError(error);
				response.setSuccess(false);
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
	@GET
	@Path("/account/info")
	public String accountInfo(@QueryParam("token")String token,@QueryParam("p")String p){
		GetShopUserInfoResponse  response=new GetShopUserInfoResponse();
		try{
			ShopUser shopUser=getShopUserByToken(token);
			if(shopUser!=null){
				ShopUser user=service.findShopUserByName(shopUser.getUserName());
				GetShopUserInfoResponseData data= new GetShopUserInfoResponseData();
				data.setCard_image(FileUtil.getFileContent(user.getIdCardPhotoPath()));
				data.setId_card(user.getIdCard());
				data.setName(user.getUserName());
				response.setData(data);
				response.setSuccess(true);
			}else{
				ResponseError error=new ResponseError();
				error.setCode(Constant.USER_NOT_LOGIN_CODE);
				error.setMsg("shop user not login!");
				response.setError(error);
				response.setSuccess(false);
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
				response.setSuccess(false);
			}else{
				ResponseError error=new ResponseError();
				error.setCode(Constant.PST_WRONG_CODE);
				error.setMsg("password is wrong!");
				response.setError(error);
				response.setSuccess(false);
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
		UserLogoutResponse  response=new UserLogoutResponse();
		CacheUtil.removeObj(request.getToken());
		response.setSuccess(true);
		JSONObject obj=JSONObject.fromObject(response);
		return obj.toString();
	}
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("/account/password")
	public String accountPassword(UserChangePasswordRequest request){
		UserChangePasswordResponse  response=new UserChangePasswordResponse();
		try{
			ShopUser shopUser=getShopUserByToken(request.getToken());
			if(shopUser!=null){
				if(shopUser.getPassword().equals(request.getOld_psd())){
					shopUser.setPassword(request.getNew_psd());
					service.updateShopUserInfo(shopUser);
					response.setSuccess(true);
				}else{
					response.setSuccess(false);
					ResponseError error=new ResponseError();
					error.setCode(Constant.OLD_PST_WRONG_CODE);
					error.setMsg("old password is wrong! ");
					response.setError(error);
				}
			}else{
				ResponseError error=new ResponseError();
				error.setCode(Constant.USER_NOT_LOGIN_CODE);
				error.setMsg("shop user not login!");
				response.setError(error);
				response.setSuccess(false);
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
	@Path("/open")
	public String open(OpenCloseShopRequest request){
		OpenCloseShopResponse response=new OpenCloseShopResponse();
		try{
			ShopUser shopUser=getShopUserByToken(request.getToken());
			if(shopUser!=null){
				Shop shop=shopUser.getShop();
				if(shop!=null){
					shop.setIsOpen(request.getOpen());
					service.updateShop(shop);
					response.setSuccess(true);
				}else{
					ResponseError error=new ResponseError();
					error.setCode(Constant.VALUE_NOT_EXIST);
					error.setMsg("shop user has no shop !");
					response.setError(error);
					response.setSuccess(false);
				}
			}else{
				ResponseError error=new ResponseError();
				error.setCode(Constant.USER_NOT_LOGIN_CODE);
				error.setMsg("shop user not login!");
				response.setSuccess(false);
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
	@Path("/info/add")
	public String infoAdd(AddShopRequest request){
		AddShopResponse response=new AddShopResponse();
		try {
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			ShopUser shopUser=getShopUserByToken(request.getToken());
			if(shopUser!=null){
				Shop shop=new Shop();
				ShopLocation location=request.getLocation();
				if(location!=null){
					shop.setAddress(location.getAddress());
					shop.setCountry(location.getCountry());
					shop.setProvince(location.getProvince());
					shop.setCity(location.getCity());
				}
				ShopCoordinate coor=request.getCoordinate();
				if(coor!=null){
					shop.setLatitude(coor.getLatitude());
					shop.setLongitude(coor.getLongitude());
				}
				Date date=new Date();
				shop.setContactStyle(arrayToString(request.getPhone()));
				shop.setIsOpen(true);
				shop.setCreateTime(date);
				shop.setName(request.getName());
				String shopIconPath=Constant.TEMP_PATH+"shopImages/"+shopUser.getId()+"/shopIcon.jpg"; 
				FileUtil.writeContentToFile(request.getIcon(), shopIconPath);
				shop.setShopPhotoPath(shopIconPath);
				shop.setShopUser(shopUser);
				service.addShop(shop);
				//shop.setBusinessType(request.get);
				AddShopResponseData data=new AddShopResponseData();
				data.setId(shop.getId());
				data.setTime(format.format(date));
				response.setData(data);
				response.setSuccess(true);
			}else{
				ResponseError error=new ResponseError();
				error.setCode(Constant.USER_NOT_LOGIN_CODE);
				error.setMsg("shop user not login!");
				response.setSuccess(false);
				response.setError(error);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ResponseError error = new ResponseError();
			error.setCode(Constant.SYSTEM_EXCEPTION_CODE);
			error.setMsg(e.getMessage());
			response.setSuccess(false);
			response.setError(error);
		}
		JSONObject obj=JSONObject.fromObject(response);
		return obj.toString();
	}
	private String arrayToString(String[] value){
		StringBuilder result=new StringBuilder();
		if(value!=null&&value.length>0){
			for(String a:value){
				result.append(a+";");
			}
		}
		return result.toString();
	}
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("/info/update")
	public String infoUpdate(UpdateShopRequest request){
		UpdateShopResponse response=new UpdateShopResponse();
		try {
			ShopUser shopUser=getShopUserByToken(request.getToken());
			if(shopUser!=null){
				Shop shop=service.findShopByShopUserId(shopUser.getId());
				if(shop!=null){
					ShopLocation location=request.getLocation();
					if(location!=null){
						shop.setAddress(location.getAddress());
						shop.setCountry(location.getCountry());
						shop.setProvince(location.getProvince());
						shop.setCity(location.getCity());
					}
					ShopCoordinate coor=request.getCoordinate();
					if(coor!=null){
						shop.setLatitude(coor.getLatitude());
						shop.setLongitude(coor.getLongitude());
					}
					shop.setContactStyle(arrayToString(request.getPhone()));
					shop.setIsOpen(true);
					shop.setName(request.getName());
					String shopIconPath=Constant.TEMP_PATH+"shopImages/"+shopUser.getId()+"/shopIcon.jpg"; 
					FileUtil.writeContentToFile(request.getIcon(), shopIconPath);
					shop.setShopPhotoPath(shopIconPath);
					service.updateShop(shop);
					//shop.setBusinessType(request.get);
					response.setSuccess(true);
				}else{
					ResponseError error=new ResponseError();
					error.setCode(Constant.VALUE_NOT_EXIST);
					error.setMsg("shop not be created!");
					response.setSuccess(false);
					response.setError(error);
				}
				
			}else{
				ResponseError error=new ResponseError();
				error.setCode(Constant.USER_NOT_LOGIN_CODE);
				error.setMsg("shop user not login!");
				response.setSuccess(false);
				response.setError(error);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ResponseError error = new ResponseError();
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
	@GET
	@Path("/info")
	public String info(@QueryParam("token") String token,@QueryParam("p") String p){
		QueryShopInfoResponse response=new QueryShopInfoResponse(); 
		try {
			ShopUser shopUser=getShopUserByToken(token);
			if(shopUser!=null){
				Shop shop=service.findShopByShopUserId(shopUser.getId());
				if(shop!=null){
					SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					List<ShopProductPrice> goodPrices=service.findShopGoodPriceInfoByShopId(shop.getId());
					QueryShopInfoResponseData data=new QueryShopInfoResponseData(); 
					Map<Integer, List<ResponseGood>> responseGoods=new HashMap<Integer,  List<ResponseGood>>();
					Map<Integer, String> responseClass=new HashMap<Integer, String>();
					if(goodPrices!=null&&goodPrices.size()>0){
						for(ShopProductPrice price:goodPrices){
							ResponseGood good=new ResponseGood();
							good.setId(price.getProduct().getId());
							good.setName(price.getProduct().getName());
							good.setPrice(price.getPrice());
							Integer classId=price.getProduct().getProductClass().getId();
							String name=price.getProduct().getProductClass().getName();
							List<ResponseGood> goods=responseGoods.get(classId);
							if(goods==null){
								goods=new ArrayList<ResponseGood>();
								responseGoods.put(classId, goods);
							}
							goods.add(good);
							if(!responseClass.containsKey(classId)){
								responseClass.put(classId, name);
							}
							
						}
						List<ResponseShopClassGoods> classGoods=new ArrayList<ResponseShopClassGoods>();
						for(Integer key:responseGoods.keySet()){
							ResponseShopClassGoods classGood=new ResponseShopClassGoods();
							classGood.setClass_id(key);
							classGood.setClass_name(responseClass.get(key));
							classGood.setGood(responseGoods.get(key));
							classGoods.add(classGood);
						}
						data.setGoods(classGoods);
						ResponseCoordinate coordinate=new ResponseCoordinate();
						coordinate.setLatitude(shop.getLatitude());
						coordinate.setLongitude(shop.getLongitude());
						data.setCoordinate(coordinate);
						data.setIcon(FileUtil.getFileContent(shop.getShopPhotoPath()));
						ResponseLocation location=new ResponseLocation();
						location.setAddress(shop.getAddress());
						location.setCity(shop.getCity());
						location.setCountry(shop.getCountry());
						location.setProvince(shop.getProvince());
						data.setLocation(location);
						data.setMark(String.valueOf(shop.getMark()));
						data.setName(shop.getName());
						data.setTime(format.format(shop.getCreateTime()));
						response.setData(data);
					}
					response.setSuccess(true);
				}else{
					ResponseError error=new ResponseError();
					error.setCode(Constant.VALUE_NOT_EXIST);
					error.setMsg("shop not be created!");
					response.setSuccess(false);
					response.setError(error);
				}
				
			}else{
				ResponseError error=new ResponseError();
				error.setCode(Constant.USER_NOT_LOGIN_CODE);
				error.setMsg("shop user not login!");
				response.setSuccess(false);
				response.setError(error);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ResponseError error = new ResponseError();
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
	@Path("/class/add")
	public String classAdd(AddGoodClassRequest request){
		AddGoodClassResponse response=new AddGoodClassResponse();
		try {
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			ShopUser shopUser=getShopUserByToken(request.getToken());
			if(shopUser!=null){
				ProductClass proClass=new ProductClass();
				proClass.setName(request.getName());
				Date date=new Date();
				proClass.setAddTime(date);
				service.addProductClass(proClass);
				AddGoodClassResponseData data=new AddGoodClassResponseData();
				data.setId(proClass.getId());
				data.setTime(format.format(date));
				response.setData(data);
				response.setSuccess(true);
			}else{
				ResponseError error=new ResponseError();
				error.setCode(Constant.USER_NOT_LOGIN_CODE);
				error.setMsg("shop user not login!");
				response.setSuccess(false);
				response.setError(error);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ResponseError error = new ResponseError();
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
	@Path("/class/delete")
	public String classDelete(DeleteGoodClassRequest request){
		DeleteGoodClassResponse response =new DeleteGoodClassResponse();
		try {
			ShopUser shopUser=getShopUserByToken(request.getToken());
			if(shopUser!=null){
				service.deleteProductClassById(request.getId());
				response.setSuccess(true);
			}else{
				ResponseError error=new ResponseError();
				error.setCode(Constant.USER_NOT_LOGIN_CODE);
				error.setMsg("shop user not login!");
				response.setSuccess(false);
				response.setError(error);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ResponseError error = new ResponseError();
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
	@Path("/class/update")
	public String classUpdate(UpdateGoodClassRequest request){
		UpdateGoodClassResponse response=new UpdateGoodClassResponse();
		try {
			ShopUser shopUser=getShopUserByToken(request.getToken());
			if(shopUser!=null){
				ProductClass proClass=service.findProductClassById(request.getId());
				if(proClass!=null){
					proClass.setName(request.getName());
					service.updateProductClass(proClass);
					response.setSuccess(true);
				}else{
					ResponseError error=new ResponseError();
					error.setCode(Constant.VALUE_NOT_EXIST);
					error.setMsg("can't find productClass ");
					response.setSuccess(false);
					response.setError(error);
				}
			}else{
				ResponseError error=new ResponseError();
				error.setCode(Constant.USER_NOT_LOGIN_CODE);
				error.setMsg("shop user not login!");
				response.setSuccess(false);
				response.setError(error);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ResponseError error = new ResponseError();
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
	@GET
	@Path("/class")
	public String classQuery(@QueryParam("token") String token,@QueryParam("p") String p,@QueryParam("start") String start,@QueryParam("count") String count){
		QueryGoodClassResponse response=new QueryGoodClassResponse();
		try {
			ShopUser shopUser=getShopUserByToken(token);
			if(shopUser!=null){
				SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				List<ProductClass> proClasses=service.findPagedProductClass(Integer.parseInt(start), Integer.parseInt(count));
				if(proClasses!=null&&proClasses.size()>0){
					QueryGoodClassResponseData data=new QueryGoodClassResponseData(); 
					List<ResponseGoodClass> goodClasses=new ArrayList<ResponseGoodClass>();
					for(ProductClass proClass:proClasses){
						ResponseGoodClass goodClass=new ResponseGoodClass();
						goodClass.setId(proClass.getId());
						goodClass.setName(proClass.getName());
						goodClass.setTime(format.format(proClass.getAddTime()));
						goodClasses.add(goodClass);
					}
					data.setGoodClass(goodClasses);
					data.setCount(goodClasses.size());
					data.setCursor_next(Integer.parseInt(start)+goodClasses.size());
					response.setData(data);
				}
				response.setSuccess(true);
			}else{
				ResponseError error=new ResponseError();
				error.setCode(Constant.USER_NOT_LOGIN_CODE);
				error.setMsg("shop user not login!");
				response.setSuccess(false);
				response.setError(error);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ResponseError error = new ResponseError();
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
	@Path("/goods/add")
	public String goodsAdd(AddGoodRequest request){
		AddGoodResponse response=new AddGoodResponse();
		try {
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			ShopUser shopUser=getShopUserByToken(request.getToken());
			if(shopUser!=null){
				Product pro=new Product();
				pro.setDefaultPrice(request.getPrice());
				pro.setName(request.getName());
				Date time=new Date();
				pro.setAddTime(time);
				ProductClass proClass=service.findProductClassById(request.getClass_id());
				if(proClass!=null)
					pro.setProductClass(proClass);
				service.addPublicProduct(pro);
				AddGoodResponseData data=new  AddGoodResponseData();
				data.setId(pro.getId());
				data.setTime(format.format(time));
				response.setData(data);
				response.setSuccess(true);
			}else{
				ResponseError error=new ResponseError();
				error.setCode(Constant.USER_NOT_LOGIN_CODE);
				error.setMsg("shop user not login!");
				response.setSuccess(false);
				response.setError(error);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ResponseError error = new ResponseError();
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
	@Path("/goods/delete")
	public String goodsDelete(DeleteGoodRequest request){
		DeleteGoodResponse response=new DeleteGoodResponse();
		try {
			ShopUser shopUser=getShopUserByToken(request.getToken());
			if(shopUser!=null){
				Boolean isPublic=request.getIs_public();
				if(isPublic!=null&&isPublic.booleanValue()){
					service.deletePublicProductById(request.getId());
				}else{
					if(shopUser.getShop()!=null){
						service.deleteShopProductPrice(shopUser.getShop().getId(), request.getId());
					}
				}
				response.setSuccess(true);
			}else{
				ResponseError error=new ResponseError();
				error.setCode(Constant.USER_NOT_LOGIN_CODE);
				error.setMsg("shop user not login!");
				response.setSuccess(false);
				response.setError(error);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ResponseError error = new ResponseError();
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
	@Path("/goods/update")
	public String goodsUpdate(UpdateGoodRequest request){
		UpdateGoodResponse response=new UpdateGoodResponse();
		try {
			ShopUser shopUser=getShopUserByToken(request.getToken());
			if(shopUser!=null){
				Product product=service.findProducById(request.getId());
				if(product!=null){
					Boolean isPublic=request.getIs_public();
					if(isPublic!=null&&isPublic.booleanValue()){
						product.setDefaultPrice(request.getPrice());
						product.setName(request.getName());
						product.setDefaultPrice(request.getPrice());
						ProductClass proClass=service.findProductClassById(request.getClass_id());
						if(proClass!=null)
							product.setProductClass(proClass);
						service.updatePublicProduct(product);
					}else{
						if(shopUser.getShop()!=null){
							service.priceShopProduct(shopUser.getShop().getId(), product.getId() ,request.getPrice());
						}
					}
					response.setSuccess(true);
				}else{
					ResponseError error=new ResponseError();
					error.setCode(Constant.VALUE_NOT_EXIST);
					error.setMsg("product not exist!");
					response.setSuccess(false);
					response.setError(error);
				}
				
			}else{
				ResponseError error=new ResponseError();
				error.setCode(Constant.USER_NOT_LOGIN_CODE);
				error.setMsg("shop user not login!");
				response.setSuccess(false);
				response.setError(error);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ResponseError error = new ResponseError();
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
	@GET
	@Path("/goods")
	public String goods(@QueryParam("token") String token,@QueryParam("p") String p,@QueryParam("start") String start,@QueryParam("count") String count){
		QueryGoodResponse response=new QueryGoodResponse();
		try {
			ShopUser shopUser=getShopUserByToken(token);
			if(shopUser!=null){
				Shop shop=service.findShopByShopUserId(shopUser.getId());
				if(shop!=null){
					SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					List<ShopProductPrice> goodPrices=service.findPagedShopGoodPriceInfo(shop.getId(),Integer.parseInt(start),Integer.parseInt(count));
					QueryGoodResponseData data=new QueryGoodResponseData(); 
					List<ResponseQueryGood> respGoods=new ArrayList<ResponseQueryGood>();
					if(goodPrices!=null&&goodPrices.size()>0){
						for(ShopProductPrice price:goodPrices){
							ResponseQueryGood good=new ResponseQueryGood();
							good.setId(price.getProduct().getId());
							good.setName(price.getProduct().getName());
							good.setPrice(price.getPrice());
							good.setIs_public(true);
							good.setSell(true);
							good.setTime(format.format(price.getProduct().getAddTime()));
							ProductClass proClass=price.getProduct().getProductClass();
							if(proClass!=null){
								ResponseGoodClass goodClass=new ResponseGoodClass();
								goodClass.setId(proClass.getId());
								goodClass.setIs_public(true);
								goodClass.setName(proClass.getName());
								goodClass.setTime(format.format(proClass.getAddTime()));
								good.setGoodClass(goodClass);
								good.setGoodClass(goodClass);
							}
							respGoods.add(good);
						}
						data.setGoods(respGoods);
						data.setCount(respGoods.size());
						data.setCursor_next(respGoods.size()+Integer.parseInt(start));
						response.setData(data);
					}
					response.setSuccess(true);
				}else{
					ResponseError error=new ResponseError();
					error.setCode(Constant.VALUE_NOT_EXIST);
					error.setMsg("shop not be created!");
					response.setSuccess(false);
					response.setError(error);
				}
			}else{
				ResponseError error=new ResponseError();
				error.setCode(Constant.USER_NOT_LOGIN_CODE);
				error.setMsg("shop user not login!");
				response.setSuccess(false);
				response.setError(error);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ResponseError error = new ResponseError();
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
	@GET
	@Path("/goods/class")
	public String goodsClass(@QueryParam("token") String token,@QueryParam("p") String p,@QueryParam("start") String start,@QueryParam("count") String count){
		QueryGoodsByClassResponse response=new QueryGoodsByClassResponse();
		try {
			ShopUser shopUser=getShopUserByToken(token);
			if(shopUser!=null){
				SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				List<ProductClass> proClasses=service.findPagedProductClass(Integer.parseInt(start),Integer.parseInt(count));
				if(proClasses!=null&&proClasses.size()>0){
					QueryGoodsByClassResponseData data=new QueryGoodsByClassResponseData();
					List<ResponseQueryByClassGoods> resClass=new ArrayList<ResponseQueryByClassGoods>();
					for(ProductClass proClass:proClasses){
						ResponseQueryByClassGoods goodClass=new ResponseQueryByClassGoods();
						goodClass.setId(proClass.getId());
						goodClass.setIs_public(true);
						goodClass.setName(proClass.getName());
						goodClass.setTime(format.format(proClass.getAddTime()));
						
						List<ResponseQueryByClassGood> goods=new ArrayList<ResponseQueryByClassGood>(); 
						List<Product> classPros=service.findProductsByClassId(proClass.getId());
						if(classPros!=null&&classPros.size()>0){
							for(Product pro:classPros){
								ResponseQueryByClassGood good=new ResponseQueryByClassGood();
								good.setId(pro.getId());
								good.setIs_public(true);
								good.setName(pro.getName());
								good.setPrice(pro.getDefaultPrice());
								good.setSell(true);
								good.setTime(format.format(pro.getAddTime()));
								goods.add(good);
							}
							goodClass.setGood(goods);
						}
						resClass.add(goodClass);
					}
					data.setClass_goods(resClass);
				}
				response.setSuccess(true);	
			}else{
				ResponseError error=new ResponseError();
				error.setCode(Constant.USER_NOT_LOGIN_CODE);
				error.setMsg("shop user not login!");
				response.setSuccess(false);
				response.setError(error);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ResponseError error = new ResponseError();
			error.setCode(Constant.SYSTEM_EXCEPTION_CODE);
			error.setMsg(e.getMessage());
			response.setSuccess(false);
			response.setError(error);
		}
		JSONObject obj=JSONObject.fromObject(response);
		return obj.toString();
	}
	
	
	private ShopUser getShopUserByToken(String token){
		Object obj=CacheUtil.getObj(token);
		if(obj!=null){
			Session session=(Session)obj;
			Object user=session.getAttribute("shopUser");
			if(user!=null)
				return (ShopUser)user;
		}
		return null;
	}
	
}
