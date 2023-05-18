package com.inventory.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.entity.Inventory;

public interface InventoryRepo extends JpaRepository<Inventory, Integer> {

	//Optional<Inventory> getProductAvailableInStockBySkuCode(String skuCode);
	List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
