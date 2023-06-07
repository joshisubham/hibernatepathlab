package com.project.springboot.model;


import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.NamedStoredProcedureQueries;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;






@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
		name = "addUser", 
		procedureName = "ADD_USER", 
		parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "address", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "age", type = Integer.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "email", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "gender", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "user_name", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "user_password", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "phone", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "role_id", type = Integer.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "archive", type = Boolean.class),
			@StoredProcedureParameter(mode = ParameterMode.OUT, name = "result", type = Integer.class)
		}
	) ,
	@NamedStoredProcedureQuery(
			name = "getUsers", 
			procedureName = "GET_USERS"
	)
	
	
	
})
@Entity
@Table(name="users")
public class User implements Serializable{
	
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  
	private int id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String phone;
	
	@Column(nullable = false)
	private String gender;
	
	@Column(nullable = false)
	private int age;
	
	@Column(nullable = false)
	private String address;
	
	@ManyToOne
	@JoinColumn(name="role_id", nullable = false, referencedColumnName = "id")
	private Role role;
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isArchive() {
		return archive;
	}

	public void setArchive(boolean archive) {
		this.archive = archive;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", phone=" + phone
				+ ", gender=" + gender + ", age=" + age + ", address=" + address + ", role=" + role + ", archive="
				+ archive + "]";
	}

	
	
	
}
