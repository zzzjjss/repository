package com.uf.fanfan.dao;

import java.util.List;

import com.uf.fanfan.entity.Agent;

public interface AgentDao extends CommonDao<Agent>{
	public Agent findAgentByName(String name);
	public List<Agent>  getAllAgents();
}
