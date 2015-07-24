package com.uf.broadcast.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uf.broadcast.dao.OrganizationDao;
import com.uf.broadcast.dao.PublisherDao;
import com.uf.broadcast.entity.Message;
import com.uf.broadcast.entity.Organization;
import com.uf.broadcast.entity.Publisher;
import com.uf.broadcast.exception.DataExistException;
import com.uf.broadcast.service.PublisherService;
@Service("publisherService")
public class PublisherServiceImpl implements PublisherService{
    @Autowired
    private OrganizationDao orgDao;
    @Autowired
    private PublisherDao publisherDao;
	
    
    
	public OrganizationDao getOrgDao() {
      return orgDao;
    }
    public void setOrgDao(OrganizationDao orgDao) {
      this.orgDao = orgDao;
    }
    public PublisherDao getPublisherDao() {
      return publisherDao;
    }
    public void setPublisherDao(PublisherDao publisherDao) {
      this.publisherDao = publisherDao;
    }
	public void publishOneMessage(Message message) {
		
		
	}
	public void registPublisher(Organization org,Publisher publisher)throws DataExistException, Exception{
	  orgDao.insert(org);
	  publisher.setOrg(org);
	  List<Publisher> data=publisherDao.findByHql("from Publisher p where p.userName=?", publisher.getUserName());
      if(data!=null&&data.size()>0){
          throw new DataExistException("exist  publisher name:"+publisher.getUserName());
      }else{
          publisherDao.insert(publisher);
      }
	}
	public Publisher login(String userName,String password){
	  List<Publisher> data=publisherDao.findByHql("from Publisher p where p.userName=? ", userName);
	  if(data==null||data.size()==0){
	    return null;
	  }else{
	    Publisher publisher=data.get(0);
	    if(publisher.getPassword()!=null&&publisher.getPassword().equals(password)){
	      return publisher;
	    }else{
	      return null;
	    }
	  }
	}
}
