package com.example.demo.models;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Address class
 * @author dboyapalli
 * Annoatations Using Lombook 
 */
@Getter
@Setter
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public class AuditFields {

	@Column
	private LocalDateTime createdTime;
	@Column
	private LocalDateTime updatedTime;
	@Column
	private String updatedBy; 
	@Column
	private String createdBy;

}
