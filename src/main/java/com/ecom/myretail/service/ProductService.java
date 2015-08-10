package com.ecom.myretail.service;

import java.util.List;

import com.ecom.myretail.model.Product;

public interface ProductService {
	
	public Product getProduct(Long  productId ) ;
	
	public List<Product> getProductSet (Long categoryId , String categoryName);

}
