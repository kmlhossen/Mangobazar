package com.mangobazar.service;

import com.mangobazar.model.Category;

import java.util.Collection;

public interface CategoryService {
    Collection<Category> getAllCategory();
    Category getCategory(long id);
}
