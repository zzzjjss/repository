package com.uf.liveplay.entity;

public class Vote implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Long upCount;
	private Long downCount;
	private Long equalCount;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getUpCount() {
		return upCount;
	}
	public void setUpCount(Long upCount) {
		this.upCount = upCount;
	}
	public Long getDownCount() {
		return downCount;
	}
	public void setDownCount(Long downCount) {
		this.downCount = downCount;
	}
	public Long getEqualCount() {
		return equalCount;
	}
	public void setEqualCount(Long equalCount) {
		this.equalCount = equalCount;
	}
	
}
