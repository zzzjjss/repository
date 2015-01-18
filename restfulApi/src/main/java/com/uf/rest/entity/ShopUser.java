package com.uf.rest.entity;

import java.io.Serializable;
import java.util.Date;

public class ShopUser implements Serializable{
	private Integer id;
	private String userName;
	private String password;
	private int registType;
	private int platform;
	private Date createDate;
	private String realName;
	private String idCard;
	private String idCardPhotoPath;
	private Double balance;
	private Shop shop;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getIdCardPhotoPath() {
		return idCardPhotoPath;
	}
	public void setIdCardPhotoPath(String idCardPhotoPath) {
		this.idCardPhotoPath = idCardPhotoPath;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	public int getRegistType() {
		return registType;
	}
	public void setRegistType(int registType) {
		this.registType = registType;
	}
	public int getPlatform() {
		return platform;
	}
	public void setPlatform(int platform) {
		this.platform = platform;
	}
	
	
}
