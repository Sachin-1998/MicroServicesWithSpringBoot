package com.product.service;

import java.util.List;
import java.util.stream.Stream;

import com.product.dto.ProductRequest;
import com.product.dto.ProductResponse;

public interface IProductMgmtService 
{
	public void addProduct(ProductRequest productRequest);
	public List<ProductResponse> getAllProduct();
}
