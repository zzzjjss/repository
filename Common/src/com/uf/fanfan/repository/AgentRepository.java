package com.uf.fanfan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uf.fanfan.entity.Agent;
@Repository
public interface AgentRepository extends JpaRepository<Agent, Integer> {
	@Query("select t from Agetn t where t.name=:name")
	public Agent findByName(@Param("name")String name);
}
