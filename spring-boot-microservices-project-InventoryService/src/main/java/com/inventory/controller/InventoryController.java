package com.inventory.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.service.IInventoryMgmtService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

	private final IInventoryMgmtService service;
	@GetMapping("/{skuCode}")
	@ResponseStatus(HttpStatus.OK)
	public boolean isInStock(@PathVariable String skuCode)
	{
		return service.IsProductInStock(skuCode);
	}
}
