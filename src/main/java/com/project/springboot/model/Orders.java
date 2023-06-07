package com.project.springboot.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="orders")
public class Orders {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  
	private int id;
	
	@ManyToOne
	@JoinColumn(name="cart_id", nullable = false, referencedColumnName = "id")
	private Cart cart;
	
	@Column(name="ordertime")
	private Date orderTime;
	
	@Column(name="dateofcollection")
	private Date dateOfCollection;
	
	@Column(columnDefinition = "TINYINT(1)")
	private boolean cancel;
}
