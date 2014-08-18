package com.uf.fanfan.service;

import java.util.List;
import java.util.Set;

import com.uf.fanfan.entity.Agent;
import com.uf.fanfan.entity.Customer;


public interface UserManageService {
public boolean registerCustomer(Customer customer);

public boolean addCustomerByAgent(Customer customer);
public void addAgent(Agent agent);
public void deleteAgentById(int agentId);
public List<Agent> getAllAgents();
public Agent getAgentById(int agentId);
public Set<Customer> getCustomersInAgent(Agent agent);


}
