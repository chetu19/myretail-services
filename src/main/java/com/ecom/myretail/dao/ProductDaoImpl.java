package com.ecom.myretail.dao;

import static com.ecom.myretail.constants.SQLConstants.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import com.ecom.myretail.model.Price;
import com.ecom.myretail.model.Product;

/**
 * @author nshetty
 * 
 * This is an implementation class for fetching the product and price details from underlying database.
 *
 */

@Repository
public class ProductDaoImpl implements ProductDao {
	
	@Autowired
	private NamedParameterJdbcOperations jdbcTemplate;
	

	/**
	 * This method fetches and returns the product details for the given productId. 
	 * @param productId
	 * @return
	 */
	public Product getProduct(final  Long productId) {
		
		SqlParameterSource namedParameters = new MapSqlParameterSource(PRODUCT_ID, productId);
		
		return this.jdbcTemplate.queryForObject( GET_PRODUCT_QUERY , namedParameters ,  
						new BeanPropertyRowMapper <Product>(Product.class) );	 
	}
	
	/**
	 * This method fetches and returns all the valid products in the system.
	 * @return
	 */
	public List<Product> getProductSet() {
		
		return this.jdbcTemplate.query(GET_ALLPRODUCTS_QUERY, new BeanPropertyRowMapper <Product>(Product.class));
	}
	
	/**
	 * This method fetches and returns all the valid products under the given category  id.
	 * @return
	 */
	public List<Product> getProductSet(final Long categoryId) {
		
		SqlParameterSource namedParameters = new MapSqlParameterSource(CATEGORY_ID, categoryId);
		return this.jdbcTemplate.query(GET_CATEGORY_PRODUCTS_BY_ID_QUERY, namedParameters, new BeanPropertyRowMapper <Product>(Product.class));
	}
	
	/**
	 * This method fetches and returns all the valid products under the given category name.
	 * @return
	 */
	public List<Product> getProductSet(final String categoryName) {
		
		SqlParameterSource namedParameters = new MapSqlParameterSource(CATEGORY_NAME, categoryName);
		return this.jdbcTemplate.query(GET_CATEGORY_PRODUCTS_BY_NAME_QUERY, namedParameters, new BeanPropertyRowMapper <Product>(Product.class));
	}
	
	/**
	 * This method fetches and returns product price details for the given product Id.
	 * @return
	 */
	public Price getProductPrice(final Long productId) {
		
		SqlParameterSource namedParameters = new MapSqlParameterSource(PRODUCT_ID, productId);
		return this.jdbcTemplate.queryForObject( GET_PRODUCT_PRICE_QUERY , namedParameters ,  
						new BeanPropertyRowMapper <Price>(Price.class) );	
	
		
	}
	

}
