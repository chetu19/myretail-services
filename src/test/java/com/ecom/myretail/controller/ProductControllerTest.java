package com.ecom.myretail.controller;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ecom.myretail.exception.MyRetailServiceException;
import com.ecom.myretail.model.Product;
import com.ecom.myretail.service.ProductService;



public class ProductControllerTest {
	
	@InjectMocks
	ProductController productController;
	
	@Mock
	ProductService productService;
	
	@BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

	@DataProvider(name = "controllerTestData1")
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

	@Test(dataProvider = "controllerTestData1")
	public void testValidProducts(Long productId, Product expectedProduct,
			Product mockProduct ) {
		
		when(productService.getProduct(productId)).thenReturn(mockProduct);
		Product product = productService.getProduct(productId);
		Assert.assertEquals(product, expectedProduct , "Product should same as the one returned from Service Layer");
		Assert.assertEquals(product.getProductId(), expectedProduct.getProductId() , "Product Id should match with the product Id from the Service");

	}
	
	@Test(expectedExceptions = MyRetailServiceException.class , expectedExceptionsMessageRegExp = "Product with Id 234 not found")
	public void testInvalidProduct() {
		
		when(productService.getProduct(new Long(234))).thenThrow(new MyRetailServiceException("Product with Id 234 not found"));
		productService.getProduct(new Long(234));

	}
	
	

}
