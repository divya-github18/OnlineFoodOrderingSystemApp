package com.example.demo.models;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clasification extends AuditFields{
	
	@Id
	@Column(name="clasification_id")
	private UUID clasificationID;
	
	@Column
	@NotBlank(message = "clasifiaction type can not be blank")
	private String clasificationType;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn
	@NotEmpty(message = "category can not be blank")
	private List<Category> category;
	
	
}
