package com.uf.broadcast.service;

import com.uf.broadcast.entity.Message;
import com.uf.broadcast.entity.Organization;
import com.uf.broadcast.entity.Publisher;
import com.uf.broadcast.exception.DataExistException;

public interface PublisherService {
	public void publishOneMessage(Message message);
	public void registPublisher(Organization org,Publisher publisher)throws DataExistException,Exception;
	public Publisher login(String userName,String password);
}
