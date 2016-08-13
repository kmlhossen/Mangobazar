package com.mangobazar.controller;

import java.util.Collection;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mangobazar.service.ProductService;
import com.mangobazar.model.Product;

@Api(value = "product",description = "Operations about product")

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	private final ProductService productyService;

    @Autowired
    public ProductController(ProductService service) {
    	productyService = service;
    }


	@RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Get Category", notes = "Returns all the product")
	public Collection<Product> Category(){
		return productyService.getAllProduct();
	}

    @ApiOperation(value = "Get Product", notes = "Returns a product associated with the id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product findOne(@ApiParam(value = "id of the product", required = true)  @PathVariable long id) {
        return productyService.getProduct(id);
    }


}
