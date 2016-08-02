package com.mangobazar.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangobazar.model.Category;
import com.mangobazar.repository.CategoryRepository;

/**
 * Implements category service interface.
 */
@Service
public class CategoryServiceImpl implements CategoryService {
	
	private final CategoryRepository categoryRepository;

	@Autowired
    public CategoryServiceImpl(CategoryRepository repository) {
		categoryRepository = repository;
    }
	
	@Override
	public Collection<Category> getAllCategory() {
		return categoryRepository.findAll();
	}

	@Override
	public Category getCategory(long id) {
		return categoryRepository.findOne(id);
	}
}
