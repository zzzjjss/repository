package com.uf.fanfan.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the tradedetail database table.
 * 
 */
@Entity
@TableGenerator(name = "Tradedetail_ID_GEN", table = "ID_TABLE", pkColumnName = "ID_KEY", valueColumnName = "ID_VALUE", pkColumnValue = "tradedetail_ID",allocationSize=1)
public class TradeDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="Tradedetail_ID_GEN")
	private BigInteger id;

	private int customerid;

	private String evaluation;

	private int productid;
	
	private int tradeAmount;
	
	private float tradeprice;

	private short tradestate;

	private Timestamp tradetime;

	private Timestamp arriveTime;
	
	
	public TradeDetail() {
	}

	public BigInteger getId() {
		return this.id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public int getCustomerid() {
		return this.customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public String getEvaluation() {
		return this.evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}

	public int getProductid() {
		return this.productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public float getTradeprice() {
		return this.tradeprice;
	}

	public void setTradeprice(float tradeprice) {
		this.tradeprice = tradeprice;
	}

	public short getTradestate() {
		return this.tradestate;
	}

	public void setTradestate(short tradestate) {
		this.tradestate = tradestate;
	}

	public Timestamp getTradetime() {
		return this.tradetime;
	}

	public void setTradetime(Timestamp tradetime) {
		this.tradetime = tradetime;
	}

	public Timestamp getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(Timestamp arriveTime) {
		this.arriveTime = arriveTime;
	}

	public int getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(int tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	
	
}