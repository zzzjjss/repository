package com.uf.fanfan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uf.fanfan.common.UserType;
import com.uf.fanfan.entity.Agent;
import com.uf.fanfan.entity.Customer;
import com.uf.fanfan.entity.DeliveryMan;
import com.uf.fanfan.entity.PlatformAdmin;
import com.uf.fanfan.entity.ShopManager;
import com.uf.fanfan.repository.AgentRepository;
import com.uf.fanfan.repository.CustomerRepository;
import com.uf.fanfan.repository.DeliveryManRepository;
import com.uf.fanfan.repository.PlatformAdminRepository;
import com.uf.fanfan.repository.ShopManagerRepository;
import com.uf.fanfan.service.LoginService;
@Service("loginService")
public class LoginServiceImpl implements LoginService{
	@Autowired
	private AgentRepository  agent;
	@Autowired
	private CustomerRepository customer;
	@Autowired
	private PlatformAdminRepository platformAdmin;
	@Autowired
	private  ShopManagerRepository  shopManager;
	@Autowired
	private DeliveryManRepository  deliveryman;
	
	public boolean login(String userName,String password,String userType){
		if(UserType.AGENT.getName().equalsIgnoreCase(userType)){
			Agent user=agent.findByName(userName);
			return user!=null&&password!=null&&password.equals(user.getPassword());
				
		} else if(UserType.CUSTOMER.getName().equalsIgnoreCase(userType)){
				Customer user=customer.findByName(userName);
				return user!=null&&password!=null&&password.equals(user.getPassword());
		} else if(UserType.PLATFORM_ADMIN.getName().equalsIgnoreCase(userType)){
			PlatformAdmin user=platformAdmin.findByName(userName);
			return user!=null&&password!=null&&password.equals(user.getPassword());
		}else if(UserType.SHOP_Manager.getName().equalsIgnoreCase(userType)){
			ShopManager user=shopManager.findByName(userName);
			return user!=null&&password!=null&&password.equals(user.getPassword());
		}else if(UserType.DELIVERY_MAN.getName().equalsIgnoreCase(userType)){
			DeliveryMan user=deliveryman.findByName(userName);
			return user!=null&&password!=null&&password.equals(user.getPassword());
		}
		return false;
		
		
	}
}
