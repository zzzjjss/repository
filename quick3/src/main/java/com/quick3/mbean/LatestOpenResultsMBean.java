package com.quick3.mbean;

import java.util.List;

import com.quick3.OpenResult;

public interface LatestOpenResultsMBean {
	public List<OpenResult> getLatestOpenResults() ;
	public void setLatestOpenResults(List<OpenResult> latestOpenResults) ;
	
}
