package com.uf.broadcast.restful;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.uf.broadcast.bean.Constant;
import com.uf.broadcast.bean.request.PublisherLoginRequest;
import com.uf.broadcast.bean.request.PublishMessageRequest;
import com.uf.broadcast.bean.request.RegistPublisherRequest;
import com.uf.broadcast.bean.response.CommonResponse;
import com.uf.broadcast.entity.Publisher;
import com.uf.broadcast.exception.DataExistException;
import com.uf.broadcast.service.PublisherService;
import com.uf.broadcast.service.ServiceFactory;

@Singleton
@Path("/publisher")
public class PublisherAction {
  private PublisherService  pubService=ServiceFactory.getService(PublisherService.class);  
  
  
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/registPublisher")
  public CommonResponse registPublisher(RegistPublisherRequest request){
      CommonResponse response=new CommonResponse();
      Publisher pub=new Publisher();
      pub.setPassword(request.getPassword());
      pub.setUserName(request.getUserName());
      try {
        pubService.registPublisher(request.getOrg(), pub);
      } catch (DataExistException e) {
          e.printStackTrace();
          response.setResult(false);
          response.setErrorCode(Constant.DATA_EXIST_CODE);
          return response;
      }catch(Exception e){
        e.printStackTrace();
        response.setResult(false);
        response.setErrorCode(Constant.SYSTEM_EXCEPTION_CODE);
        return response;
      }
      response.setResult(true);
      return response;
  }
  
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/publisherLogin")
  public CommonResponse publishLogin(PublisherLoginRequest request){
      CommonResponse response=new CommonResponse();
      try{
        Publisher publisher=pubService.login(request.getUserName(), request.getPassword());
        if(publisher!=null){
          response.setResult(true);
        }else{
          response.setResult(false);
        }
      }catch(Exception e){
        e.printStackTrace();
        response.setResult(false);
        response.setErrorCode(Constant.SYSTEM_EXCEPTION_CODE);
      }
      return response;
  }
  
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
