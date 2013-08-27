package com.uf.fanfan.dao;

import com.uf.fanfan.entity.Customer;


public interface CustomerDao{
	
	public Customer findCustomerByName(String name);
}
