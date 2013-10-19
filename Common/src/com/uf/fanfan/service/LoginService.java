package com.uf.fanfan.service;

import com.uf.fanfan.common.UserType;
import com.uf.fanfan.entity.Customer;

public interface LoginService {
	public boolean login(String userName,String password,UserType userType);
	
}
