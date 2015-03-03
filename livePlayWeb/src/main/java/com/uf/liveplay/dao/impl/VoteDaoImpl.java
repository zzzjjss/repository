package com.uf.liveplay.dao.impl;

import org.springframework.stereotype.Component;

import com.uf.liveplay.dao.VoteDao;
import com.uf.liveplay.entity.Vote;
@Component("voteDao")
public class VoteDaoImpl extends CommonDaoImpl<Vote> implements VoteDao{

}
