package com.uf.rest.bean.response;

public class GetShopUserInfoResponseData {
	private String name;
	private String id_card;
	private byte[] card_image;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId_card() {
		return id_card;
	}
	public void setId_card(String id_card) {
		this.id_card = id_card;
	}
	public byte[] getCard_image() {
		return card_image;
	}
	public void setCard_image(byte[] card_image) {
		this.card_image = card_image;
	}
	
}
