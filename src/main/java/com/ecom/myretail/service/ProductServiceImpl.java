package com.ecom.myretail.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.ecom.myretail.dao.ProductDao;
import com.ecom.myretail.exception.MyRetailServiceException;
import com.ecom.myretail.model.Price;
import com.ecom.myretail.model.Product;

/**This is Product and Price Service Implementation Class. It exposes necessary methods
 * to  product and price  details from the system.
 * 
 * @author nshetty
 *
 */
@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductDao productDao;
	
	/**
	 * This method fetches the product details for given productId.
	 */
	public Product  getProduct(final Long  productId ){
		
		try{
			return  productDao.getProduct(productId);
		}catch(RuntimeException ex){
			String errorMsg = String.format("Product with Id %d not found", productId);
			throw new MyRetailServiceException(errorMsg , ex );
		}
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
	public List<Product> getProductSet(final  Long categoryId, final  String categoryName) {
		
		
		try{
			List <Product> productSet;
			if(null == categoryId && StringUtils.isEmpty(categoryName)){
				productSet = productDao.getProductSet(); //Returns all the products if optional parameters categoryId and categoryName are not passed
			}else if (null != categoryId){
				productSet =  productDao.getProductSet( categoryId); // Returns the products under the given categoryId
			}else {
				productSet = productDao.getProductSet( categoryName); // Returns the products under the given category Name
			}
			
			return productSet;
		}catch(RuntimeException ex){
			String errorMsg = String.format("No Products Found");
			throw new MyRetailServiceException(errorMsg , ex );
		}
		
		
	}
	
	/**
	 * This method fetches the Price details of  given productId.
	 */
	public Price getProductPrice(final Long productId) {
		try{
			return productDao.getProductPrice(productId);
		}catch(RuntimeException ex){
			String errorMsg = String.format("Price found for Product with Id %d", productId);
			throw new MyRetailServiceException(errorMsg , ex );
		}
	}

}
