package com.mangobazar.service;

import java.util.Collection;

import com.mangobazar.model.Category;

/**
 * Defines available category service.
 */

public interface CategoryService {
    Category createCategory(String name);

    Collection<Category> getAllCategory();

    Category getCategory(long id);
}
