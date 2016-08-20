package com.mangobazar.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.mangobazar.model.Product;
import com.mangobazar.service.ProductService;
import com.mangobazar.views.ProductViews;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "product", description = "Operations about product")

@RestController
@RequestMapping("/api/product")
public class ProductController {

	private final ProductService productService;

	@Autowired
	public ProductController(ProductService service) {
		productService = service;
	}

	
	@JsonView(ProductViews.View.class)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping
	@ApiOperation(value = "create product", notes = "Creates and returns a product")
	public void createProduct(@RequestBody @JsonView(ProductViews.Create.class) Product product,
			HttpServletResponse response) {
			productService.createProduct(product);
	}

	@JsonView(ProductViews.View.class)
	@ApiOperation(value = "Get Product", notes = "Returns all the product")
	@GetMapping
	public Collection<Product> Product(HttpServletResponse response) {
		return productService.getAllProduct();
		

	}

	@JsonView(ProductViews.View.class)
	@ApiOperation(value = "Get Product", notes = "Returns a product associated with the id")
	@GetMapping(value = "/{id}")
	public Product findOne(@ApiParam(value = "id of the product", required = true) @PathVariable long id,
			HttpServletResponse response) {
		return  productService.getProduct(id);

	}

}
