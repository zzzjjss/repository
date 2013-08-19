package com.uf.fanfan.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


/**
 * The persistent class for the tradedetail database table.
 * 
 */
@Entity
@Table(name="order_detail")
@TableGenerator(name = "orderDetail_ID_GEN", table = "ID_TABLE", pkColumnName = "ID_KEY", valueColumnName = "ID_VALUE", pkColumnValue = "order_detail_ID",allocationSize=1)
public class OrderDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="orderDetail_ID_GEN")
	private String id;

	private String evaluation;

	private int productid;

	private int tradeAmount;

	private float tradeprice;

	private Timestamp tradetime;
	//bi-directional many-to-one association to CustomerOrder
	@ManyToOne
	@JoinColumn(name="orderid")
	private CustomerOrder customerOrder;

	public OrderDetail() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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

	public int getTradeAmount() {
		return this.tradeAmount;
	}

	public void setTradeAmount(int tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public float getTradeprice() {
		return this.tradeprice;
	}

	public void setTradeprice(float tradeprice) {
		this.tradeprice = tradeprice;
	}

	public CustomerOrder getCustomerOrder() {
		return this.customerOrder;
	}

	public void setCustomerOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
	}
	public Timestamp getTradetime() {
		return this.tradetime;
	}

	public void setTradetime(Timestamp tradetime) {
		this.tradetime = tradetime;
	}
}