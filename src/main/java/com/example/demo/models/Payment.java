package com.example.demo.models;

import java.time.LocalDateTime;

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
public class Payment extends AuditFields {

	@Id
	private String paymentId;

	@Column
	@NotBlank(message = "payment mode can not be blank")
	private String paymentMode;
	
	@Column
	@NotBlank(message = "payment status can not be blank")
	private String paymentStatus;
	
	@Column
	private LocalDateTime paymentDateAndTime;
	
	@Column
	private long paymentAmount; 

	@OneToOne
	@JoinColumn(name = "place_order_id")
	@NotNull(message = "place order object can not be blank in payment object")
	private PlaceOrder placeOrder;

}
