package com.uf.broadcast.dao.impl;

import org.springframework.stereotype.Component;

import com.uf.broadcast.dao.MessageDao;
import com.uf.broadcast.entity.Message;
@Component("messageDao")
public class MessageDaoImpl extends CommonDaoImpl<Message> implements MessageDao{
	
}
