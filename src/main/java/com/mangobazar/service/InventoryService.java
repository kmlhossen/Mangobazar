package com.mangobazar.service;

import com.mangobazar.model.Inventory;

public interface InventoryService {
	void addInventory(Inventory inventory);
	void updateInventory(Inventory inventory);
	Inventory getInventory(long inventoryId);
	Inventory getInventoryBySku(long skuId);

}
