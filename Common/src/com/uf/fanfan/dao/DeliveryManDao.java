package com.uf.fanfan.dao;

import com.uf.fanfan.entity.Deliveryman;

public interface DeliveryManDao extends CommonDao<DeliveryManDao> {
	//@Query("select t from DeliveryMan t where t.name=:name")
	public Deliveryman findByName(String name);
}
