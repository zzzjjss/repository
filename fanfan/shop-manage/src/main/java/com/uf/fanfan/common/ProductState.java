package com.uf.fanfan.common;

public enum ProductState {
	//架下，架上，删除
	FRAME_DOWN("down"),FRAME_UP("up"),DELETE("delete");
	private String state;
	private ProductState(String state){
		this.state=state;
	}
	public String getStateString(){
		return state;
	}
}
