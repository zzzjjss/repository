package com.uf.broadcast.restful;

import java.util.UUID;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.uf.broadcast.bean.ErrorInfo;
import com.uf.broadcast.bean.request.PublishMessageRequest;
import com.uf.broadcast.bean.request.PublisherLoginRequest;
import com.uf.broadcast.bean.request.RegistPublisherRequest;
import com.uf.broadcast.bean.response.CommonResponse;
import com.uf.broadcast.bean.response.LoginResponse;
import com.uf.broadcast.cache.ICache;
import com.uf.broadcast.entity.Message;
import com.uf.broadcast.entity.Publisher;
import com.uf.broadcast.exception.DataExistException;
import com.uf.broadcast.service.PublisherService;
import com.uf.broadcast.service.ServiceFactory;

@Singleton
@Path("/publisher")
public class PublisherAction {
  private PublisherService  pubService=ServiceFactory.getService(PublisherService.class);  
  private ICache  cache=ServiceFactory.getService(ICache.class);
  
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
          response.setErrorInfo(ErrorInfo.DATA_EXIST);
          return response;
      }catch(Exception e){
        e.printStackTrace();
        response.setResult(false);
        response.setErrorInfo(ErrorInfo.SYSTEM_EXCEPTION);
        return response;
      }
      response.setResult(true);
      return response;
  }
  
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/publisherLogin")
  public LoginResponse publisherLogin(PublisherLoginRequest request){
      LoginResponse response=new LoginResponse();
      try{
        Publisher publisher=pubService.login(request.getUserName(), request.getPassword());
        if(publisher!=null){
          UUID id=UUID.randomUUID();
          String idString=id.toString();
          cache.store(idString, publisher);
          response.setSessionId(idString);
          response.setResult(true);
        }else{
          response.setResult(false);
        }
      }catch(Exception e){
        e.printStackTrace();
        response.setResult(false);
        response.setErrorInfo(ErrorInfo.SYSTEM_EXCEPTION);
      }
      return response;
  }
  
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/publishMessage")
	public CommonResponse publishMessage(PublishMessageRequest request){
	    CommonResponse response=new CommonResponse();
	    try{
	      String session=request.getSession();
	        Publisher publisher=cache.getObjectByKey(session, Publisher.class);
	        if(publisher==null){
	          response.setResult(false);
	          response.setErrorInfo(ErrorInfo.NO_LOGIN);
	        }else{
	          Message message=new Message();
	          message.setContent(request.getContent());
	          message.setPublisher(publisher);
	          message.setOrg(publisher.getOrg());
	          pubService.publishOneMessage(message);
	          response.setResult(true);
	        }
	    }catch(Exception e){
	      e.printStackTrace();
	      response.setResult(false);
	      response.setErrorInfo(ErrorInfo.SYSTEM_EXCEPTION);
	    }
		return response;
	}
}
