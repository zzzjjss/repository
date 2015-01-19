package com.uf.rest.bean.response;

public class ShopIncomeResponseData {
	private Float income;
	private Float balance;
	private Boolean has_card;
	private ResponseBankCard card;
	private ResponseWithDraw withdraw;
	public Float getIncome() {
		return income;
	}
	public void setIncome(Float income) {
		this.income = income;
	}
	public Float getBalance() {
		return balance;
	}
	public void setBalance(Float balance) {
		this.balance = balance;
	}
	public Boolean getHas_card() {
		return has_card;
	}
	public void setHas_card(Boolean has_card) {
		this.has_card = has_card;
	}
	public ResponseBankCard getCard() {
		return card;
	}
	public void setCard(ResponseBankCard card) {
		this.card = card;
	}
	public ResponseWithDraw getWithdraw() {
		return withdraw;
	}
	public void setWithdraw(ResponseWithDraw withdraw) {
		this.withdraw = withdraw;
	}
	
}
