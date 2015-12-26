package com.uf.broadcast.service;

import com.uf.broadcast.entity.Organization;
import com.uf.broadcast.entity.Publisher;
import com.uf.broadcast.exception.DataExistException;

public interface AdminService {
	public void addOrganization(Organization org);
	public void addPublisher(Publisher publisher)throws DataExistException;
}
