package com.uf.fanfan.dao;

import java.io.Serializable;

public interface  CommonDao<T> {
	public void save(T obj);
	public void delete(T obj);
	public void update(T obj);
	public T loadById(Class<T> entity,Serializable id);
}
