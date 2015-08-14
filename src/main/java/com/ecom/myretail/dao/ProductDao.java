package com.ecom.myretail.dao;

import java.util.List;
import com.ecom.myretail.model.Price;
import com.ecom.myretail.model.Product;

/**
 * @author nshetty
 * 
 * This is an interface for fetching the product and price details from underlying database.
 *
 */

public interface ProductDao {
	

	
	/**
	 * This method  returns the product details for the given productId. 
	 * @param productId
	 * @return
	 */
	public Product getProduct(final Long productId);
	
	/**
	 * This method  returns all the valid products in the system.
	 * @return
	 */
	public List<Product> getProductSet();
	
	/**
	 * This method fetches and returns all the valid products under the given category  id.
	 * @return
	 */
	public List<Product> getProductSet(final Long categoryId);

	/**
	 * This method fetches and returns all the valid products under the given category name.
	 * @return
	 */
	public List<Product> getProductSet(final String categoryName);
	
	/**
	 * This method fetches and returns product price details for the given product Id.
	 * @return
	 */
	public Price getProductPrice(final Long productId);

}
