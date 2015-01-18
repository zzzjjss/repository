package com.uf.rest.bean.request;

public class ShopUserInfoUpdateRequest {
	private String token;
	private Integer p;
	private String name;
	private String id_card;
	private byte[] card_image;
	private String password;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Integer getP() {
		return p;
	}
	public void setP(Integer p) {
		this.p = p;
	}
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
