package com.order.services;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import com.order.dto.InventoryResponse;
import com.order.dto.OrderLineItemsDto;
import com.order.dto.OrderRequest;
import com.order.entities.Order;
import com.order.entities.OrderLineItems;
import com.order.repositories.IOrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements IOrderMgmtService {

	private final IOrderRepository orderRepo;
	private final WebClient.Builder webClientBuilder;

	@Override
	public String placeOrder(OrderRequest orderRequest) {

		Order order = new Order();
		order.setOrderNumbers(UUID.randomUUID().toString());

		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtosList().stream()
				.map(orderLineItemsDto -> mapToDto(orderLineItemsDto)).collect(Collectors.toList());
		order.setOrderLineItems(orderLineItems);

		List<String> skuCodes = order.getOrderLineItems().stream().map(OrderLineItems::getSkuCode).toList();

		// call inventory service and place order if product is in stock
		// mono is data type in reactive framework ..able to read the data from
		// webclient response
		InventoryResponse[] inventoryResponsesArrays = webClientBuilder
				.build()
				.get()
				.uri("http://Inventory-Service/api/inventory",
						uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes)
						.build())
				.retrieve()
				.bodyToMono(InventoryResponse[].class)
				.block();// block for synchronous communication

		boolean allProductsIsInStock = Arrays.stream(inventoryResponsesArrays).allMatch(InventoryResponse::isInStock);
		if (allProductsIsInStock) {
			orderRepo.save(order);
			return "Order Place Successfully";
		} else {
			throw new IllegalArgumentException("Product is not in stock...please try again later..");
		}

	}

	private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
		OrderLineItems orderLineItem = new OrderLineItems();
		orderLineItem.setId(orderLineItemsDto.getId());
		orderLineItem.setPrice(orderLineItemsDto.getPrice());
		orderLineItem.setQuantity(orderLineItemsDto.getQuantity());
		orderLineItem.setSkuCode(orderLineItemsDto.getSkuCode());
		return orderLineItem;
	}
}
