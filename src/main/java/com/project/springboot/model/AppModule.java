package com.project.springboot.model;
import org.hibernate.annotations.Proxy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="appmodule")
@Proxy(lazy=false)
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public boolean isArchive() {
		return archive;
	}
	public void setArchive(boolean archive) {
		this.archive = archive;
	}
	@Override
	public String toString() {
		return "AppModule [id=" + id + ", name=" + name + ", viewpermission=" + viewpermission + ", addpermission="
				+ addpermission + ", editpermission=" + editpermission + ", deletepermission=" + deletepermission
				+ ", archive=" + archive + "]";
	}

	
	
}
