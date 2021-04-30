package com.example.demo.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.models.OrderStatus;


public interface IOrderStatusRepository extends JpaRepository<OrderStatus, String> {
	@Modifying
	@Query(value= "insert into order_status(order_status_id, created_by, created_time, order_status_messsage) values(:orderStatusId,:createdBy,:createdTime,:orderStatusMesssage)",nativeQuery = true)
	void insertOrderStatusValues(String orderStatusId, String createdBy, LocalDateTime createdTime,String orderStatusMesssage);


}
