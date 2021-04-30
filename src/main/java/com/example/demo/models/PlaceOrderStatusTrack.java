package com.example.demo.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderStatusTrack {
	@Id
	private String placeOrderStatusTrackId;
	
	@OneToOne(cascade = {CascadeType.ALL}, optional =false)
	@JoinColumn(name = "place_order_Id")
	private PlaceOrder placeOrder;
	
	@OneToOne(cascade = {CascadeType.ALL}, optional =false)
	@JoinColumn(name = "order_status_id")
	private OrderStatus orderStatus;
	
	@Column
	private LocalDateTime statusTime; 

}
