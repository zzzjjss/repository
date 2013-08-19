package com.uf.fanfan.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;



@Entity
@Table(name="customer_order")
@TableGenerator(name = "order_ID_GEN", table = "ID_TABLE", pkColumnName = "ID_KEY", valueColumnName = "ID_VALUE", pkColumnValue = "customer_order_ID",allocationSize=1)
public class CustomerOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="order_ID_GEN")
	private String id;

	private Timestamp arrivetime;

	private int customerid;

	private float sumMoney;

	private short tradestate;

	

	//bi-directional many-to-one association to OrderDetail
	@OneToMany(mappedBy="customerOrder")
	private List<OrderDetail> orderDetails;

	public CustomerOrder() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Timestamp getArrivetime() {
		return this.arrivetime;
	}

	public void setArrivetime(Timestamp arrivetime) {
		this.arrivetime = arrivetime;
	}

	public int getCustomerid() {
		return this.customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public float getSumMoney() {
		return this.sumMoney;
	}

	public void setSumMoney(float sumMoney) {
		this.sumMoney = sumMoney;
	}

	public short getTradestate() {
		return this.tradestate;
	}

	public void setTradestate(short tradestate) {
		this.tradestate = tradestate;
	}



	public List<OrderDetail> getOrderDetails() {
		return this.orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public OrderDetail addOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().add(orderDetail);
		orderDetail.setCustomerOrder(this);

		return orderDetail;
	}

	public OrderDetail removeOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().remove(orderDetail);
		orderDetail.setCustomerOrder(null);

		return orderDetail;
	}

}
