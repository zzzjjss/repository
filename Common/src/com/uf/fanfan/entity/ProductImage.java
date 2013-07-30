package com.uf.fanfan.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;
@Entity
@TableGenerator(name = "ProductImage_ID_GEN", table = "ID_TABLE", pkColumnName = "ID_KEY", valueColumnName = "ID_VALUE", pkColumnValue = "productImage_ID",allocationSize=1)
public class ProductImage implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="ProductImage_ID_GEN")
	private Integer id;
	private byte[] image;
	private String imageFileExtName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getImageFileExtName() {
		return imageFileExtName;
	}
	public void setImageFileExtName(String imageFileExtName) {
		this.imageFileExtName = imageFileExtName;
	}
	public String getImageFileName() {
		if(id==null||imageFileExtName==null)
			return "";
		else
			return id.toString()+imageFileExtName;
	}
	
}
