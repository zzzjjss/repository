package com.uf.fanfan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uf.fanfan.common.UserType;
import com.uf.fanfan.dao.AgentDao;
import com.uf.fanfan.dao.CustomerDao;
import com.uf.fanfan.dao.DeliveryManDao;
import com.uf.fanfan.dao.PlatformAdminDao;
import com.uf.fanfan.dao.ShopManagerDao;
import com.uf.fanfan.entity.Agent;
import com.uf.fanfan.entity.Customer;
import com.uf.fanfan.entity.DeliveryMan;
import com.uf.fanfan.entity.PlatformAdmin;
import com.uf.fanfan.entity.ShopManager;
import com.uf.fanfan.service.UserService;

public class UserServiceImpl implements UserService{
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
	
	public boolean login(String userName,String password,String userType){
		if(UserType.AGENT.getName().equalsIgnoreCase(userType)){
			Agent user=agentDao.findAgentByName(userName);
			return user!=null&&password!=null&&password.equals(user.getPassword());
				
		} else if(UserType.CUSTOMER.getName().equalsIgnoreCase(userType)){
				Customer user=customerDao.findCustomerByName(userName);
				return user!=null&&password!=null&&password.equals(user.getPassword());
		} else if(UserType.PLATFORM_ADMIN.getName().equalsIgnoreCase(userType)){
			PlatformAdmin user=platformAdminDao.findByName(userName);
			return user!=null&&password!=null&&password.equals(user.getPassword());
		}else if(UserType.SHOP_Manager.getName().equalsIgnoreCase(userType)){
			ShopManager user=shopManagerDao.findByName(userName);
			return user!=null&&password!=null&&password.equals(user.getPassword());
		}else if(UserType.DELIVERY_MAN.getName().equalsIgnoreCase(userType)){
			DeliveryMan user=deliverymanDao.findByName(userName);
			return user!=null&&password!=null&&password.equals(user.getPassword());
		}
		return false;
		
		
	}
	public Agent getAgentById(int agentId){
		//return agentDao.findOne(agentId);
		return null;
	}
	public void addCustomer(Customer customer){
		//customerDao.saveAndFlush(customer);
	}
}
