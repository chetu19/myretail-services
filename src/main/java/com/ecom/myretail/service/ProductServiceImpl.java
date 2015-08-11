package com.ecom.myretail.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.ecom.myretail.dao.ProductDao;
import com.ecom.myretail.exception.MyRetailServiceException;
import com.ecom.myretail.model.Price;
import com.ecom.myretail.model.Product;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductDao productDao;
	
	public Product  getProduct(Long  productId ){
		
		try{
			return  productDao.getProduct(productId);
		}catch(RuntimeException ex){
			String errorMsg = String.format("Product with Id %d not found", productId);
			throw new MyRetailServiceException(errorMsg , ex );
		}
	}

	public List<Product> getProductSet(Long categoryId, String categoryName) {
		
		try{
			if(null == categoryId && StringUtils.isEmpty(categoryName)){
				return productDao.getProductSet();
			}else if (null != categoryId){
				return productDao.getProductSet( categoryId);
			}else {
				return productDao.getProductSet( categoryName);
			}
		}catch(RuntimeException ex){
			String errorMsg = String.format("No Products Found");
			throw new MyRetailServiceException(errorMsg , ex );
		}
	}

	public Price getProductPrice(Long productId) {
		try{
			return productDao.getProductPrice(productId);
		}catch(RuntimeException ex){
			String errorMsg = String.format("Price found for Product with Id %d", productId);
			throw new MyRetailServiceException(errorMsg , ex );
		}
	}

}
