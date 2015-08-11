package com.ecom.myretail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ecom.myretail.model.Product;
import com.ecom.myretail.service.ProductService;
import com.ecom.myretail.model.Price;


/**
 * This is Product and Price Controller. It exposes rest service to fetch product and price details.
 * @author nshetty
 */
@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	/**
	 * @param  productId
	 * @return Returns the Product details for the requested Product Id
	 */
	@RequestMapping(value = "/{productId}", method = RequestMethod.GET)
	public Product getProduct(@PathVariable Long productId){
		
		return productService.getProduct(productId);
		
	}
	
	/**
	 * Returns a list of Products with their details
	 * 
	 * @param categoryId
	 * @param categoryName
	 * @return Returns all the products if optional parameters categoryId and categoryName are not passed. 
	 * If categoryId or categoryName is passed, then its products are returned. 
	 * When both are passed,  products under the categoryId are returned (categoryName is ignored)
	 */
	@RequestMapping(value = "/product-set", method = RequestMethod.GET)
	public List <Product> getProductSet(@RequestParam (required= false ) Long categoryId  
			, @RequestParam  (required= false , defaultValue ="") String categoryName){
		
		return productService.getProductSet(categoryId , categoryName);
		
	}
	
	
	/**
	 * Returns the Product Price details
	 * 
	 * @param pid
	 * @return Returns Returns the Price for requested  Product Id 
	 */
	@RequestMapping(value = "/{productId}/price", method = RequestMethod.GET)
	public Price getProductPrice(@PathVariable("productId") Long pid) { 
		return productService.getProductPrice(pid);
	}
	
	

}
