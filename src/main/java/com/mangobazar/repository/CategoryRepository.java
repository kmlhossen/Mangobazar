package com.mangobazar.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.mangobazar.model.Category;


public interface CategoryRepository extends JpaRepository<Category, Long> {

}

