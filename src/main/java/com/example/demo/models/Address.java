package com.example.demo.models;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Address class
 * @author dboyapalli
 * Annoatations Using Lombook 
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Address {

	@Id
	private UUID addressId;
	
	@NotBlank(message = "street can not be blank") 
	@Column
	private String street;
	
	@NotBlank(message = "city can not be blank")
	@Column
	private String city;
	
	@NotBlank(message = "state can't be blank")
	@Column
	private String state;
	
	@NotBlank(message = "country can't be blank")
	@Column
	private String country;
	
	@NotNull(message = "zip code can not be null")
	@Column
	@Pattern(regexp = "^[0-9]{6}",message = "zip code must be 6 digits only")
	private String zipCode;
	
	@Column
	private double longitude;
	@Column
	private double latitude;
}
