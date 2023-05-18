package com.order.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Override
	public void placeOrder(OrderRequest orderRequest) {

		Order order = new Order();
		order.setOrderNumbers(UUID.randomUUID().toString());

		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtosList().stream()
				.map(orderLineItemsDto -> mapToDto(orderLineItemsDto)).collect(Collectors.toList());
		order.setOrderLineItems(orderLineItems);
		orderRepo.save(order);

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
