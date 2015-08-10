package com.ecom.myretail.model;

public class Product implements java.io.Serializable {

	private static final long serialVersionUID = 5267766547116217152L;
	
	private Long   	productId;
	private String   	sku;
	private String 	productName;
	private Long 	categoryId;
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	
	

}
