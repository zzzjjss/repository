package com.uf.rest.entity;

import java.io.Serializable;
import java.util.Date;

public class Shop implements Serializable{
	private Integer id;
	private String name;
	private String shopPhotoPath;
	private String shopIntroducePhotoPath;
	private String contactStyle;
	private String country;
	private String province;
	private String city;
	private String address;
	private Double longitude;
	private Double latitude;
	private Date createTime;
	private Float mark;
	private BusinessType businessType;
	private ShopUser shopUser;
	private Boolean isOpen;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShopPhotoPath() {
		return shopPhotoPath;
	}
	public void setShopPhotoPath(String shopPhotoPath) {
		this.shopPhotoPath = shopPhotoPath;
	}
	public String getShopIntroducePhotoPath() {
		return shopIntroducePhotoPath;
	}
	public void setShopIntroducePhotoPath(String shopIntroducePhotoPath) {
		this.shopIntroducePhotoPath = shopIntroducePhotoPath;
	}
	public String getContactStyle() {
		return contactStyle;
	}
	public void setContactStyle(String contactStyle) {
		this.contactStyle = contactStyle;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public BusinessType getBusinessType() {
		return businessType;
	}
	public void setBusinessType(BusinessType businessType) {
		this.businessType = businessType;
	}
	public ShopUser getShopUser() {
		return shopUser;
	}
	public void setShopUser(ShopUser shopUser) {
		this.shopUser = shopUser;
	}
	public Boolean getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Float getMark() {
		return mark;
	}
	public void setMark(Float mark) {
		this.mark = mark;
	}
	
}
