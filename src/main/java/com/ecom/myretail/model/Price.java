package com.ecom.myretail.model;

public class Price implements java.io.Serializable {

	private static final long serialVersionUID = 6362118694847719369L;
	
	private Long 	productId;
	private Double  regularPrice;
	private Double  salePrice;
	private Double  clearancePrice;
	
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Double getRegularPrice() {
		return regularPrice;
	}
	public void setRegularPrice(Double regularPrice) {
		this.regularPrice = regularPrice;
	}
	public Double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}
	public Double getClearancePrice() {
		return clearancePrice;
	}
	public void setClearancePrice(Double clearancePrice) {
		this.clearancePrice = clearancePrice;
	}
	
	
	
	

}
