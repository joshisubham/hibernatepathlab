package com.project.springboot.model;
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
@Table(name="role_permission")
public class RolePermission {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  
	private int id;
	@ManyToOne
	@JoinColumn(nullable= false, name="role_id", referencedColumnName = "id")
	private Role role;
	@ManyToOne
	@JoinColumn(nullable= false, name="module_id", referencedColumnName = "id")
	private AppModule appmodule;
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private boolean viewpermission;
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private boolean addpermission;
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private boolean editpermission;
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private boolean deletepermission;
}
