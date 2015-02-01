package com.uf.rest.restful;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.sf.json.JSONObject;

import com.uf.rest.bean.Constant;
import com.uf.rest.bean.ResponseError;
import com.uf.rest.bean.Session;
import com.uf.rest.bean.push.ChangeOrderStatePushContent;
import com.uf.rest.bean.push.PushShop;
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
import com.uf.rest.bean.request.UpdateOrderStateRequest;
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
import com.uf.rest.bean.response.DrawDetailResponse;
import com.uf.rest.bean.response.DrawDetailResponseData;
import com.uf.rest.bean.response.GetClientVersionResponse;
import com.uf.rest.bean.response.GetClientVersionResponseData;
import com.uf.rest.bean.response.GetShopUserInfoResponse;
import com.uf.rest.bean.response.GetShopUserInfoResponseData;
import com.uf.rest.bean.response.IsUserExistResponse;
import com.uf.rest.bean.response.IsUserExistResponseData;
import com.uf.rest.bean.response.OpenCloseShopResponse;
import com.uf.rest.bean.response.OrderResponseGood;
import com.uf.rest.bean.response.QueryGoodClassResponse;
import com.uf.rest.bean.response.QueryGoodClassResponseData;
import com.uf.rest.bean.response.QueryGoodResponse;
import com.uf.rest.bean.response.QueryGoodResponseData;
import com.uf.rest.bean.response.QueryGoodsByClassResponse;
import com.uf.rest.bean.response.QueryGoodsByClassResponseData;
import com.uf.rest.bean.response.QueryGoodsSellResponse;
import com.uf.rest.bean.response.QueryGoodsSellResponseData;
import com.uf.rest.bean.response.QueryShopInfoResponse;
import com.uf.rest.bean.response.QueryShopInfoResponseData;
import com.uf.rest.bean.response.ResponseBankCard;
import com.uf.rest.bean.response.ResponseCoordinate;
import com.uf.rest.bean.response.ResponseDrawDetail;
import com.uf.rest.bean.response.ResponseGood;
import com.uf.rest.bean.response.ResponseGoodClass;
import com.uf.rest.bean.response.ResponseIncome;
import com.uf.rest.bean.response.ResponseLocation;
import com.uf.rest.bean.response.ResponseOrderCount;
import com.uf.rest.bean.response.ResponseOrderState;
import com.uf.rest.bean.response.ResponseOrderToShopUser;
import com.uf.rest.bean.response.ResponseQueryByClassGood;
import com.uf.rest.bean.response.ResponseQueryByClassGoods;
import com.uf.rest.bean.response.ResponseQueryGood;
import com.uf.rest.bean.response.ResponseSell;
import com.uf.rest.bean.response.ResponseShop;
import com.uf.rest.bean.response.ResponseShopClassGoods;
import com.uf.rest.bean.response.ResponseUser;
import com.uf.rest.bean.response.ResponseVisitCount;
import com.uf.rest.bean.response.ResponseWithDraw;
import com.uf.rest.bean.response.SellHomeResponse;
import com.uf.rest.bean.response.SellHomeResponseData;
import com.uf.rest.bean.response.SellIncomeResponse;
import com.uf.rest.bean.response.SellIncomeResponseData;
import com.uf.rest.bean.response.SellOrderDealReponse;
import com.uf.rest.bean.response.SellOrderDealReponseData;
import com.uf.rest.bean.response.SellOrderResponse;
import com.uf.rest.bean.response.SellOrderResponseData;
import com.uf.rest.bean.response.SellVisitResponse;
import com.uf.rest.bean.response.SellVisitResponseData;
import com.uf.rest.bean.response.ShopHomeResponse;
import com.uf.rest.bean.response.ShopHomeResponseData;
import com.uf.rest.bean.response.ShopIncomeResponse;
import com.uf.rest.bean.response.ShopIncomeResponseData;
import com.uf.rest.bean.response.ShopOrderResponse;
import com.uf.rest.bean.response.ShopOrderResponseData;
import com.uf.rest.bean.response.UpdateGoodClassResponse;
import com.uf.rest.bean.response.UpdateGoodResponse;
import com.uf.rest.bean.response.UpdateOrderStateResponse;
import com.uf.rest.bean.response.UpdateShopResponse;
import com.uf.rest.bean.response.UserChangePasswordResponse;
import com.uf.rest.bean.response.UserLoginResponse;
import com.uf.rest.bean.response.UserLoginResponseData;
import com.uf.rest.bean.response.UserLogoutResponse;
import com.uf.rest.bean.response.UserRegistResponse;
import com.uf.rest.bean.response.UserRegistResponseData;
import com.uf.rest.entity.ClientVersion;
import com.uf.rest.entity.Order;
import com.uf.rest.entity.OrderDetail;
import com.uf.rest.entity.OrderStateHistory;
import com.uf.rest.entity.Product;
import com.uf.rest.entity.ProductClass;
import com.uf.rest.entity.Shop;
import com.uf.rest.entity.ShopBankCard;
import com.uf.rest.entity.ShopProductPrice;
import com.uf.rest.entity.ShopUser;
import com.uf.rest.entity.ShopVisitRecord;
import com.uf.rest.entity.ShopWithDrawRecord;
import com.uf.rest.entity.User;
import com.uf.rest.exception.UserExistException;
import com.uf.rest.service.CustomService;
import com.uf.rest.service.ServiceFactory;
import com.uf.rest.service.ShopService;
import com.uf.rest.util.CacheUtil;
import com.uf.rest.util.DateUtil;
import com.uf.rest.util.FileUtil;
import com.uf.rest.util.PushClient;

