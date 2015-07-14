package com.uf.broadcast.restful;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.uf.broadcast.bean.request.PublishMessageRequest;
import com.uf.broadcast.bean.response.CommonResponse;

@Singleton
@Path("/publisher")
public class PublisherAction {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/publishMessage")
	public CommonResponse publishMessage(PublishMessageRequest request){
		
		CommonResponse response=new CommonResponse();
		response.setResult(true);
		return response;
	}
}
