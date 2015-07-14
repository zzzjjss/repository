package com.uf.broadcast.restful;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.uf.broadcast.bean.Constant;
import com.uf.broadcast.bean.request.AddOrganizationRequest;
import com.uf.broadcast.bean.request.AddPublisherRequest;
import com.uf.broadcast.bean.response.CommonResponse;
import com.uf.broadcast.entity.Organization;
import com.uf.broadcast.entity.Publisher;
import com.uf.broadcast.exception.DataExistException;
import com.uf.broadcast.service.AdminService;
import com.uf.broadcast.service.ServiceFactory;

@Singleton
@Path("/admin")
public class AdminAction {
	private AdminService adminService=ServiceFactory.getService(AdminService.class);
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/addOrganization")
	public CommonResponse addOrganization(AddOrganizationRequest request){
		adminService.addOrganization(request.getOrg());
		CommonResponse response=new CommonResponse();
		response.setResult(true);
		return response;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/addPublisher")
	public CommonResponse addPublisher(AddPublisherRequest request){
		CommonResponse response=new CommonResponse();
		Publisher  publisher=new Publisher();
		Organization org=new Organization();
		org.setId(request.getOrgId());
		publisher.setOrg(org);
		publisher.setPassword(request.getPassword());
		publisher.setUserName(request.getUserName());
		
		try {
			adminService.addPublisher(publisher);
		} catch (DataExistException e) {
			e.printStackTrace();
			response.setResult(false);
			response.setErrorCode(Constant.DATA_EXIST_CODE);
			return response;
		}
		response.setResult(true);
		return response;
	}
}
