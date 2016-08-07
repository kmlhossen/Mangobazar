package com.mangobazar.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangobazar.model.Category;
import com.mangobazar.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private  CategoryRepository categoryRepository;

	@Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
    }
	


	@Override
	public Category getCategory(long id) {
		return categoryRepository.findOne(id);
	}



	@Override
	public Collection<Category> getAllCategory() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}



	
}
