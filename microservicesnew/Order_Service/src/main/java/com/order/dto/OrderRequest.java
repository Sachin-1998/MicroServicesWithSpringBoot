package com.order.dto;

import java.util.List;

import com.order.entities.OrderLineItems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderRequest 
{
	private  List<OrderLineItemsDto> orderLineItemsDtosList;
}
