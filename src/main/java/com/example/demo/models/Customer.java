package com.example.demo.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Customer {
	
	@Id
	@Column(name="customer_id")
	private String customerId;
	
	@Column(name="first_name")
	@NotBlank(message = "customer first name can not be blank")
	private String firstName;
	
	@Column(name="last_name" )
	@NotBlank(message = "customer last Name can not be blank")
	private String lastName;
	
	
	@Column(name="mobile_mum") 
	@NotNull(message = "customer mobile name can not be null")
	private long mobileNum;
	

	@Column(name="mail_id")
	@Email(message = "mail should be in proper manner")
	private String mailId;
	
	@Valid
	@NotNull (message="address cannot be empty")
	@OneToOne
	@JoinColumn(name="customer_address_id")
	private Address customerAddress;

}
