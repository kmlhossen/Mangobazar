package com.mangobazar.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangobazar.model.Product;
import com.mangobazar.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private  ProductRepository productRepository;

	@Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
    }

	@Override
	public Product getProduct(long id) {
		return productRepository.findOne(id);
	}



	@Override
	public Collection<Product> getAllProduct() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}



	
}
