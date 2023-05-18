package com.order.services;

import com.order.dto.OrderRequest;

public interface IOrderMgmtService
{
	public String placeOrder(OrderRequest orderRequest);

}
