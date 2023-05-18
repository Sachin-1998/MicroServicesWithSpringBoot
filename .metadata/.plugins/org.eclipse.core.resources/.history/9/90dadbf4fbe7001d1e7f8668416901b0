package com.product.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.product.dto.ProductRequest;
import com.product.dto.ProductResponse;
import com.product.entity.Product;
import com.product.repository.IProductRepo;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements IProductMgmtService{

	
	private  final IProductRepo productRepo;
	
	@Override
	public void addProduct(ProductRequest productRequest) 
	{
		Product product=Product.builder()
				.product_name(productRequest.getProduct_name())
				.product_description(productRequest.getProduct_description())
				.product_price(productRequest.getProduct_price())
				.build();
		productRepo.insert(product);
		log.info("Product {} is saved",product.getProduct_id());
		
	}

	@Override
	public List<ProductResponse> getAllProduct() 
	{
		List<Product> list=this.productRepo.findAll();
		List<ProductResponse> products=list.stream().map(product -> this.MapToProductResponse(product)).collect(Collectors.toList());
		return products;
		
	}

	private ProductResponse MapToProductResponse(Product product) {
		
		return ProductResponse.builder()
				.product_id(product.getProduct_id())
				.product_name(product.getProduct_name())
				.product_description(product.getProduct_description())
				.product_price(product.getProduct_price())
				.build();
	}

}
