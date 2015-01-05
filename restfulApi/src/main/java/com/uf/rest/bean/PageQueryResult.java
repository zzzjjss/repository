package com.uf.rest.bean;

import java.util.ArrayList;
import java.util.List;

public class PageQueryResult<T> {
	private List<T> pageData = new ArrayList<T>();
	// total page number
	private int totalPage;
	// current page index
	private int pageIndex;
	// the record number of each page
	private int pageSize;
	// the total record .
	private long totalRecord;

	public List<T> getPageData() {
		return pageData;
	}

	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord;
	}

}
