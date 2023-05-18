package com.order.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

import com.order.dto.OrderRequest;
import com.order.services.IOrderMgmtService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
	private final IOrderMgmtService service;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
	//@TimeLimiter(name = "inventory")
	//@Retry(name = "inventory")
	public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest) {

		return CompletableFuture.supplyAsync(() -> service.placeOrder(orderRequest));

	}

	public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException) {
		return CompletableFuture.supplyAsync(() -> "Oops! Something went wrong, please order after some time!");

	}
	/*
	 * public String placeOrder(@RequestBody OrderRequest orderRequest) {
	 * 
	 * service.placeOrder(orderRequest); return "Order placed Successfully";
	 * 
	 * }
	 */
	/*
	 * public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest,
	 * RuntimeException runtimeException) { return CompletableFuture.supplyAsync(()
	 * -> "Oops! Something went wrong, please order after some time!");
	 * 
	 * }
	 */

}
