package com.example.demo.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrder {
	
	
	@Id
	private String placeOrderId;
	
	@Column
	private LocalDateTime orderTime;
	
	@Column
	private LocalDateTime estimatedDeliveryTime;
	
	@Column
	private String grandtotal;
	
	@OneToOne
	@JoinColumn(name="cart_id")
	@NotNull(message = "cartItems can not be null in place order object")
	private CartItems cartId;
	
	@OneToOne
	@JoinColumn(name="customer_id")
	@NotNull(message = "customer object can not be null in place order object")
	private Customer customerId; 
	
	@OneToOne
	@JoinColumn(name = "resturant_id")
	@NotNull(message = "resturant object can not be null in place order object")
	private Resturant resturant;
	
	@OneToOne
	@JoinColumn(name = "apply_coupn_id")
	private OffersAndCoupns offersAndCoupns;
	

}
