package com.uf.fanfan.entity;

// Generated 2013-10-10 21:40:33 by Hibernate Tools 3.4.0.CR1

/**
 * Productimage generated by hbm2java
 */
public class ProductImage implements java.io.Serializable {

	private int id;
	private Product product;
	private byte[] bitImage;
	private String imageFileExtName;

	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public byte[] getBitImage() {
		return this.bitImage;
	}

	public void setBitImage(byte[] bitImage) {
		this.bitImage = bitImage;
	}

	public String getImageFileExtName() {
		return this.imageFileExtName;
	}

	public void setImageFileExtName(String imageFileExtName) {
		this.imageFileExtName = imageFileExtName;
	}

}
