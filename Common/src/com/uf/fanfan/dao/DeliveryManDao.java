package com.uf.fanfan.dao;

import com.uf.fanfan.entity.DeliveryMan;

public interface DeliveryManDao  {
	//@Query("select t from DeliveryMan t where t.name=:name")
	public DeliveryMan findByName(String name);
}
