package com.mangobazar.service;

import com.mangobazar.model.Category;
import com.mangobazar.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Implements category service interface.
 */
@Transactional
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(String name) {
        Category category = new Category();
        category.setName(name);
        return categoryRepository.saveAndFlush(category);
    }

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
