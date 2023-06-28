package com.project.springboot.model;
import java.io.Serializable;

import org.hibernate.annotations.Proxy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedStoredProcedureQueries;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name="role_permission")
@Proxy(lazy=false)


public class RolePermission implements Serializable{
	
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  
	private int id;
	
	@ManyToOne 
	@JoinColumn(nullable= false, name="role_id", referencedColumnName = "id")
//	@Column(nullable= false, name="role_id", columnDefinition = "INT")
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public AppModule getAppmodule() {
		return appmodule;
	}

	public void setAppmodule(AppModule appmodule) {
		this.appmodule = appmodule;
	}

	public boolean isViewpermission() {
		return viewpermission;
	}

	public void setViewpermission(boolean viewpermission) {
		this.viewpermission = viewpermission;
	}

	public boolean isAddpermission() {
		return addpermission;
	}

	public void setAddpermission(boolean addpermission) {
		this.addpermission = addpermission;
	}

	public boolean isEditpermission() {
		return editpermission;
	}

	public void setEditpermission(boolean editpermission) {
		this.editpermission = editpermission;
	}

	public boolean isDeletepermission() {
		return deletepermission;
	}

	public void setDeletepermission(boolean deletepermission) {
		this.deletepermission = deletepermission;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "RolePermission [id=" + id + ", role=" + role + ", appmodule=" + appmodule + ", viewpermission="
				+ viewpermission + ", addpermission=" + addpermission + ", editpermission=" + editpermission
				+ ", deletepermission=" + deletepermission + "]";
	}

	
	
	
	
}
