package com.inventory.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.inventory.dto.InventoryResponse;
import com.inventory.entity.Inventory;

public interface IInventoryMgmtService {

	// public boolean IsProductInStock(String skuCode);
	@Transactional(readOnly = true)
	List<InventoryResponse> getBySkuCodeIn(List<String> skuCode);

}
