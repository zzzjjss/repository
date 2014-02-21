package com.uf.fanfan.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uf.fanfan.dao.AgentDao;
import com.uf.fanfan.dao.CustomerDao;
import com.uf.fanfan.dao.DeliveryManDao;
import com.uf.fanfan.dao.PlatformAdminDao;
import com.uf.fanfan.dao.ShopManagerDao;
import com.uf.fanfan.entity.Agent;
import com.uf.fanfan.entity.Customer;
import com.uf.fanfan.service.UserManageService;
@Service("userManageService")
public class UserManageServiceImpl implements UserManageService{
	@Autowired
	private AgentDao  agentDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private PlatformAdminDao platformAdminDao;
	@Autowired
	private  ShopManagerDao  shopManagerDao;
	@Autowired
	private DeliveryManDao  deliverymanDao;
	@Override
	public boolean registerCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean addCustomerByAgent(Customer customer) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void addAgent(Agent agent) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteAgentById(int agentId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Agent> getAllAgents() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Agent getAgentById(int agentId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set<Customer> getCustomersInAgent(Agent agent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
