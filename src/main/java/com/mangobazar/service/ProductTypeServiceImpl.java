package com.mangobazar.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mangobazar.model.ProductType;
import com.mangobazar.repository.ProductTypeRepository;

@Transactional
@Service
public class ProductTypeServiceImpl implements ProductTypeService {

	private final ProductTypeRepository productTypeRepository;
	@Autowired
	public ProductTypeServiceImpl(ProductTypeRepository productTypeRepository)
	{
		this.productTypeRepository=productTypeRepository;
	}
	
	@Override
	public void addProductType(ProductType productType) {
	
		productTypeRepository.saveAndFlush(productType);

	}

	@Override
	public void updateProductType(ProductType productType) {
		productTypeRepository.saveAndFlush(productType);

	}

	@Override
	public Set<ProductType> getProductTypeListByProductId(long productId) {
		return productTypeRepository.findByProductId(productId);
	}

	@Override
	public void deleteProductType(long porductTypeId) {
		productTypeRepository.delete(porductTypeId);

	}

	@Override
	public ProductType getProductTypeById(long id) {

		return productTypeRepository.findOne(id);
	}

}
