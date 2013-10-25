package com.uf.fanfan.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.uf.fanfan.dao.AgentDao;
import com.uf.fanfan.entity.Agent;
@Component("agentDao")
public class AgentDaoImpl extends CommonDaoImpl<Agent> implements AgentDao{

	@Override
	public Agent getAgentByName(String name) {
		String hql="selecct a from Agent a where a.name=:name";
		List<Agent> agents=getHibernateTemplate().findByNamedParam(hql, "name", name);
		return (agents!=null&&agents.size()>0)?agents.get(0):null;
		
	}

	@Override
	public List<Agent> getAllAgents() {
		return getHibernateTemplate().find("select a from Agent a");
	}

}
