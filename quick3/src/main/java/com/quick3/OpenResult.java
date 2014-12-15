package com.quick3;

import java.util.Date;


public class OpenResult {
	private int id;
	private int dateIndex;
	private Date opendate;
	private int  result;
	private int totalIndex;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getOpendate() {
		return opendate;
	}
	public void setOpendate(Date opendate) {
		this.opendate = opendate;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public int getDateIndex() {
		return dateIndex;
	}
	public void setDateIndex(int dateIndex) {
		this.dateIndex = dateIndex;
	}
	public int getTotalIndex() {
		return totalIndex;
	}
	public void setTotalIndex(int totalIndex) {
		this.totalIndex = totalIndex;
	}
	
}
