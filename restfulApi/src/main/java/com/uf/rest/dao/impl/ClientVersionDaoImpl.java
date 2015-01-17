package com.uf.rest.dao.impl;

import org.springframework.stereotype.Component;

import com.uf.rest.dao.ClientVersionDao;
import com.uf.rest.entity.ClientVersion;
@Component("clientVersionDao")
public class ClientVersionDaoImpl extends CommonDaoImpl<ClientVersion> implements ClientVersionDao{

}
