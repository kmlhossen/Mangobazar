package com.mangobazar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;
import com.mangobazar.model.Inventory;
import com.mangobazar.repository.InventoryRepository;

@Service
public class InventoryServiceImpl implements InventoryService {
	
	private InventoryRepository inventoryRespository; 
	
	@Autowired
	public InventoryServiceImpl(InventoryRepository inventoryRespository)
	{
		this.inventoryRespository=inventoryRespository;
	}

	@Override
	public void addInventory(Inventory inventory) {
		
		inventoryRespository.saveAndFlush(inventory);
	}

	@Override
	public void updateInventory(Inventory inventory) {
		
		if(inventoryRespository.findOne(inventory.getId())==null)
			throw  new NoSuchElementException ("No element of type inventory found to update");
		
		inventoryRespository.saveAndFlush(inventory);

	}

	@Override
	public Inventory getInventory(long inventoryId) {
		
		return inventoryRespository.getOne(inventoryId);
	}

	@Override
	public Inventory getInventoryBySku(long skuId) {
		// TODO will be implemented after sku model is implemented
		return null;
	}

}
