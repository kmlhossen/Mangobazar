package com.mangobazar.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mangobazar.model.ProductType;
import com.mangobazar.service.ProductTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "ProductType", description = "Operations about ProductType")
@RestController
@RequestMapping("/api/producttype")
public class ProductTypeController {
	  private final ProductTypeService productTypeService;

	    @Autowired()
	    public ProductTypeController(ProductTypeService service) {
	    	productTypeService = service;
	    }
	    
	    @PreAuthorize("hasRole('ROLE_ADMIN')")
	    @ApiOperation(value = "Create ProductType", notes = "Create ProductType")
	    @PostMapping
	    public void createProductType(@RequestBody ProductType productType, HttpServletResponse response) {
	    	productTypeService.addProductType(productType);
	    }
	    
	    @GetMapping("/getProductTypeListByProductIdvalue/{productId}")
	    @ApiOperation(value = "Get ProductType List", notes = "Returns all the ProductType of a Product",response = ProductType.class)
	    public Collection<ProductType> GetProductTypeList(@ApiParam(value = "id of the product", required = true) @PathVariable long productId) {
	        return productTypeService.getProductTypeListByProductId(productId);
	    }
	    
	    @GetMapping("/getProductTypeById/{productTypeId}")
	    @ApiOperation(value = "Get ProductType", notes = "Returns the ProductType", response = ProductType.class)
	    public ProductType getProductType(@ApiParam(value = "id of the productType", required = true) @PathVariable long productTypeId) {
	        return productTypeService.getProductTypeById(productTypeId);
	    }
	    
	    @PreAuthorize("hasRole('ROLE_ADMIN')")
	    @PatchMapping("{id}")
	    @ApiOperation(value = "Update ProductType Name", notes = "Updates the ProductType Name")
	    public void updateProductType(@ApiParam(value = "id of the product type", required = true) @PathVariable long id,@RequestBody String productTypeName, HttpServletResponse response) {
	         productTypeService.updateProductType(id,productTypeName);
	    }
	    
	    @PreAuthorize("hasRole('ROLE_ADMIN')")
	    @DeleteMapping("{productTypeId}")
	    @ApiOperation(value = "Remove ProductType", notes = "Removes the ProductType")
	    public void deleteProductType(@ApiParam(value = "id of the productType", required = true) @PathVariable long productTypeId) {
	        productTypeService.deleteProductType(productTypeId);
	    }
	   

}
