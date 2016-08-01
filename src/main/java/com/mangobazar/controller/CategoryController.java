package com.mangobazar.controller;

import com.mangobazar.model.Category;
import com.mangobazar.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

@Api(value = "category", description = "Operations about category")

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService service) {
    	categoryService = service;
    }


	@RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Get Category", notes = "Returns all the category")
	public Collection<Category> Category(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
		return categoryService.getAllCategory();
	}

    @ApiOperation(value = "Get Category", notes = "Returns a category associated with the id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Category findOne(@ApiParam(value = "id of the category", required = true)  @PathVariable long id) {
        return categoryService.getCategory(id);
    }

}
