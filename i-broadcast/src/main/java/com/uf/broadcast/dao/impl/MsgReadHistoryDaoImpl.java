package com.uf.broadcast.dao.impl;

import org.springframework.stereotype.Component;

import com.uf.broadcast.dao.MsgReadHistoryDao;
import com.uf.broadcast.entity.MsgReadHistory;
@Component("msgReadHistoryDao")
public class MsgReadHistoryDaoImpl extends CommonDaoImpl<MsgReadHistory> implements MsgReadHistoryDao{	
}
