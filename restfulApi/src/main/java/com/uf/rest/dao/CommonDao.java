package com.uf.rest.dao;

import java.io.Serializable;

public interface  CommonDao<T> {
	public void insert(T obj);
	public void delete(T obj);
	public void update(T obj);
	public T findById(Class<T> entity,Serializable id);
}
