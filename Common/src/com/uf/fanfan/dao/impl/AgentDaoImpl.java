package com.uf.fanfan.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.uf.fanfan.dao.AgentDao;
import com.uf.fanfan.entity.Agent;
@Component("agentDao")
public class AgentDaoImpl extends CommonDaoImpl<Agent> implements AgentDao{

	@Override
	public Agent findAgentByName(String name) {
		return null;
	}

	@Override
	public List<Agent> getAllAgents() {
		// TODO Auto-generated method stub
		return null;
	}

}
