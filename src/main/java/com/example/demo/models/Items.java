package com.example.demo.models;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.eclipse.persistence.annotations.TypeConverter;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import com.thoughtworks.xstream.converters.basic.UUIDConverter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Items {
	

	@Id
	@Column(name="item_id")
	private UUID itemId;
	
	@Length(min=3, max=50, message="item name should be between 3-50 characters")
	@Column(name="item_name", nullable=false)
	private String itemName;
	
	@Column(name="item_cost", nullable=false)
	@NotNull(message = "item cost can not be null")
	private int itemCost;

	
	


}
