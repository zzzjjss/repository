package com.uf.fanfan.dao;

import com.uf.fanfan.entity.Agent;

public interface AgentDao extends CommonDao<Agent>{
	public Agent findAgentByName(String name);
}
