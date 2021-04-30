package com.example.demo.models;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

public class MenuList extends AuditFields {

	@Id
	@Column(name="menu_list_id")
	private UUID menuListId;
	
	
	@NotBlank(message = "resturant code can not be blank")
	@Column(name="resturant_code")
	private String resturantCode;


	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn
	@NotNull(message = "clasification can not be null")
	private  List<Clasification> clasification; 
}
