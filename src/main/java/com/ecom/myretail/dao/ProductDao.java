package com.ecom.myretail.dao;

import java.util.List;

import com.ecom.myretail.model.Price;
import com.ecom.myretail.model.Product;

public interface ProductDao {
	
	String getProductQuery = "select prd.product_id  , prd.product_name , prd.sku , catprdrel.category_id "
			+ "from product prd , catprdrel catprdrel "
			+ "where prd.product_id =catprdrel.product_id and prd.product_id =:product_id";
	String getAllProductsQuery = "select prd.product_id as product_id , prd.product_name , prd.sku , catprdrel.category_id "
			+ "from product prd , catprdrel catprdrel "
			+ "where prd.product_id =catprdrel.product_id";
	String getCategorProductsByIdQuery = "select prd.product_id as product_id , prd.product_name , prd.sku , catprdrel.category_id "
			+ "from product prd , catprdrel catprdrel "
			+ "where prd.product_id =catprdrel.product_id and catprdrel.category_id = :category_id";
	String getCategorProductsByNameQuery = "select prd.product_id as product_id , prd.product_name , prd.sku , catprdrel.category_id "
			+ "from product prd , catprdrel catprdrel , category cat "
			+ "where prd.product_id =catprdrel.product_id and catprdrel.category_id = cat.category_id and cat.category_name= :category_name";
	String getProductPriceQuery = "select prc.product_id  , prc.regular_price , prc.sale_price , prc.clearance_price "
			+ "from  productprice prc  "
			+ "where prc.product_id = :product_id";
	
	
	public Product getProduct(Long productId);

	public List<Product> getProductSet();

	public List<Product> getProductSet(Long categoryId);

	public List<Product> getProductSet(String categoryName);
	
	public Price getProductPrice(Long productId);

}
