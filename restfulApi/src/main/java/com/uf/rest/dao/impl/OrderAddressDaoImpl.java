package com.uf.rest.dao.impl;

import org.springframework.stereotype.Component;

import com.uf.rest.dao.OrderAddressDao;
import com.uf.rest.entity.OrderAddress;
@Component("orderAddressDao")
public class OrderAddressDaoImpl extends CommonDaoImpl<OrderAddress> implements OrderAddressDao{

}
