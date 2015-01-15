package com.uf.rest.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.uf.rest.dao.BankCardDao;
import com.uf.rest.entity.BankCard;
@Component("bankCardDao")
public class BankCardDaoImpl extends CommonDaoImpl<BankCard> implements BankCardDao{
	public List<BankCard> findPagedUserBankCards(Integer userId,Integer start,Integer count){
		Query query=this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(" from BankCard o where o.user.id=:userId ");
		query.setParameter("userId", userId);
		query.setFirstResult(start);
		query.setMaxResults(count);
		return query.list();
	}
}
