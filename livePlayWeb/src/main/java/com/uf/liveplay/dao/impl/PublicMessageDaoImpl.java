package com.uf.liveplay.dao.impl;

import org.springframework.stereotype.Component;

import com.uf.liveplay.dao.PublicMessageDao;
import com.uf.liveplay.entity.PublicMessage;
@Component("publicMessageDao")
public class PublicMessageDaoImpl extends CommonDaoImpl<PublicMessage>  implements PublicMessageDao{

}
