package com.ecom.myretail.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import com.ecom.myretail.model.Price;
import com.ecom.myretail.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	


	public Product getProduct( Long productId) {
		
		SqlParameterSource namedParameters = new MapSqlParameterSource("product_id", productId);
		
		return this.jdbcTemplate.queryForObject( getProductQuery , namedParameters ,  
						new BeanPropertyRowMapper <Product>(Product.class) );	 
	}
	
	public List<Product> getProductSet() {
		
		return this.jdbcTemplate.query(getAllProductsQuery, new BeanPropertyRowMapper <Product>(Product.class));
	}
	
	
	public List<Product> getProductSet(Long categoryId) {
		
		SqlParameterSource namedParameters = new MapSqlParameterSource("category_id", categoryId);
		return this.jdbcTemplate.query(getCategorProductsByIdQuery, namedParameters, new BeanPropertyRowMapper <Product>(Product.class));
	}
	
	public List<Product> getProductSet(String categoryName) {
		
		SqlParameterSource namedParameters = new MapSqlParameterSource("category_name", categoryName);
		return this.jdbcTemplate.query(getCategorProductsByNameQuery, namedParameters, new BeanPropertyRowMapper <Product>(Product.class));
	}

	public Price getProductPrice(Long productId) {
		
		SqlParameterSource namedParameters = new MapSqlParameterSource("product_id", productId);
		return this.jdbcTemplate.queryForObject( getProductPriceQuery , namedParameters ,  
						new BeanPropertyRowMapper <Price>(Price.class) );	
	
		
	}
	

}
