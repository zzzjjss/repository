package com.uf.broadcast.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uf.broadcast.dao.OrganizationDao;
import com.uf.broadcast.dao.PublisherDao;
import com.uf.broadcast.entity.Organization;
import com.uf.broadcast.entity.Publisher;
import com.uf.broadcast.exception.DataExistException;
import com.uf.broadcast.service.AdminService;
@Service("adminService")
public class AdminServiceImpl implements AdminService{
	@Autowired
	private OrganizationDao orgDao;
	@Autowired
	private PublisherDao publisherDao;
	
	public PublisherDao getPublisherDao() {
		return publisherDao;
	}

	public void setPublisherDao(PublisherDao publisherDao) {
		this.publisherDao = publisherDao;
	}

	public OrganizationDao getOrgDao() {
		return orgDao;
	}

	public void setOrgDao(OrganizationDao orgDao) {
		this.orgDao = orgDao;
	}

	@Override
	public void addOrganization(Organization org){
		orgDao.insert(org);
		
	}

	@Override
	public void addPublisher(Publisher publisher)throws DataExistException {
		List<Publisher> data=publisherDao.findByHql("from Publisher p where p.userName=:name", publisher.getUserName());
		if(data!=null&&data.size()>0){
			throw new DataExistException("exist  publisher name:"+publisher.getUserName());
		}else{
			publisherDao.insert(publisher);
		}
		
	}
	
}
