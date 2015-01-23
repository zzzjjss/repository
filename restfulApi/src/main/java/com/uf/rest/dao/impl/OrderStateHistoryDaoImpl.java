package com.uf.rest.dao.impl;

import org.springframework.stereotype.Component;

import com.uf.rest.dao.OrderStateHistoryDao;
import com.uf.rest.entity.OrderStateHistory;
@Component("orderStateHistoryDao")
public class OrderStateHistoryDaoImpl extends CommonDaoImpl<OrderStateHistory> implements OrderStateHistoryDao{

}
