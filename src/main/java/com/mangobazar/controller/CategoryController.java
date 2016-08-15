package com.mangobazar.controller;

import com.mangobazar.dto.CategoryDto;
import com.mangobazar.model.Category;
import com.mangobazar.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

@Api(value = "Category", description = "Operations about category")
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService service) {
        categoryService = service;
    }
    
    @PreAuthorize("hasPermission(#categoryDto,'create')")
    @ApiOperation(value = "Create category", notes = "Create category")
    @RequestMapping(method = RequestMethod.POST)
    public void createCategory(@RequestBody CategoryDto categoryDto, HttpServletResponse response) {
        categoryService.createCategory(categoryDto.getName());
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Get Category", notes = "Returns all the category", response = Category.class)
    public Collection<Category> Category() {
        return categoryService.getAllCategory();
    }

    @ApiOperation(value = "Get Category", notes = "Returns a category associated with the id", response = Category.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Category findOne(@ApiParam(value = "id of the category", required = true) @PathVariable long id) {
        return categoryService.getCategory(id);
    }

}
