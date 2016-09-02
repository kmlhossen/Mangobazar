package com.mangobazar.service;

import java.util.Set;

import com.mangobazar.model.ProductType;

public interface ProductTypeService {
	
	public void addProductType(ProductType productType);
	public void updateProductType(long id,String productName);
	public Set<ProductType> getProductTypeListByProductId(long productId);
	
	public ProductType getProductTypeById(long Id);
	
	public void deleteProductType(long porductTypeId);

}
