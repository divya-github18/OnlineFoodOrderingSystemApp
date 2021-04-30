package com.example.demo.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OffersAndCoupns {
	
	@Id
	private String offersAndCoupnsId; 
	
	@Column
	private long coupnNumber; 
	
	@Column
	private long discount;
	
	@Column
	private LocalDateTime startDate;
	
	@Column
	private LocalDateTime expirayDate;
	 

}
