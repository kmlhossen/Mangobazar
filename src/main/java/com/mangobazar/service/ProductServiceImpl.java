package com.mangobazar.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mangobazar.model.Product;
import com.mangobazar.repository.ProductRepository;

@Service
@Transactional
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
		return productRepository.findAll();
	}

	@Override
	public Product createProduct(Product product) {
      return productRepository.findOne(productRepository.saveAndFlush(product).getId());
		
	}

	@Override
	public Product updateProduct(Product product) {
		
		return productRepository.saveAndFlush(product);
	}

	@Override
	public void deleteProduct(long id) {
		productRepository.delete(id);
		
	}



	
}
