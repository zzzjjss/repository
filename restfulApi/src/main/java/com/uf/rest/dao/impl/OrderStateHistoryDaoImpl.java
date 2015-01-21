package com.uf.rest.dao.impl;

import com.uf.rest.dao.OrderStateHistoryDao;
import com.uf.rest.entity.OrderStateHistory;
import org.springframework.stereotype.Component;
@Component("orderStateHistoryDao")
public class OrderStateHistoryDaoImpl extends CommonDaoImpl<OrderStateHistory> implements OrderStateHistoryDao{

}
