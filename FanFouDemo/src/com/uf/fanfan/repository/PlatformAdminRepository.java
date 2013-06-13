package com.uf.fanfan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uf.fanfan.entity.Customer;
import com.uf.fanfan.entity.PlatformAdmin;
@Repository
public interface PlatformAdminRepository extends JpaRepository<PlatformAdmin, Integer> {
	@Query("select t from PlatformAdmin t where t.name=:name")
	public PlatformAdmin findByName(@Param("name")String name);
}
