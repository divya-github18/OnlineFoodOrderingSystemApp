package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Delivery {
	
	@Id
	private String deliveryGuyId;
	
	@Column
	@NotBlank(message = "delivery person name can not be blank")
	private String name;
	
	@Column
	@NotNull(message = "delivery person mobile nuber can not be null")
	private long mobilenumber;
	
	@OneToOne
	@NotNull(message = "place order object can not be null in delivery object")
	@JoinColumn(name="place_order_id")
	private PlaceOrder placeOrder;
	
 
	@OneToOne
	@NotNull(message = "customer address object can not be null in delivery object")
	@JoinColumn(name="customer_address")
	private Customer customer;
	
	

}
