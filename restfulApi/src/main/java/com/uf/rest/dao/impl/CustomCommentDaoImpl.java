package com.uf.rest.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.uf.rest.dao.CustomCommentDao;
import com.uf.rest.entity.CustomComment;
@Component("customCommentDao")
public class CustomCommentDaoImpl extends CommonDaoImpl<CustomComment> implements CustomCommentDao{
	public List<CustomComment> findPagedComments(Integer start,Integer count){
		Query query=this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("select c from CustomComment c ");
		query.setFirstResult(start);
		query.setMaxResults(count);
		return query.list();
	}
}
