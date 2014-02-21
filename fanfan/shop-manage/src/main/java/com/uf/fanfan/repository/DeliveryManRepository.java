package com.uf.fanfan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uf.fanfan.entity.DeliveryMan;

public interface DeliveryManRepository extends JpaRepository<DeliveryMan, Integer> {
	@Query("select t from DeliveryMan t where t.name=:name")
	public DeliveryMan findByName(@Param("name")String name);
}
