package com.uf.stock.bean;

public class Stock {
    public static final String STOCK_TYPE_SHANG_HAI="sh";
    public static final String STOCK_TYPE_SHEN_ZHEN="sz";
    public static final String STOCK_TYPE_CHUANG_YE="cy";
    
	private Integer code;
	private String name;
	private String type;
	
	
	public Integer getCode() {
    return code;
  }
  public void setCode(Integer code) {
    this.code = code;
  }
  public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
	
}
