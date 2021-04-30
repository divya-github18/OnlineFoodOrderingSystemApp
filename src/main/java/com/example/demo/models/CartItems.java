package com.example.demo.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
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
public class CartItems {
	
	@Id
	private String cartId;
	
	@Column
	@NotNull(message = "quantity can not be empty")
	private int quantity;
	
	@Column
	private float finalPrice;
	
	@Column
	private String discount;
	
	@OneToOne
	@JoinColumn(name="resturant_id")
	@NotNull(message = "Resturant can not be empty")
	private Resturant resturantId;
	
	@OneToOne
	@JoinColumn(name = "item_id")
	@NotNull(message = "Items can not be empty")
	private Items itemId;
 
}
