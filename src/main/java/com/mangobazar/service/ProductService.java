package com.mangobazar.service;

import com.mangobazar.model.Product;

import java.util.Collection;

public interface ProductService {
	Product createProduct(Product product);
    Collection<Product> getAllProduct();
    Product getProduct(long id);
    
    Product updateProduct(Product product);
    
    void deleteProduct(long id);
	

}
