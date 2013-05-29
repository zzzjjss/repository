package com.uf.fanfan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uf.fanfan.entity.Agent;
@Repository
public interface AgentRepository extends JpaRepository<Agent, Integer> {

}
