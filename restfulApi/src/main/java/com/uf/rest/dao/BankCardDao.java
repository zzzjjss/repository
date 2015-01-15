package com.uf.rest.dao;

import java.util.List;

import com.uf.rest.entity.BankCard;

public interface BankCardDao extends CommonDao<BankCard>{
	public List<BankCard> findPagedUserBankCards(Integer userId,Integer start,Integer count);
}
