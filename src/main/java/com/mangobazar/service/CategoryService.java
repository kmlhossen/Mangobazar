package com.mangobazar.service;

import com.mangobazar.model.Category;
import java.util.Collection;

/**
 * Defines available category service.
 */
public interface CategoryService {
    Category createCategory(String name);
    Collection<Category> getAllCategory();
    Category getCategory(long id);
}
