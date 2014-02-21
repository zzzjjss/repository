package com.uf.fanfan.common;

public enum TradeState {
	//客户提交订单后，订单是PROCESSING 处理中状态，处理中的订单，客户可以随时取消，
	//在早上10点时，快餐店确定订单后，订单处于SURED（被确认） 状态，此时，客户不能修改订单。
	//快餐送到，收到钱后，，商店确认交易成功，对于少数失败的，商店管理员，确认失败。
	PROCESSING,SURED,SUCESS,FAIL
}