@Singleton
@Path("/shop")
public class ShopAction {
	private Logger logger = LogManager.getLogger(ShopAction.class);
	private ShopService service=ServiceFactory.getService(ShopService.class);
	private CustomService customService=ServiceFactory.getService(CustomService.class);
	private PushClient  pushClient=ServiceFactory.getService(PushClient.class);
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
			JSONObject obj=JSONObject.fromObject(request);
			logger.error(" <-----"+obj.toString()+"---->", e);
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
				String filePath=Constant.TEMP_PATH+"shopUserIdImag";
				String absoPath=FileUtil.writeContentToFile(request.getCard_image(), filePath,shopUser.getId()+".jpg");
				shopUser.setIdCardPhotoPath(absoPath);
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
			JSONObject obj=JSONObject.fromObject(request);
			logger.error("  <-----"+obj.toString()+"---->", e);
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
			logger.error("<-----"+token+"---->", e);
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
			logger.error(" <-----"+userName+"---->", e);
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
				data.setId(user.getId());
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
			JSONObject obj=JSONObject.fromObject(request);
			logger.error(" <-----"+obj.toString()+"---->", e);
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
			JSONObject obj=JSONObject.fromObject(request);
			logger.error(" <-----"+obj.toString()+"---->", e);
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
				Shop shop=service.findShopByShopUserId(shopUser.getId());
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
			JSONObject obj=JSONObject.fromObject(request);
			logger.error(" <-----"+obj.toString()+"---->", e);
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
				String shopIconPath=Constant.TEMP_PATH+"shopImages/"+shopUser.getId(); 
				String absoPath=FileUtil.writeContentToFile(request.getIcon(), shopIconPath,"shopIcon.jpg");
				shop.setShopPhotoPath(absoPath);
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
			JSONObject obj=JSONObject.fromObject(request);
			logger.error(" <-----"+obj.toString()+"---->", e);
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
				result.append(a+",");
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
					String shopIconPath=Constant.TEMP_PATH+"shopImages/"+shopUser.getId(); 
					String absPath=FileUtil.writeContentToFile(request.getIcon(), shopIconPath,"shopIcon.jpg");
					shop.setShopPhotoPath(absPath);
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
			JSONObject obj=JSONObject.fromObject(request);
			logger.error(" <-----"+obj.toString()+"---->", e);
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
			logger.error(" <-----"+token+"---->", e);
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
	@Path("/goods/sell")
	public String goodsSell(@QueryParam("token") String token,@QueryParam("p") String p,@QueryParam("start") String start,@QueryParam("count") String count){
		QueryGoodsSellResponse response=new QueryGoodsSellResponse();
		try {
			ShopUser shopUser=getShopUserByToken(token);
			if(shopUser!=null){
				Shop shop=service.findShopByShopUserId(shopUser.getId());
				if(shop!=null){
					//List<ShopProductPrice> products=service.findPagedShopGoodPriceInfo(shop.getId(), Integer.parseInt(start), Integer.parseInt(count));
					List<Order> orders=service.findShopOrderByOrderState(shop.getId(), Constant.ORDER_STATE_COMPLETE);
					Map<Integer, Integer> counts=new HashMap<Integer, Integer>();
					Map<Integer, Product> prducts=new HashMap<Integer, Product>();
					if(orders!=null&&orders.size()>0){
						for(Order order:orders){
							Set<OrderDetail> details=order.getOrderDetails();
							if(details!=null&&details.size()>0){
								for(OrderDetail detail:details){
									int sellCount=detail.getCount();
									Product product=detail.getProduct();
									if(product!=null){
										Integer id=product.getId();
										Integer preCount=counts.get(id);
										if(preCount==null){
											counts.put(id, sellCount);
										}else{
											counts.put(id, sellCount+preCount);
										}
										if(prducts.get(id)==null){
											prducts.put(id, product);
										}
									}
									
								}
							}
						}
					}
					QueryGoodsSellResponseData data=new QueryGoodsSellResponseData();
					List<ResponseSell> resSells=new ArrayList<ResponseSell>();
					for(Integer key:prducts.keySet()){
						Product product=prducts.get(key);
						Integer sellCount=counts.get(key);
						if(product!=null){
							ResponseSell resSell=new ResponseSell();
							resSell.setCount(sellCount);
							ResponseGood good=new  ResponseGood();
							good.setId(product.getId());
							good.setName(product.getName());
							good.setPrice(product.getDefaultPrice());
							resSell.setGood(good);
							resSells.add(resSell);
						}
					}
					data.setSell(resSells);
					response.setData(data);
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
		} catch (Exception e) {
			logger.error(" <-----"+start+";"+count+"---->", e);
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
			JSONObject obj=JSONObject.fromObject(request);
			logger.error(" <-----"+obj.toString()+"---->", e);
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
			JSONObject obj=JSONObject.fromObject(request);
			logger.error(" <-----"+obj.toString()+"---->", e);
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
						if(proClass.getAddTime()!=null){
							goodClass.setTime(format.format(proClass.getAddTime()));
						}
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
			logger.error(" <-----"+start+";"+count+"---->", e);
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
			JSONObject obj=JSONObject.fromObject(request);
			logger.error(" <-----"+obj.toString()+"---->", e);
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
					Shop shop=service.findShopByShopUserId(shopUser.getId());
					if(shop!=null){
						service.deleteShopProductPrice(shop.getId(), request.getId());
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
			JSONObject obj=JSONObject.fromObject(request);
			logger.error(" <-----"+obj.toString()+"---->", e);
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
						Shop shop=service.findShopByShopUserId(shopUser.getId());
						if(shop!=null){
							service.priceShopProduct(shop.getId(), product.getId() ,request.getPrice());
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
			JSONObject obj=JSONObject.fromObject(request);
			logger.error(" <-----"+obj.toString()+"---->", e);
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
							if(price.getProduct()!=null){
								good.setId(price.getProduct().getId());
								good.setName(price.getProduct().getName());
								good.setTime(format.format(price.getProduct().getAddTime()));
								ProductClass proClass=price.getProduct().getProductClass();
								if(proClass!=null){
									ResponseGoodClass goodClass=new ResponseGoodClass();
									goodClass.setId(proClass.getId());
									goodClass.setIs_public(true);
									goodClass.setName(proClass.getName());
									if(proClass.getAddTime()!=null){
										goodClass.setTime(format.format(proClass.getAddTime()));
									}
									good.setGoodClass(goodClass);
									good.setGoodClass(goodClass);
								}
							}
							good.setPrice(price.getPrice());
							good.setIs_public(true);
							good.setSell(true);
							
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
			logger.error(" <-----"+start+";"+count+"---->", e);
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
						if(proClass.getAddTime()!=null){
							goodClass.setTime(format.format(proClass.getAddTime()));
						}
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
								if(pro.getAddTime()!=null){
									good.setTime(format.format(pro.getAddTime()));
								}
								goods.add(good);
							}
							goodClass.setGood(goods);
						}
						resClass.add(goodClass);
					}
					data.setClass_goods(resClass);
					data.setCount(resClass.size());
					data.setCursor_next(Integer.parseInt(start)+resClass.size());
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
			logger.error(" <-----"+start+";"+count+"---->", e);
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
	@Path("/home")
	public String shopHome(@QueryParam("token") String token,@QueryParam("p") String p){
		ShopHomeResponse response=new ShopHomeResponse();
		try {
			ShopUser shopUser=getShopUserByToken(token);
			if(shopUser!=null){
				Shop shop=service.findShopByShopUserId(shopUser.getId());
				if(shop!=null){
					ShopHomeResponseData data=new ShopHomeResponseData(); 
					data.setShop_id(shop.getId());
					List<ShopProductPrice> priceProducts=service.findShopGoodPriceInfoByShopId(shop.getId());
					if(priceProducts!=null){
						data.setGood_count(priceProducts.size());
					}else{
						data.setGood_count(0);
					}
					List<Order> orders=service.findAllSucessShopOrder(shop.getId());
					
					if(orders!=null){
						data.setOrder_count(orders.size());
						data.setIncome(countOrdersTotalMoney(orders));
					}else{
						data.setOrder_count(0);
						data.setIncome(0f);
					}
					Date today=new Date();
					Date preDay=DateUtil.nextSomeDays(today,-1);
					List<Order> todayOrder=service.findOneDaySucessOrders(shop.getId(),today);
					List<Order> preDayOrder=service.findOneDaySucessOrders(shop.getId(),preDay);
					float todayTotal=countOrdersTotalMoney(todayOrder);
					float predayTotal=countOrdersTotalMoney(preDayOrder);
					float busi=todayTotal;
					if(predayTotal!=0){
						busi=(todayTotal-predayTotal)/predayTotal;
					}
					data.setBusiness(busi);
					response.setData(data);
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
		} catch (Exception e) {
			logger.error(" <--------->", e);
			ResponseError error = new ResponseError();
			error.setCode(Constant.SYSTEM_EXCEPTION_CODE);
			error.setMsg(e.getMessage());
			response.setSuccess(false);
			response.setError(error);
		}		
		JSONObject obj=JSONObject.fromObject(response);
		return obj.toString();
	}
	private float countOrdersTotalMoney(List<Order> orders){
		Float totalMoney=0.0f;
		for(Order order:orders){
			Set<OrderDetail> details=order.getOrderDetails();
			if(details!=null){
				for(OrderDetail detail:details){
					float prices=detail.getPrice()*detail.getCount();
					totalMoney=totalMoney+prices;
				}
			}
		}
		return totalMoney;
	}
	private float countOrderMoney(Order order){
		Float totalMoney=0.0f;
		if(order!=null){
			Set<OrderDetail> details=order.getOrderDetails();
			if(details!=null){
				for(OrderDetail detail:details){
					float prices=detail.getPrice()*detail.getCount();
					totalMoney=totalMoney+prices;
				}
			}
		}
		return totalMoney;
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@GET
	@Path("/income")
	public String shopIncome(@QueryParam("token") String token,@QueryParam("p") String p){
		ShopIncomeResponse response=new ShopIncomeResponse();
		try {
			ShopUser shopUser=getShopUserByToken(token);
			if(shopUser!=null){
				Shop shop=service.findShopByShopUserId(shopUser.getId());
				if(shop!=null){
					ShopIncomeResponseData data=new ShopIncomeResponseData(); 
					if(shopUser.getBalance()!=null){
						data.setBalance(shopUser.getBalance().floatValue());
					}
					List<Order> orders=service.findAllSucessShopOrder(shop.getId());
					if(orders!=null){
						data.setIncome(countOrdersTotalMoney(orders));
					}else{
						data.setIncome(0f);
					}
					ShopBankCard card=service.findShopBankCard(shop.getId());
					if(card!=null){
						data.setHas_card(true);
						ResponseBankCard bankCard=new ResponseBankCard();
						bankCard.setBank(card.getBankName());
						bankCard.setCard(card.getCardNumber());
						bankCard.setId(card.getId());
						bankCard.setName(card.getUserName());
						SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						bankCard.setTime(format.format(card.getAddTime()));
						data.setCard(bankCard);
					}else{
						data.setHas_card(false);
					}
					ResponseWithDraw withDraw=new ResponseWithDraw();
					List<ShopWithDrawRecord> withDraws=service.findInProcessWithdraw(shop.getId());
					if(withDraws!=null){
						withDraw.setCount(withDraws.size());
						float total=0.0f;
						for(ShopWithDrawRecord d:withDraws){
							total+=d.getMoney();
						}
						withDraw.setTotal(total);
						ShopWithDrawRecord lastR=service.findLastInprocessWithdraw(shop.getId());
						if(lastR!=null){
							withDraw.setLast(lastR.getMoney());
						}else{
							withDraw.setLast(0f);
						}
					}else{
						withDraw.setCount(0);
						withDraw.setLast(0f);
						withDraw.setTotal(0f);
					}
					data.setWithdraw(withDraw);
					response.setData(data);
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
		} catch (Exception e) {
			logger.error(" <--------->", e);
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
	@Path("/income/withdraw")
	public String shopIncomeWithdraw(@QueryParam("token") String token,@QueryParam("p") String p,@QueryParam("start") String start,@QueryParam("count") String count){
		DrawDetailResponse response=new DrawDetailResponse();
		try {
			ShopUser shopUser=getShopUserByToken(token);
			if(shopUser!=null){
				Shop shop=service.findShopByShopUserId(shopUser.getId());
				if(shop!=null){
					SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					DrawDetailResponseData data=new DrawDetailResponseData();
					List<ShopWithDrawRecord> records=service.findPagedWithdraw(shop.getId(), Integer.parseInt(start), Integer.parseInt(count));
					if(records!=null&&records.size()>0){
						List<ResponseDrawDetail> resDetail=new ArrayList<ResponseDrawDetail>();
						for(ShopWithDrawRecord record:records){
							ResponseDrawDetail resRecord=new  ResponseDrawDetail();
							resRecord.setMoney(record.getMoney());
							resRecord.setDay(format.format(record.getWithdrawTime()));
							resDetail.add(resRecord);
						}
						data.setWithdraw(resDetail);
					}
					response.setData(data);
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
		} catch (Exception e) {
			logger.error(" <-----"+start+";"+count+"---->", e);
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
	@Path("/order/get")
	public String shopOrder(@QueryParam("token") String token,@QueryParam("p") String p,@QueryParam("start") String start,@QueryParam("count") String count){
		ShopOrderResponse response=new ShopOrderResponse();
		try {
			ShopUser shopUser=getShopUserByToken(token);
			if(shopUser!=null){
				Shop shop=service.findShopByShopUserId(shopUser.getId());
				if(shop!=null){
					ShopOrderResponseData data=generateShopOrderResponseByOrderState(shop.getId(),Constant.ORDER_STATE_WAITGET,Integer.parseInt(start),Integer.parseInt(count));
					response.setData(data);
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
		} catch (Exception e) {
			logger.error(" <-----"+start+";"+count+"---->", e);
			ResponseError error = new ResponseError();
			error.setCode(Constant.SYSTEM_EXCEPTION_CODE);
			error.setMsg(e.getMessage());
			response.setSuccess(false);
			response.setError(error);
		}	
		JSONObject obj=JSONObject.fromObject(response);
		return obj.toString();
	}
	private ShopOrderResponseData generateShopOrderResponseByOrderState(Integer shopId,Integer orderState,Integer start,Integer count){
		ShopOrderResponseData data=new ShopOrderResponseData();
		List<Order> orders=service.findPagedShopOrderByOrderState(shopId, orderState, start, count);
		if(orders!=null&&orders.size()>0){
			data.setCount(orders.size());
			data.setCursor_next(orders.size()+start);
			List<ResponseOrderToShopUser> resOrders=new ArrayList<ResponseOrderToShopUser>();
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			for(Order order:orders){
				ResponseOrderToShopUser resOrder=new ResponseOrderToShopUser();
				if(order.getDeliverAddress()!=null){
					resOrder.setDeliver_address_id(order.getDeliverAddress().getId());
				}
				
				resOrder.setId(order.getId());
				resOrder.setPayment(String.valueOf(order.getPaymentType()));
				if(order.getPickAddress()!=null){
					resOrder.setPick_address_id(order.getPickAddress().getId());
				}
				Set<OrderStateHistory> stateHis=order.getOrderStatesHistory();
				if(stateHis!=null&&stateHis.size()>0){
					List<ResponseOrderState> resStates=new ArrayList<ResponseOrderState>();
					for(OrderStateHistory state:stateHis){
						ResponseOrderState resState=new ResponseOrderState();
						resState.setName(state.getState().toString());
						resState.setTime(format.format(state.getTime()));
						resStates.add(resState);
					}
					resOrder.setState(resStates);
				}
				if(order.getCreateTime()!=null){
					resOrder.setTime(format.format(order.getCreateTime()));
				}
				User user=order.getUser();
				ResponseUser responseUser=new ResponseUser();
				responseUser.setId(user.getId());
				responseUser.setName(user.getName());
				responseUser.setPhone("");
				resOrder.setUser(responseUser);
				if((order.getPaymentType()==Constant.ORDER_PAYTYPE_CASH&&order.getOrderState()>=Constant.ORDER_STATE_PROCESSING)||(order.getPaymentType()!=Constant.ORDER_PAYTYPE_CASH&&order.getOrderState()>=Constant.ORDER_STATE_WAITGET)){
					resOrder.setPaid(true);
				}else{
					resOrder.setPaid(false);
				}
				Set<OrderDetail> details=order.getOrderDetails();
				if(details!=null&&details.size()>0){
					List<OrderResponseGood> resGoods=new ArrayList<OrderResponseGood>();
					for(OrderDetail detail:details){
						OrderResponseGood resGood=new OrderResponseGood();
						resGood.setId(detail.getProduct().getId());
						resGood.setName(detail.getProduct().getName());
						resGood.setCount(detail.getCount());
						resGood.setPrice(detail.getPrice());
						resGoods.add(resGood);
					}
					resOrder.setGood(resGoods);
				}
				resOrders.add(resOrder);
			}
			data.setOrder(resOrders);
		}
		return data;
		
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@GET
	@Path("/order/process")
	public String shopOrderProcess(@QueryParam("token") String token,@QueryParam("p") String p,@QueryParam("start") String start,@QueryParam("count") String count){
		ShopOrderResponse response=new ShopOrderResponse();
		try {
			ShopUser shopUser=getShopUserByToken(token);
			if(shopUser!=null){
				Shop shop=service.findShopByShopUserId(shopUser.getId());
				if(shop!=null){
					ShopOrderResponseData data=generateShopOrderResponseByOrderState(shop.getId(),Constant.ORDER_STATE_PROCESSING,Integer.parseInt(start),Integer.parseInt(count));
					response.setData(data);
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
		} catch (Exception e) {
			logger.error(" <-----"+start+";"+count+"---->", e);
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
	@Path("/order/finish")
	public String shopOrderFinish(@QueryParam("token") String token,@QueryParam("p") String p,@QueryParam("start") String start,@QueryParam("count") String count){
		ShopOrderResponse response=new ShopOrderResponse();
		try {
			ShopUser shopUser=getShopUserByToken(token);
			if(shopUser!=null){
				Shop shop=service.findShopByShopUserId(shopUser.getId());
				if(shop!=null){
					ShopOrderResponseData data=generateShopOrderResponseByOrderState(shop.getId(),Constant.ORDER_STATE_COMPLETE,Integer.parseInt(start),Integer.parseInt(count));
					response.setData(data);
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
		} catch (Exception e) {
			logger.error(" <-----"+start+";"+count+"---->", e);
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
	@Path("/order/update/state")
	public String updateOrderState(UpdateOrderStateRequest request){
		UpdateOrderStateResponse response=new UpdateOrderStateResponse();
		try {
			ShopUser shopUser=getShopUserByToken(request.getToken());
			if(shopUser!=null){
				Integer orders[]=request.getOrder_id();
				if(orders!=null&&orders.length>0){
					for(Integer orderId:orders){
						service.updateOrderState(orderId, request.getState());
						pushChangeOrderStateMessage(orderId);
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
			JSONObject obj=JSONObject.fromObject(request);
			logger.error(" <-----"+obj.toString()+"---->", e);
			ResponseError error = new ResponseError();
			error.setCode(Constant.SYSTEM_EXCEPTION_CODE);
			error.setMsg(e.getMessage());
			response.setSuccess(false);
			response.setError(error);
		}	
		JSONObject obj=JSONObject.fromObject(response);
		return obj.toString();
	}
	private void pushChangeOrderStateMessage(Integer orderId){
		Order order=customService.findOrderById(orderId);
		if(order!=null){
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Shop shop=order.getShop();
			ShopUser shopUser=shop.getShopUser();
			ChangeOrderStatePushContent  message=new ChangeOrderStatePushContent();
			if(order.getOrderState()!=null){
				message.setState(order.getOrderState().toString());
			}
			if(shop!=null){
				PushShop pushShop=new PushShop();
				pushShop.setId(shop.getId());
				pushShop.setName(shop.getName());
				if(shop.getContactStyle()!=null){
					pushShop.setPhone(shop.getContactStyle().split(","));	
				}
				message.setShop(pushShop);
			}
			message.setId(order.getId());
			
			pushClient.pushMessageToAll(shopUser.getId().toString(), JSONObject.fromObject(message).toString());
		}
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@GET
	@Path("/sell/home")
	public String sellHome(@QueryParam("token") String token,@QueryParam("p") String p,@QueryParam("start") String start,@QueryParam("count") String count){
		SellHomeResponse response=new SellHomeResponse();
		try {
			ShopUser shopUser=getShopUserByToken(token);
			if(shopUser!=null){
				Shop shop=service.findShopByShopUserId(shopUser.getId());
				if(shop!=null){
					SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
					Date begin=null;
					Date end=null;
					if("0".equals(start)){
						begin=service.findShopFirstSuccessOrderDate(shop.getId());
					}else{
						begin=format.parse(start);
					}
					if(begin!=null){
						end=DateUtil.nextSomeDays(begin,Integer.parseInt(count));
						List<Order> orders=service.findSuccessShopOrder(shop.getId(), begin, end);
						Map<String, Float> incomes=new HashMap<String, Float>();
						Map<String, Integer> orderCounts=new HashMap<String, Integer>();
						if(orders!=null&&orders.size()>0){
							for(Order order:orders){
								float orderMoney=countOrderMoney(order);
								String date=format.format(order.getCreateTime());
								Float total=incomes.get(date);
								Integer totalCount=orderCounts.get(date);
								if(total==null){
									total=0.0f;	
								}
								if(totalCount==null){
									totalCount=0;
								}
								incomes.put(date, orderMoney+total);
								orderCounts.put(date, totalCount+1);
							}
						}
						
						SellHomeResponseData data=new SellHomeResponseData();
						data.setCount(incomes.keySet().size());
						if(service.countShopSucessOrderAfterDate(shop.getId(), end)>0){
							Date nextDay=DateUtil.nextSomeDays(end,1);
							data.setCursor_next(format.format(nextDay));
						}else{
							data.setCursor_next("0");
						}
						
						List<ResponseIncome> resIncomes=new ArrayList<ResponseIncome>();
						for(String key:incomes.keySet()){
							ResponseIncome resIncome=new ResponseIncome();
							resIncome.setDate(key);
							resIncome.setMoney(incomes.get(key));
							resIncomes.add(resIncome);
						}
						data.setIncome(resIncomes);
						List<ResponseOrderCount> resOrders=new ArrayList<ResponseOrderCount>();
						for(String key:orderCounts.keySet()){
							ResponseOrderCount resOrder=new ResponseOrderCount();
							resOrder.setCount(orderCounts.get(key));
							resOrder.setDate(key);
							resOrders.add(resOrder);
						}
						data.setOrder(resOrders);
						response.setData(data);
					}
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
		} catch (Exception e) {
			logger.error(" <-----"+start+";"+count+"---->", e);
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
	@Path("/sell/income")
	public String sellIncome(@QueryParam("token") String token,@QueryParam("p") String p,@QueryParam("start") String start,@QueryParam("count") String count){
		SellIncomeResponse response=new SellIncomeResponse();
		try {
			ShopUser shopUser=getShopUserByToken(token);
			if(shopUser!=null){
				Shop shop=service.findShopByShopUserId(shopUser.getId());
				if(shop!=null){
					SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
					Date begin=null;
					Date end=null;
					if("0".equals(start)){
						begin=service.findShopFirstSuccessOrderDate(shop.getId());
					}else{
						begin=format.parse(start);
					}
					if(begin!=null){
						end=DateUtil.nextSomeDays(begin,Integer.parseInt(count));
						List<Order> orders=service.findSuccessShopOrder(shop.getId(), begin, end);
						Map<String, Float> incomes=new HashMap<String, Float>();
						if(orders!=null&&orders.size()>0){
							for(Order order:orders){
								float orderMoney=countOrderMoney(order);
								String date=format.format(order.getCreateTime());
								Float total=incomes.get(date);
								if(total==null){
									total=0.0f;	
								}
								incomes.put(date, orderMoney+total);
							}
						}
						
						SellIncomeResponseData data=new SellIncomeResponseData();
						data.setCount(incomes.keySet().size());
						if(service.countShopSucessOrderAfterDate(shop.getId(), end)>0){
							Date nextDay=DateUtil.nextSomeDays(end,1);
							data.setCursor_next(format.format(nextDay));
						}else{
							data.setCursor_next("0");
						}
						
						List<ResponseIncome> resIncomes=new ArrayList<ResponseIncome>();
						for(String key:incomes.keySet()){
							ResponseIncome resIncome=new ResponseIncome();
							resIncome.setDate(key);
							resIncome.setMoney(incomes.get(key));
							resIncomes.add(resIncome);
						}
						data.setIncome(resIncomes);
						response.setData(data);
					}
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
		} catch (Exception e) {
			logger.error(" <-----"+start+";"+count+"---->", e);
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
	@Path("/sell/order")
	public String sellOrder(@QueryParam("token") String token,@QueryParam("p") String p,@QueryParam("start") String start,@QueryParam("count") String count){
		SellOrderResponse response=new SellOrderResponse();
		try {
			ShopUser shopUser=getShopUserByToken(token);
			if(shopUser!=null){
				Shop shop=service.findShopByShopUserId(shopUser.getId());
				if(shop!=null){
					SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
					Date begin=null;
					Date end=null;
					if("0".equals(start)){
						begin=service.findShopFirstSuccessOrderDate(shop.getId());
					}else{
						begin=format.parse(start);
					}
					if(begin!=null){
						end=DateUtil.nextSomeDays(begin,Integer.parseInt(count));
						List<Order> orders=service.findSuccessShopOrder(shop.getId(), begin, end);
						Map<String, Integer> orderCounts=new HashMap<String, Integer>();
						if(orders!=null&&orders.size()>0){
							for(Order order:orders){
								float orderMoney=countOrderMoney(order);
								String date=format.format(order.getCreateTime());
								Integer totalCount=orderCounts.get(date);
								if(totalCount==null){
									totalCount=0;
								}
								orderCounts.put(date, totalCount+1);
							}
						}
						
						SellOrderResponseData data=new SellOrderResponseData();
						if(service.countShopSucessOrderAfterDate(shop.getId(), end)>0){
							Date nextDay=DateUtil.nextSomeDays(end,1);
							data.setCursor_next(format.format(nextDay));
						}else{
							data.setCursor_next("0");
						}
						data.setCount(orderCounts.size());
						List<ResponseOrderCount> resOrders=new ArrayList<ResponseOrderCount>();
						for(String key:orderCounts.keySet()){
							ResponseOrderCount resOrder=new ResponseOrderCount();
							resOrder.setCount(orderCounts.get(key));
							resOrder.setDate(key);
							resOrders.add(resOrder);
						}
						data.setOrder(resOrders);
						response.setData(data);
					}
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
		} catch (Exception e) {
			logger.error(" <-----"+start+";"+count+"---->", e);
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
	@Path("/sell/visit")
	public String sellVisit(@QueryParam("token") String token,@QueryParam("p") String p,@QueryParam("start") String start,@QueryParam("count") String count){
		SellVisitResponse response=new SellVisitResponse();
		try {
			ShopUser shopUser=getShopUserByToken(token);
			if(shopUser!=null){
				Shop shop=service.findShopByShopUserId(shopUser.getId());
				if(shop!=null){
					SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
					Date begin=null;
					Date end=null;
					if("0".equals(start)){
						begin=service.findShopFirstVisitDate(shop.getId());
					}else{
						begin=format.parse(start);
					}
					if(begin!=null){
						end=DateUtil.nextSomeDays(begin,Integer.parseInt(count));
						List<ShopVisitRecord> records=service.findShopVisitRecord(shop.getId(), begin, end);
						Map<String, Integer> recordCounts=new HashMap<String, Integer>();
						if(records!=null&&records.size()>0){
							for(ShopVisitRecord record:records){
								String date=format.format(record.getDate());
								Integer totalCount=recordCounts.get(date);
								if(totalCount==null){
									totalCount=0;
								}
								recordCounts.put(date, totalCount+record.getVisitCount());
							}
						}
						SellVisitResponseData data=new SellVisitResponseData();
						data.setCount(recordCounts.keySet().size());
						if(service.countShopVisitAfterDate(shop.getId(), end)>0){
							Date nextDay=DateUtil.nextSomeDays(end,1);
							data.setCursor_next(format.format(nextDay));
						}else{
							data.setCursor_next("0");
						}
						List<ResponseVisitCount> visits=new ArrayList<ResponseVisitCount>();
						for(String key:recordCounts.keySet()){
							ResponseVisitCount resVisit=new ResponseVisitCount();
							resVisit.setCount(recordCounts.get(key));
							resVisit.setDate(key);
							visits.add(resVisit);
						}
						data.setVisit(visits);
						response.setData(data);
						response.setSuccess(true);
					}
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
		} catch (Exception e) {
			logger.error(" <-----"+start+";"+count+"---->", e);
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
	@Path("/sell/order/deal")
	public String sellOrderDeal(@QueryParam("token") String token,@QueryParam("p") String p,@QueryParam("start") String start,@QueryParam("count") String count){
		SellOrderDealReponse response = new SellOrderDealReponse();
		try {
			ShopUser shopUser = getShopUserByToken(token);
			if (shopUser != null) {
				Shop shop = service.findShopByShopUserId(shopUser.getId());
				if (shop != null) {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					Date begin = null;
					Date end = null;
					if ("0".equals(start)) {
						begin = service.findShopFirstSuccessOrderDate(shop.getId());
					} else {
						begin = format.parse(start);
					}
					if (begin != null) {
						end = DateUtil.nextSomeDays(begin, Integer.parseInt(count));
						List<Order> orders = service.findSuccessShopOrder(shop.getId(), begin, end);
						Map<String, Integer> orderCounts = new HashMap<String, Integer>();
						if (orders != null && orders.size() > 0) {
							for (Order order : orders) {
								String date = format.format(order.getCreateTime());
								Integer totalCount = orderCounts.get(date);
								if (totalCount == null) {
									totalCount = 0;
								}
								orderCounts.put(date, totalCount + 1);
							}
						}

						SellOrderDealReponseData data = new SellOrderDealReponseData();
						if (service.countShopSucessOrderAfterDate(shop.getId(), end) > 0) {
							Date nextDay = DateUtil.nextSomeDays(end, 1);
							data.setCursor_next(format.format(nextDay));
						} else {
							data.setCursor_next("0");
						}
						List<ResponseOrderCount> resOrders = new ArrayList<ResponseOrderCount>();
						for (String key : orderCounts.keySet()) {
							ResponseOrderCount resOrder = new ResponseOrderCount();
							resOrder.setCount(orderCounts.get(key));
							resOrder.setDate(key);
							resOrders.add(resOrder);
						}
						data.setOrder(resOrders);
						response.setData(data);
					}
					response.setSuccess(true);
				} else {
					ResponseError error = new ResponseError();
					error.setCode(Constant.VALUE_NOT_EXIST);
					error.setMsg("shop user has no shop !");
					response.setError(error);
					response.setSuccess(false);
				}
			} else {
				ResponseError error = new ResponseError();
				error.setCode(Constant.USER_NOT_LOGIN_CODE);
				error.setMsg("shop user not login!");
				response.setSuccess(false);
				response.setError(error);
			}
		} catch (Exception e) {
			logger.error(" <-----" + start + ";" + count + "---->", e);
			ResponseError error = new ResponseError();
			error.setCode(Constant.SYSTEM_EXCEPTION_CODE);
			error.setMsg(e.getMessage());
			response.setSuccess(false);
			response.setError(error);
		}
		JSONObject obj = JSONObject.fromObject(response);
		return obj.toString();
	}
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@GET
	@Path("/version/last")
	public String versionLast(@QueryParam("token") String token,@QueryParam("p") String p){
		GetClientVersionResponse response=new GetClientVersionResponse();
		try{
			ShopUser shopUser=getShopUserByToken(token);
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(shopUser!=null){
				ClientVersion version=service.findLastClientVersion();
				if(version!=null){
					GetClientVersionResponseData data=new GetClientVersionResponseData();
					data.setCode(version.getCode());
					data.setId(version.getId());
					data.setInfo(version.getInfo());
					data.setTime(format.format(version.getUpdateTime()));
					data.setUrl(version.getUrl());
					data.setVersion(version.getVersion());
					response.setData(data);
				}
				response.setSuccess(true);
			}else{
				ResponseError error=new ResponseError();
				error.setCode(Constant.USER_NOT_LOGIN_CODE);
				error.setMsg("shop user not login");
				response.setError(error);
				response.setSuccess(false);
			}
		}catch(Exception e){
			logger.error(" <--------->", e);
			com.uf.rest.bean.ResponseError error=new com.uf.rest.bean.ResponseError();
			error.setCode(Constant.SYSTEM_EXCEPTION_CODE);
			error.setMsg(e.getMessage());
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
