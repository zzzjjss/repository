package com.uf.fanfan.service;

import com.uf.fanfan.common.UserType;
import com.uf.fanfan.entity.Agent;
import com.uf.fanfan.entity.Customer;


public interface UserService {
public boolean login(String userName,String password,UserType userType);
public void addCustomer(Customer customer);
public Agent getAgentById(int agentId);
}
