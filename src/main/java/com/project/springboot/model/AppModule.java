package com.project.springboot.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name="appmodule")
public class AppModule {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  
	private int id;
	private String name;
	
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private boolean viewpermission;
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private boolean addpermission;
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private boolean editpermission;
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private boolean deletepermission;
	@Column(columnDefinition = "TINYINT(1)")
	private boolean archive;

	
	
}
