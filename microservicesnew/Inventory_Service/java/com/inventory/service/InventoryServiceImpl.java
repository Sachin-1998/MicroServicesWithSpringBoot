package com.inventory.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inventory.Repository.InventoryRepo;
import com.inventory.entity.Inventory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements IInventoryMgmtService {

	private final InventoryRepo repo;

	

	@Override
	public boolean IsProductInStock(String skuCode) {
		
		Optional<Inventory> product = repo.getProductAvailableInStockBySkuCode(skuCode);
				if (product.isPresent()) {
					return true;
				} else

					return false;
	}

}
