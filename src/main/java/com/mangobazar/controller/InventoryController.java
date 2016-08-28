package com.mangobazar.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mangobazar.model.Inventory;
import com.mangobazar.service.InventoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "Inventory")
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

	private final InventoryService inventoryService;

	@Autowired
	public InventoryController(InventoryService service) {
		inventoryService = service;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ApiOperation(value = "Create inventory", notes = "Create inventory")
	@PostMapping
	public void createInventory(@RequestBody Inventory inventory, HttpServletResponse response) {
		inventoryService.addInventory(inventory);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ApiOperation(value = "update inventory", notes = "update inventory")
	@PostMapping(value = "/update")
	public void updateInventory(@RequestBody Inventory inventory, HttpServletResponse response) {
		inventoryService.updateInventory(inventory);
	}

	@GetMapping(value = "/sku/{skuId}")
	@ApiOperation(value = "Get inventory", notes = "Returns the inventory of sku", response = Inventory.class)
	public Inventory Inventory(@ApiParam(value = "skuId of the sku", required = true) @PathVariable long skuId) {
		return inventoryService.getInventoryBySku(skuId);
	}

	@ApiOperation(value = "Get Inventory", notes = "Returns a inventory associated with the id", response = Inventory.class)
	@GetMapping(value = "/{id}")
	public Inventory findOne(@ApiParam(value = "id of the inventory", required = true) @PathVariable long id) {
		return inventoryService.getInventory(id);
	}

}
