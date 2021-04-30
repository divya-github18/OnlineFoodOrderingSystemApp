package com.example.demo.models;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Address class
 * @author dboyapalli
 * Using Lombook 
 */

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Resturant extends AuditFields{


	

	@Id
	@org.springframework.data.annotation.Id
	@Column(name="restaurant_id")
	private String resturantId;
	
	@Pattern(regexp = "^[a-zA-Z0-9_ ]*$",message = "Give a proper resturant name")
	@Length(min=3, max=50, message="restaurant name should be between 3-50 characters")
	@NotBlank(message="restaurant name cannot be empty")
	@Column(name="restaurant_name", unique=true)
	private String resturantName;
	
	
	@Length(min=3, max=50, message="restaurant type should be between 3-50 characters")
	@NotBlank(message="restarant type cannot be empty")
	@Column(name="restaurant_type")
	private String resturantType; 
	

	@Column(name="restaurant_code")
	private String resturantCode;
	
	@Valid
	@NotNull (message="address cannot be empty")
	@OneToOne(cascade = {CascadeType.ALL}, optional=false)
	@JoinColumn(name="address_id")
	private Address resturantAddress;
	

	
}

