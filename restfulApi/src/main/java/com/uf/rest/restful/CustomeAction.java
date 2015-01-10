package com.uf.rest.restful;

import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.uf.rest.entity.Shop;
import com.uf.rest.service.CustomService;
import com.uf.rest.service.ServiceFactory;


public class CustomeAction {
	private CustomService service=ServiceFactory.getService(CustomService.class);
	
	
	
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/goods")
    public String goods(@FormParam("token") String token,@FormParam("p") String platform,@FormParam("start") String start,@FormParam("count") String count,
    					@FormParam("longitude") String longitude,@FormParam("latitude") String latitude,@FormParam("city") String city,@FormParam("request_order") String request_order) {
		
		return null;
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/shop/goods/price")
    public String shopGoodsPrice(@FormParam("token") String token,@FormParam("p") String platform,@FormParam("start") String start,@FormParam("count") String count,
    					@FormParam("good") String goods) {
		
		return null;
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/order/add")
    public String orderAdd(@FormParam("token") String token,@FormParam("p") String platform,@FormParam("shop_id") String start,@FormParam("pick_address_id") String pickAddress,
    					@FormParam("deliver_address_id") String deliver_address_id) {
		
		return null;
	}
	
	
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/order/delete")
    public String orderDelete(@FormParam("token") String token,@FormParam("p") String platform,@FormParam("order_id") String orderId) {
		
		return null;
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/order/update")
    public String orderUpdate(@FormParam("token") String token,@FormParam("p") String platform,@FormParam("shop_id") String start,@FormParam("pick_address_id") String pickAddress,
    					@FormParam("deliver_address_id") String deliver_address_id) {
		
		return null;
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/order")
    public String order(@FormParam("token") String token,@FormParam("p") String platform,@FormParam("state") String state) {
		
		return null;
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/address/add")
    public String addressAdd(@FormParam("token") String token,@FormParam("p") String platform,@FormParam("state") String state) {
		
		return null;
	}
	
	
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/address/remove")
    public String addressRemove(@FormParam("token") String token,@FormParam("p") String platform,@FormParam("state") String state) {
		
		return null;
	}
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/address/update")
    public String addressUpdate(@FormParam("token") String token,@FormParam("p") String platform,@FormParam("state") String state) {
		
		return null;
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/address")
    public String address(@FormParam("token") String token,@FormParam("p") String platform,@FormParam("state") String state) {
		
		return null;
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/card/add")
    public String cardAdd(@FormParam("token") String token,@FormParam("p") String platform,@FormParam("state") String state) {
		
		return null;
	}
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/card/remove")
    public String cardRemove(@FormParam("token") String token,@FormParam("p") String platform,@FormParam("state") String state) {
		
		return null;
	}
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/card/update")
    public String cardUpdate(@FormParam("token") String token,@FormParam("p") String platform,@FormParam("state") String state) {
		
		return null;
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/card")
    public String card(@FormParam("token") String token,@FormParam("p") String platform,@FormParam("state") String state) {
		
		return null;
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/comment/add")
    public String commentAdd(@FormParam("token") String token,@FormParam("p") String platform,@FormParam("state") String state) {
		
		return null;
	}
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/comment")
    public String comment(@FormParam("token") String token,@FormParam("p") String platform,@FormParam("state") String state) {
		
		return null;
	}
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/version/last")
    public String versionLast(@FormParam("token") String token,@FormParam("p") String platform,@FormParam("state") String state) {
		
		return null;
	}
	
	
}
