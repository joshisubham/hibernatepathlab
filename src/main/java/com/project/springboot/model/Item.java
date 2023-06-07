package com.project.springboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Entity
@Table(name = "item")
public class Item {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, unique= true)
	private String name;
	
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private boolean beforefood;
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private boolean afterfood;
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private boolean normal;
	
	@Column(nullable = false)
	private double price;
	
	@Column(nullable = false)
	private int offer;
	@Column(columnDefinition = "TINYINT(1)")
	private boolean archive;
	
}
