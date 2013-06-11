package com.uf.fanfan.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.uf.fanfan.common.ProductState;

import java.sql.Timestamp;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@TableGenerator(name = "Product_ID_GEN", table = "ID_TABLE", pkColumnName = "ID_KEY", valueColumnName = "ID_VALUE", pkColumnValue = "product_ID",allocationSize=1)
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="Product_ID_GEN")
	private Integer id;

	private Timestamp createTime;

	private String description;

	private byte[] image;

	private String imageFileName;

	private String name;

	private double price;

	private Integer saleSum;
	
	private ProductState state;

	@ManyToOne
	 // JoinColumn表示外键的列  
    @JoinColumn(name="shopid")  
	private Shop shop;

	public Product() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getImageFileName() {
		return this.imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	
	public Shop getShop() {
		return this.shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Integer getSaleSum() {
		return saleSum;
	}

	public void setSaleSum(Integer saleSum) {
		this.saleSum = saleSum;
	}
	@Column(name = "state")	@Enumerated(EnumType.STRING)
	public ProductState getState() {
		return state;
	}

	public void setState(ProductState state) {
		this.state = state;
	}
	
}