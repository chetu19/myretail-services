package com.ecom.myretail.service;

import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataRetrievalFailureException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.ecom.myretail.dao.ProductDao;
import com.ecom.myretail.exception.MyRetailServiceException;
import com.ecom.myretail.model.Product;

/**
 * This is a test class that tests the methods inside ProductService class.
 * @author nshetty
 *
 */
public class ProductServiceTest {

	@InjectMocks
	ProductService productService = new ProductServiceImpl();
	
	@Mock ProductDao productDao;
	
	@BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

	@DataProvider(name = "serviceTest1")
	public static Object[][] productIds() {
		
		Product expectedProduct1 = new Product();
		expectedProduct1.setProductId(new Long(1));
		Product mockProduct1 = new Product();
		mockProduct1.setProductId(new Long(1));
		
		Product expectedProduct2 = new Product();
		expectedProduct2.setProductId(new Long(3));
		Product mockProduct2 = new Product();
		mockProduct2.setProductId(new Long(3));

		return new Object[][] { 
				{ new Long(1), expectedProduct1, mockProduct1 },
				{ new Long(3), expectedProduct2, mockProduct2  } 
			};
	}

	@Test(dataProvider = "serviceTest1")
	public void testValidProducts(Long productId, Product expectedProduct,
			Product mockProduct ) {
		
		when(productDao.getProduct(productId)).thenReturn(mockProduct);
		Product product = productService.getProduct(productId);
		Assert.assertEquals(product, expectedProduct , "Product should same as the one returned from DAO");
		Assert.assertEquals(product.getProductId(), expectedProduct.getProductId() , "Product Id should match with the product Id from the DAO");

	}
	
	@Test(expectedExceptions = MyRetailServiceException.class , expectedExceptionsMessageRegExp = "Product with Id 234 not found")
	public void testInvalidProduct() {
		
		when(productDao.getProduct(new Long(234))).thenThrow(new DataRetrievalFailureException("Exception XYZ while retrieving data from database"));
		productService.getProduct(new Long(234));

	}

}
