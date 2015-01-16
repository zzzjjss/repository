package com.uf.rest.dao;

import java.util.List;

import com.uf.rest.entity.CustomComment;

public interface CustomCommentDao extends CommonDao<CustomComment>{
	public List<CustomComment> findPagedComments(Integer start,Integer count);
}
