package com.uf.fanfan.service;

import java.util.List;
import java.util.Set;

import com.uf.fanfan.common.UserType;
import com.uf.fanfan.entity.Agent;
import com.uf.fanfan.entity.Customer;


public interface UserManageService {
/**
 * 客户自己通过网站注册账号	
 * @param customer
 * @return
 */
public boolean registerCustomer(Customer customer);

public boolean addCustomerByAgent(Customer customer);
/**
 * 增加一个代理点
 * @param agent
 */
public void addAgent(Agent agent);
public void deleteAgentById(int agentId);
public List<Agent> getAllAgents();
public Agent getAgentById(int agentId);
public Set<Customer> getCustomersInAgent(Agent agent);


}
