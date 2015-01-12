package com.uf.rest.restful;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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

import net.sf.json.JSONObject;

import com.uf.rest.bean.Constant;
import com.uf.rest.bean.ResponseError;
import com.uf.rest.bean.Session;
import com.uf.rest.bean.request.CreateOrderRequest;
import com.uf.rest.bean.request.CustomHomeRequest;
import com.uf.rest.bean.request.DeleteOrderRequest;
import com.uf.rest.bean.request.GoodPriceRequest;
import com.uf.rest.bean.request.RegistUserRequest;
import com.uf.rest.bean.request.RequestGood;
import com.uf.rest.bean.request.UpdateOrderRequest;
import com.uf.rest.bean.request.UserChangePasswordRequest;
import com.uf.rest.bean.request.UserLoginRequest;
import com.uf.rest.bean.request.UserLogoutRequest;
import com.uf.rest.bean.response.CreateOrderResponse;
import com.uf.rest.bean.response.CreateOrderResponseData;
import com.uf.rest.bean.response.CustomHomeResponse;
import com.uf.rest.bean.response.CustomHomeResponseData;
import com.uf.rest.bean.response.CustomProcessOrderCountResponse;
import com.uf.rest.bean.response.CustomProcessOrderCountResponseData;
import com.uf.rest.bean.response.DeleteOrderResponse;
import com.uf.rest.bean.response.GetGoodsResponse;
import com.uf.rest.bean.response.GetGoodsResponseData;
import com.uf.rest.bean.response.GoodPriceResponse;
import com.uf.rest.bean.response.GoodPriceResponseData;
import com.uf.rest.bean.response.IsUserExistResponse;
import com.uf.rest.bean.response.IsUserExistResponseData;
import com.uf.rest.bean.response.QueryOrderResponse;
import com.uf.rest.bean.response.QueryOrderResponseData;
import com.uf.rest.bean.response.ResponseClassGoods;
import com.uf.rest.bean.response.ResponseGood;
import com.uf.rest.bean.response.ResponseLocation;
import com.uf.rest.bean.response.ResponseOrder;
import com.uf.rest.bean.response.ResponseShop;
import com.uf.rest.bean.response.ShopGoodsPrice;
import com.uf.rest.bean.response.UpdateOrderResponse;
import com.uf.rest.bean.response.UserChangePasswordResponse;
import com.uf.rest.bean.response.UserLoginResponse;
import com.uf.rest.bean.response.UserLoginResponseData;
import com.uf.rest.bean.response.UserLogoutResponse;
import com.uf.rest.bean.response.UserRegistResponse;
import com.uf.rest.bean.response.UserRegistResponseData;
import com.uf.rest.entity.Order;
import com.uf.rest.entity.OrderAddress;
import com.uf.rest.entity.OrderDetail;
import com.uf.rest.entity.Product;
import com.uf.rest.entity.Shop;
import com.uf.rest.entity.ShopProductPrice;
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
		User user=getUserByToken(token);
		CustomProcessOrderCountResponse response=new CustomProcessOrderCountResponse();
		try{
			if(user!=null){
				int orderCount=customService.findUserProcessingOrderCount(user.getId());
				CustomProcessOrderCountResponseData data=new CustomProcessOrderCountResponseData();
				data.setCount(orderCount);
				response.setData(data);
				response.setSuccess(true);
			}else{
				ResponseError error=new ResponseError();
				error.setCode(Constant.USER_NOT_LOGIN_CODE);
				error.setMsg("user not login");
				response.setError(error);
				response.setSuccess(false);
			}
		}catch(Exception e){
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
	@Path("/goods")
    public String goods(@QueryParam("token") String token) {
		GetGoodsResponse response=new GetGoodsResponse();
		try{
			List<Product> products=customService.findAllProducts();
			if(products!=null){
				GetGoodsResponseData data=new GetGoodsResponseData();
				Map<String, List<ResponseGood>> classProducts=new HashMap<String, List<ResponseGood>>();
				for(Product p:products){
					String className=p.getProductClass().getName();
					List<ResponseGood> goods=classProducts.get(className);
					if(goods==null){
						goods=new ArrayList<ResponseGood>();
						classProducts.put(className, goods);
					}
					ResponseGood good=new ResponseGood();
					good.setId(p.getId());
					good.setName(p.getName());
					goods.add(good);
				}
				List<ResponseClassGoods> responseGoods=new ArrayList<ResponseClassGoods>();
				for(String key:classProducts.keySet()){
					ResponseClassGoods classGoods=new ResponseClassGoods();
					classGoods.setGoodClass(key);
					classGoods.setGoods(classProducts.get(key));
					responseGoods.add(classGoods);
				}
				data.setGood(responseGoods);
				response.setData(data);
				response.setSuccess(true);
			}
		}catch(Exception e){
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
	@Path("/shop/goods/price")
	public String shopGoodPrice(GoodPriceRequest request){
		GoodPriceResponse response=new GoodPriceResponse();
		try{
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			List<Shop> shops= customService.findShops(request.getStart(),request.getCount());
			if(shops!=null&&shops.size()>0){
				List<ShopGoodsPrice> shopsProductPrice=new ArrayList<ShopGoodsPrice>();
				for(Shop s:shops){
					List<ShopProductPrice> shopProductPrices=customService.findShopProductPricesByProductIdsAndShopId(request.getGood(), s.getId());
					ShopGoodsPrice shopGoodPrice=new ShopGoodsPrice();
					if(shopProductPrices!=null&&shopProductPrices.size()>0){
						Shop shop=shopProductPrices.get(0).getShop();
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
						shopGoodPrice.setShop(responseShop);
						List<ResponseGood> responseGoods=new ArrayList<ResponseGood>(); 
						for(ShopProductPrice productPrice:shopProductPrices){
							ResponseGood good=new ResponseGood();
							good.setId(productPrice.getProduct().getId());
							good.setName(productPrice.getProduct().getName());
							good.setPrice(productPrice.getPrice());
							responseGoods.add(good);
						}
						shopGoodPrice.setGood(responseGoods);
					}
					shopsProductPrice.add(shopGoodPrice);
					GoodPriceResponseData data=new GoodPriceResponseData();
					data.setPrice(shopsProductPrice);
					response.setData(data);
					response.setSuccess(true);
				}
			}else{
				response.setSuccess(true);
			}
		}catch(Exception e){
			e.printStackTrace();
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
	@Path("/order/add")
    public String orderAdd(CreateOrderRequest request) {
		CreateOrderResponse response=new CreateOrderResponse();
		User user=getUserByToken(request.getToken());
		try{
			if(user!=null){
				Order order=new Order();
				Date date=new Date();
				order.setCreateTime(date);
				OrderAddress delive=new OrderAddress();
				delive.setId(request.getDeliver_address_id());
				order.setDeliverAddress(delive);
				OrderAddress pick=new OrderAddress();
				pick.setId(request.getPick_address_id());
				order.setPickAddress(pick);
				order.setOrderState(Constant.ORDER_STATE_PROCESSING);
				order.setPaymentType(request.getPayment());
				order.setUser(user);
				Shop shop=new Shop();
				shop.setId(request.getShop_id());
				order.setShop(shop);
				List<RequestGood> goods=request.getGood();
				if(goods!=null&&goods.size()>0){
					Set<OrderDetail> details=new HashSet<OrderDetail>();
					for(RequestGood good:goods){
						OrderDetail detail=new OrderDetail();
						detail.setCount(good.getCount());
						Product pro=new Product();
						pro.setId(good.getId());
						detail.setProduct(pro);
						detail.setPrice(good.getPrice());
						details.add(detail);
					}
					customService.saveOrder(order, details);
					CreateOrderResponseData data=new CreateOrderResponseData();
					data.setId(order.getId());
					SimpleDateFormat form=new SimpleDateFormat("yyyy-MM-dd");
					data.setTime(form.format(date));
					response.setData(data);
					response.setSuccess(true);
				}else{
					ResponseError error=new ResponseError();
					error.setCode(Constant.GOOD_NOT_EXIST);
					error.setMsg("good is empty");
					response.setError(error);
					response.setSuccess(false);
				}
			}else{
				ResponseError error=new ResponseError();
				error.setCode(Constant.USER_NOT_LOGIN_CODE);
				error.setMsg("user not login");
				response.setError(error);
				response.setSuccess(false);
			}
		}catch(Exception e){
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
	@Path("/order/delete")
    public String orderDelete(DeleteOrderRequest request) {
		DeleteOrderResponse response=new DeleteOrderResponse();
		User user=getUserByToken(request.getToken());
		try{
			if(user!=null){
				customService.deleteOrderById(request.getOrder_id());
				response.setSuccess(true);
			}else{
				ResponseError error=new ResponseError();
				error.setCode(Constant.USER_NOT_LOGIN_CODE);
				error.setMsg("user not login");
				response.setError(error);
				response.setSuccess(false);
			}
		}catch(Exception e){
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
	@Path("/order/update")
    public String orderDelete(UpdateOrderRequest request) {
		UpdateOrderResponse response=new UpdateOrderResponse();
		User user=getUserByToken(request.getToken());
		try{
			if(user!=null){
				Order order=customService.findOrderById(request.getOrder_id());
				OrderAddress delive=new OrderAddress();
				delive.setId(request.getDeliver_address_id());
				order.setDeliverAddress(delive);
				OrderAddress pick=new OrderAddress();
				pick.setId(request.getPick_address_id());
				order.setPickAddress(pick);
				order.setOrderState(Constant.ORDER_STATE_PROCESSING);
				order.setPaymentType(request.getPayment());
				order.setUser(user);
				List<RequestGood> goods=request.getGood();
				if(goods!=null&&goods.size()>0){
					Set<OrderDetail> details=new HashSet<OrderDetail>();
					for(RequestGood good:goods){
						OrderDetail detail=new OrderDetail();
						detail.setCount(good.getCount());
						Product pro=new Product();
						pro.setId(good.getId());
						detail.setProduct(pro);
						detail.setPrice(good.getPrice());
						detail.setOrder(order);
						details.add(detail);
					}
					customService.updateOrder(order, details);
					response.setSuccess(true);
				}else{
					ResponseError error=new ResponseError();
					error.setCode(Constant.GOOD_NOT_EXIST);
					error.setMsg("good is empty");
					response.setError(error);
					response.setSuccess(false);
				}
			}else{
				ResponseError error=new ResponseError();
				error.setCode(Constant.USER_NOT_LOGIN_CODE);
				error.setMsg("user not login");
				response.setError(error);
				response.setSuccess(false);
			}
		}catch(Exception e){
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
	@Path("/order")
    public String order(@QueryParam("token") String token,@QueryParam("p") String p,@QueryParam("state") String state,@QueryParam("start") String start,@QueryParam("count") String count) {
		QueryOrderResponse response=new QueryOrderResponse(); 
		User user=getUserByToken(token);
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		if(user!=null){
			List<Order> orders=customService.findPagedOrdersByState(user.getId(), Integer.parseInt(state), Integer.parseInt(start), Integer.parseInt(count));
			if(orders!=null&&orders.size()>0){
				QueryOrderResponseData data=new QueryOrderResponseData();
				List<ResponseOrder> responseOrders=new ArrayList<ResponseOrder>();
				for(Order order:orders){
					ResponseOrder resOrder=new ResponseOrder();
					resOrder.setDeliver_address_id(order.getDeliverAddress().getId());
					resOrder.setId(order.getId());
					resOrder.setPayment(String.valueOf(order.getPaymentType()));
					resOrder.setPick_address_id(order.getPickAddress().getId());
					resOrder.setState(order.getOrderState().toString());
					resOrder.setTime(format.format(order.getCreateTime()));
					Shop shop=order.getShop();
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
					resOrder.setShop(responseShop);
					Set<OrderDetail> details=order.getOrderDetails();
					if(details!=null&&details.size()>0){
						List<ResponseGood> resGoods=new ArrayList<ResponseGood>();
						for(OrderDetail detail:details){
							ResponseGood resGood=new ResponseGood();
							resGood.setId(detail.getProduct().getId());
							resGood.setName(detail.getProduct().getName());
							resGood.setCount(detail.getCount());
							resGood.setPrice(detail.getPrice());
							resGoods.add(resGood);
						}
						resOrder.setGood(resGoods);
					}
					responseOrders.add(resOrder);
				}
				data.setOrder(responseOrders);
				response.setData(data);
			}
			response.setSuccess(true);
		}else{
			ResponseError error=new ResponseError();
			error.setCode(Constant.USER_NOT_LOGIN_CODE);
			error.setMsg("user not login");
			response.setError(error);
			response.setSuccess(false);
		}
		
		try{
			
		}catch(Exception e){
			e.printStackTrace();
			com.uf.rest.bean.ResponseError error=new com.uf.rest.bean.ResponseError();
			error.setCode(Constant.SYSTEM_EXCEPTION_CODE);
			error.setMsg(e.getMessage());
			response.setError(error);
		}
		JSONObject obj=JSONObject.fromObject(response);
		return obj.toString();
	}
	
	private User getUserByToken(String token){
		Object obj=CacheUtil.getObj(token);
		if(obj!=null){
			Session session=(Session)obj;
			Object user=session.getAttribute("user");
			if(user!=null)
				return (User)user;
		}
		return null;
	}
	
	
}
