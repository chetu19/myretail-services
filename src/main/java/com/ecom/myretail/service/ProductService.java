package com.ecom.myretail.service;

import java.util.List;

import com.ecom.myretail.model.Price;
import com.ecom.myretail.model.Product;

/**This is Product and Price Service Interface. It exposes necessary methods
 * to create API for fetching product and price  details.
 * @author nshetty
 *
 */
public interface ProductService {
	
	/**
	 * This method fetches the product details for given productId.
	 */
	public Product getProduct(final Long  productId ) ;
	
	/**
	 * Returns a list of Products with their details
	 * 
	 * @param categoryId
	 * @param categoryName
	 * @return Returns all the products if optional parameters categoryId and categoryName are not passed. 
	 * If categoryId or categoryName is passed, then its products are returned. 
	 * When both are passed,  products under the categoryId are returned (categoryName is ignored)
	 */
	public List<Product> getProductSet (final Long categoryId , final String categoryName);

	/**
	 * This method fetches the Price details of  given productId.
	 */
	public Price getProductPrice(final Long productId);

}
