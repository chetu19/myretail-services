package com.ecom.myretail.constants;

public class SQLConstants {
	
	
	public static final String PRODUCT_ID 	= "product_id";
	public static final String CATEGORY_ID = "category_id";
	public static final String CATEGORY_NAME = "category_name";
	
	//*** Ideally these Queries should be read from a properties file****************
	public static final String GET_PRODUCT_QUERY = "select prd.product_id  , prd.product_name , prd.sku , catprdrel.category_id "
			+ "from myretaildb.product prd , myretaildb.catprdrel catprdrel "
			+ "where prd.product_id =catprdrel.product_id and prd.product_id =:product_id";
	public static final String GET_ALLPRODUCTS_QUERY = "select prd.product_id as product_id , prd.product_name , prd.sku , catprdrel.category_id "
			+ "from myretaildb.product prd , myretaildb.catprdrel catprdrel "
			+ "where prd.product_id =catprdrel.product_id";
	public static final String GET_CATEGORY_PRODUCTS_BY_ID_QUERY = "select prd.product_id as product_id , prd.product_name , prd.sku , catprdrel.category_id "
			+ "from myretaildb.product prd , myretaildb.catprdrel catprdrel "
			+ "where prd.product_id =catprdrel.product_id and catprdrel.category_id = :category_id";
	public static final String GET_CATEGORY_PRODUCTS_BY_NAME_QUERY = "select prd.product_id as product_id , prd.product_name , prd.sku , catprdrel.category_id "
			+ "from myretaildb.product prd , myretaildb.catprdrel catprdrel , myretaildb.category cat "
			+ "where prd.product_id =catprdrel.product_id and catprdrel.category_id = cat.category_id and cat.category_name= :category_name";
	public static final String GET_PRODUCT_PRICE_QUERY = "select prc.product_id  , prc.regular_price , prc.sale_price , prc.clearance_price "
			+ "from  myretaildb.productprice prc  "
			+ "where prc.product_id = :product_id";
	

}
