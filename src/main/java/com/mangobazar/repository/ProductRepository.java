package com.mangobazar.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.mangobazar.model.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {

}

