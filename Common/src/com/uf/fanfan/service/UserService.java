package com.uf.fanfan.service;

import com.uf.fanfan.entity.Agent;
import com.uf.fanfan.entity.Customer;


public interface UserService {
public boolean login(String userName,String password,String userType);
public void addCustomer(Customer customer);
public Agent getAgentById(int agentId);
}
