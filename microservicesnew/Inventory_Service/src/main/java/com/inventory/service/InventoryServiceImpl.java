package com.inventory.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.inventory.Repository.InventoryRepo;
import com.inventory.dto.InventoryResponse;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements IInventoryMgmtService {

	private final InventoryRepo repo;

	@SneakyThrows // To throw exception temporarely not recommeded to use in prod
	@Override
	public List<InventoryResponse> getBySkuCodeIn(List<String> skuCode) {
		/*
		 * log.info("Wait started");
		 * 
		 * Thread.sleep(10000);
		 * 
		 * log.info("Wait ended");
		 */

		return repo
				.findBySkuCodeIn(skuCode).stream().map(inventory -> InventoryResponse.builder()
						.skuCode(inventory.getSkuCode()).isInStock(inventory.getQuantity() > 0).build())
				.collect(Collectors.toList());

	}

}
