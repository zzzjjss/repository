package com.uf.fanfan.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uf.fanfan.entity.TradeDetail;
@Repository
public interface TradeDetailRepository extends JpaRepository<TradeDetail, BigInteger> {
	@Query( " select t from TradeDetail t where t.productid = :id " )
	public  List<TradeDetail>  findByProductid(@Param("id")Integer productid);
}
