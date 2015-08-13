package com.ecom.myretail.dao;

import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ecom.myretail.model.Product;


public class ProductDaoTest {
	
	@InjectMocks
	ProductDao productDao = new ProductDaoImpl();
	
	@Mock
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void getProductShouldReturnValidProduct() {

		Product mockProduct = new Product();
		mockProduct.setProductId(new Long(123));
		mockProduct.setSku("test sku");
		mockProduct.setProductName("test name");
		mockProduct.setCategoryId(new Long(9999));
		
		Mockito.when(
				jdbcTemplate.queryForObject(Mockito.anyString(), Mockito.any(SqlParameterSource.class),
						Mockito.any(RowMapper.class))).thenReturn(mockProduct);
			 
		Product expectedProduct = mockProduct; // Expected product is same as one the from JDBC template  . Created new variable for explanation purpose only
		Product actualProduct = productDao.getProduct(new Long(123));
		Assert.assertNotNull(actualProduct , "Product not be null when valid data is present in DB");
		Assert.assertEquals(actualProduct, expectedProduct , "Product should be same as the one returned JDBC Query");
		//--- Below test are not required as the above one already covers it.
		Assert.assertEquals(actualProduct.getProductId(), expectedProduct.getProductId() , "Product Id should be same as the one returned from DB");
		Assert.assertEquals(actualProduct.getProductName(),expectedProduct.getProductName() , "Product Name should be same as the one returned from DB");
		Assert.assertEquals(actualProduct.getSku(), expectedProduct.getSku(), "Product SKU should be same as the one returned from DB");
	}
	
	
	@SuppressWarnings("unchecked")
	@Test(expectedExceptions = EmptyResultDataAccessException.class )
	public void getProductShouldThrowExceptionForInvalidProductIdRequest() {

		Mockito.when(
				jdbcTemplate.queryForObject(Mockito.anyString(), Mockito.any(SqlParameterSource.class),
						Mockito.any(RowMapper.class))).thenThrow(new EmptyResultDataAccessException (1));
		productDao.getProduct(new Long(123));
		
	}
	
	@DataProvider(name = "daoProductSetTest")
	public static Object[][] productIds() {
		
		Product mockProduct1 = new Product();
		mockProduct1.setProductId(new Long(123));
		mockProduct1.setSku("test sku");
		mockProduct1.setProductName("test name");
		mockProduct1.setCategoryId(new Long(9999));
		
		Product mockProduct2 = new Product();
		mockProduct2.setProductId(new Long(123));
		mockProduct2.setSku("test sku");
		mockProduct2.setProductName("test name");
		mockProduct2.setCategoryId(new Long(9999));
		
		List <Product> mockProductList = new ArrayList<Product>();
		mockProductList.add(mockProduct1);
		mockProductList.add(mockProduct2);

		return new Object[][] { 
				{ new Long(9999) , mockProductList  }
			};
	}
	
	
	@SuppressWarnings("unchecked")
	@Test(dataProvider = "daoProductSetTest")
	public void getProductSetShouldReturnValidAndSameProducts( Long categoryId , List<Product> mockProductList) {

		
		List<Product> expectedProductList = mockProductList;
		Mockito.when(
				jdbcTemplate.query(Mockito.anyString(),  Mockito.any(SqlParameterSource.class), 
						Mockito.any(RowMapper.class))).thenReturn(mockProductList);
			 
		List<Product> productList = productDao.getProductSet(categoryId);
		Product [] actualProductsArray = productList.toArray(new Product[productList.size()]);
		Product [] expectedProductsArray = expectedProductList.toArray(new Product[productList.size()]);
		
		Assert.assertEqualsNoOrder(actualProductsArray, expectedProductsArray , "Products returned from DAO should be same as Products from JDBC Query");

	}
	
	
	
	
	

	
}
