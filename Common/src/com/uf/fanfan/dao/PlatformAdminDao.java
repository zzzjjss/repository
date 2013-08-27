package com.uf.fanfan.dao;

import org.springframework.stereotype.Repository;

import com.uf.fanfan.entity.PlatformAdmin;

public interface PlatformAdminDao {
	//@Query("select t from PlatformAdmin t where t.name=:name")
	public PlatformAdmin findByName(String name);
}
