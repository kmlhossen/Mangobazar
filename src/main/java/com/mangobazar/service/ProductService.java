package com.mangobazar.service;

import com.mangobazar.model.Product;

import java.util.Collection;

public interface ProductService {
    Collection<Product> getAllProduct();
    Product getProduct(long id);
	

}
