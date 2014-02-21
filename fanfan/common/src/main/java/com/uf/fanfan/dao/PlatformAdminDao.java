package com.uf.fanfan.dao;

import com.uf.fanfan.entity.PlatformAdmin;

public interface PlatformAdminDao extends CommonDao<PlatformAdmin>{
	//@Query("select t from PlatformAdmin t where t.name=:name")
	public PlatformAdmin findByName(String name);
}
