package com.uf.fanfan.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

import com.uf.fanfan.common.ProductState;


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

	private String name;

	private double price;

	private Integer saleSum;
	
	private ProductState state;
	
	@OneToMany(mappedBy="product")
	private List<ProductImage> images;
	
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

	public List<ProductImage> getImages() {
		return images;
	}

	public void setImages(List<ProductImage> images) {
		this.images = images;
	}
	
	
}