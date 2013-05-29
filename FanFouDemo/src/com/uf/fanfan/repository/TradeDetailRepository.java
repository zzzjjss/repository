package com.uf.fanfan.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uf.fanfan.entity.TradeDetail;
@Repository
public interface TradeDetailRepository extends JpaRepository<TradeDetail, BigInteger> {

}
