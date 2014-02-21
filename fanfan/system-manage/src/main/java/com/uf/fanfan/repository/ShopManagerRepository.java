package com.uf.fanfan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uf.fanfan.entity.ShopManager;
@Repository
public interface ShopManagerRepository extends JpaRepository<ShopManager, Integer> {
	@Query("select t from ShopManager t where t.name=:name")
	public ShopManager findByName(@Param("name")String name);
}
