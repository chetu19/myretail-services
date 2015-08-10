package com.ecom.myretail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.myretail.model.Product;
import com.ecom.myretail.service.ProductService;


@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/{productId}", method = RequestMethod.GET)
	public Product getProduct(@PathVariable Long productId){
		
		return productService.getProduct(productId);
		
	}
	
	@RequestMapping(value = "/product-set", method = RequestMethod.GET)
	public List <Product> getProductSet(@RequestParam (required= false ) Long categoryId  
			, @RequestParam  (required= false , defaultValue ="") String categoryName){
		
		return productService.getProductSet(categoryId , categoryName);
		
	}
	
	

}
