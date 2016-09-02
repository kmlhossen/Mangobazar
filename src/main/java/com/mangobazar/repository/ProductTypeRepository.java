package com.mangobazar.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mangobazar.model.ProductType;
@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {

	Set<ProductType> findByProductId(long productId);
}
