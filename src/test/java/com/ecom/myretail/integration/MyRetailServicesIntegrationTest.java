package com.ecom.myretail.integration;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import org.dbunit.ext.h2.H2Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ecom.myretail.dao.ProductDao;
import com.ecom.myretail.model.Product;
/*import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;*/

/**
 * @author nshetty
 * 
 * This is an integration test class. This class tests exposed HTTP APIs end to end.
 * It also tests the database connectivity and SQL queries against In memory  H2 database 
 *
 */

@ContextConfiguration(locations ="classpath:rest-service-servlet.xml" )
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    //DbUnitTestExecutionListener.class 
    })
@WebAppConfiguration
//@DatabaseSetup("classpath:DBTestData.xml")
public class MyRetailServicesIntegrationTest extends AbstractTestNGSpringContextTests {
	

	@Autowired
	ProductDao productDao;
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	

	@BeforeTest
	public void setUp() {
		
	}
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
	
	/**
	 * Tests the DAO layer method (tests if data can be retrieved from DB)
	 * */
	@Test
    public void testGetProductReturnValidProductFromDB() {	
		Product product =productDao.getProduct(new Long(1001));
        Assert.assertEquals(product.getProductId(), new Long(1001));
        Assert.assertEquals(product.getProductName(), "test product1");
        Assert.assertEquals(product.getCategoryId(), new Long(9999));
        Assert.assertEquals(product.getSku(), "abc123");
    }
	
	
	@Test
    public void testProductNotFound() throws Exception {
        mockMvc.perform(get("/product/{productId}" ,"77777")
        	.contentType(contentType))
        	.andExpect(status().isNotFound());;
    }
	
	
	@DataProvider(name = "validProducts")
	public static Object[][] products() {
		
		Product product1 = new Product();
		product1.setProductId(new Long(1001));
		product1.setProductName("test product1");
		product1.setCategoryId(new Long(9999));
		product1.setSku("abc123");
		
		return new Object[][] { 
				{ "1001" , product1} 
			};
	}
	
	@Test(dataProvider="validProducts")
    public void testFetchValidProduct( String inputProductId , Product expectedProduct) throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        mockMvc.perform(get("/product/{productId}" ,inputProductId)
        	.contentType(contentType))
        	.andExpect(status().isOk())
        	.andExpect(content().contentType(contentType)).
        	andExpect(jsonPath("$.productId", is(1001)));
    }
	
	
	
		
}
