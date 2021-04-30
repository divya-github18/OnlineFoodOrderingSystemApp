package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.demo.models.OrderStatus;
import com.example.demo.repository.IOrderStatusRepository;
import com.example.demo.util.EntitlementUtils;
import com.example.demo.util.StandardReleaseResponse;

@Service
@Transactional
public class OrderStatusService {

	@Autowired
	IOrderStatusRepository iOrderStatusRepository;

	public ResponseEntity<StandardReleaseResponse> handleUserActions(OrderStatus orderStatus) {

		return createOrderstatus(orderStatus);
		
	}
	/**
	 * CreateOrderstatus
	 * @param orderStatus
	 * @return response entity
	 */
	private ResponseEntity<StandardReleaseResponse> createOrderstatus(OrderStatus orderStatus) {
		orderStatus.setOrderStatusId(UUID.randomUUID().toString());
		orderStatus.setCreatedTime(LocalDateTime.now());
		iOrderStatusRepository.insertOrderStatusValues(orderStatus.getOrderStatusId(),orderStatus.getCreatedBy(),orderStatus.getCreatedTime(),orderStatus.getOrderStatusMesssage());
		return new EntitlementUtils().createResponseEntity(201, "created"); 

	}
	 

	

}
