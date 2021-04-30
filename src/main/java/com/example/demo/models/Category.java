package com.example.demo.models;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Category extends AuditFields{
	
	@Id
	@Column(name="category_id")
	private UUID categoryId;

	@Column
	@NotBlank(message = "category name can not be blank") 
	private String categoryName;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn
	@NotEmpty(message = "items can not be blank")
	private List<Items> items;
	


}
