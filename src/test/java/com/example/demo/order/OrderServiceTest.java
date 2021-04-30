package com.example.demo.order;


import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.example.demo.BaseClass;
import com.example.demo.models.OrderStatus;
import com.example.demo.repository.IOrderStatusRepository;
import com.example.demo.service.OrderStatusService;


class OrderServiceTest extends BaseClass{
	
	@Mock
	IOrderStatusRepository iOrderStatusRepository;
	@InjectMocks
	OrderStatusService orderStatusService;
	
	@Test
	@DisplayName("create order status test case in service layer")
	void orderStatusCreate()
	{
		OrderStatus orderStatus=new OrderStatus();
		orderStatus.setOrderStatusId(UUID.randomUUID().toString());
		orderStatus.setCreatedBy("divya");
		orderStatus.setCreatedTime(LocalDateTime.now()); 
		orderStatus.setOrderStatusMesssage("order recived");
		orderStatusService.handleUserActions(orderStatus);
		Mockito.verify(iOrderStatusRepository).insertOrderStatusValues(orderStatus.getOrderStatusId(), orderStatus.getCreatedBy(), orderStatus.getCreatedTime(), orderStatus.getOrderStatusMesssage());
 

	}
	

}
