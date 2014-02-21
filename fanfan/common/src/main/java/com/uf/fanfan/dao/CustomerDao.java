package com.uf.fanfan.dao;

import java.util.List;

import com.uf.fanfan.entity.Customer;


public interface CustomerDao extends CommonDao<Customer>{
	
	public Customer findCustomerByName(String name);
}
