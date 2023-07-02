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
			procedureName = "GET_USERS",
			resultClasses = { User.class }
	),
	@NamedStoredProcedureQuery(
		name = "checkIfUsernameExists",
		procedureName = "checkIfUsernameExists",
		resultClasses = { User.class },
		parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_email", type = String.class),
				
		}
			/*
			 * CREATE DEFINER=`root`@`localhost` PROCEDURE `checkIfUsernameExists`(IN db_email text)
				BEGIN
					select * from users where users.email = db_email;
				END
			 * 
			 * 
			 */
	),
	@NamedStoredProcedureQuery(
			name = "validateLogin",
			procedureName = "validateLogin",
			resultClasses = { User.class },
			parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_email", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_password", type = String.class),
					
			}
				/*
				 *  CREATE DEFINER=`root`@`localhost` PROCEDURE `validateLogin`(IN db_email TEXT, IN db_password TEXT)
					BEGIN
						select * from users where users.email = db_email and users.`password` = db_password;
					END
				 * 
				 * 
				 */
	),
	@NamedStoredProcedureQuery(
			name = "getUserById",
			procedureName = "getUserById",
			resultClasses = { User.class },
			parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_id", type = String.class)
					
			}
				/*
				 * CREATE DEFINER=`root`@`localhost` PROCEDURE `getUserById`(IN db_id TEXT)
					BEGIN
						select * from users where id = db_id;
					END
				 * 
				 * 
				 */
		),
	@NamedStoredProcedureQuery(
			name = "updateUser",
			procedureName = "updateUser",
			resultClasses = { User.class },
			parameters = {
					
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_address", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_age", type = Integer.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "email", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "gender", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "user_name", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "user_password", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "phone", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "role_id", type = Integer.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "archive", type = Boolean.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_id", type = Integer.class),
				}
			
				/*
				 * 
				IN db_address TEXT, IN db_age INT, 
				IN email VARCHAR(250), IN gender VARCHAR(250), 
				IN user_name VARCHAR(250),IN user_password VARCHAR(250), 
				IN phone VARCHAR(250), IN role_id INT, 
				IN archive BOOLEAN, IN db_id INT
				 * CREATE DEFINER=`root`@`localhost` PROCEDURE `updateUser`(IN db_id TEXT, IN db_address TEXT, IN db_age TEXT, IN db_archive TEXT, IN db_email TEXT, IN db_gender TEXT, IN db_user_name TEXT, IN db_user_password TEXT, IN db_phone TEXT, IN db_role_id TEXT, OUT result INT)
				BEGIN
					update users set users.`address`= db_address, users.`age`= db_age,users.`archive`= db_archive,
				    users.`email`= db_email,
				    users.`gender`= db_gender,users.`name`= db_user_name,users.`password`=db_user_password,
				    users.`phone`= db_phone, users.`role_id`= db_role_id where 
				    users.`id` = db_id;
					-- VALUES(id,db_address,db_age,db_archive,db_email,db_gender,db_user_name ,db_user_password,db_phone,db_role_id);
				
				
				select ifnull(id, 0) INTO result from users where id= (select last_insert_id()) and `email`= db_email;
				
				END
								 * 
				 * 
				 */
		),
		@NamedStoredProcedureQuery(
				name = "getUserPermission",
				procedureName = "getUserPermission",
				resultClasses = { RolePermission.class },
				parameters = {
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_user_id", type = String.class)
				}
				/*
				 * CREATE DEFINER=`root`@`localhost` PROCEDURE `getUserPermission`(in db_user_id text)
					BEGIN
						select role_permission.* from role_permission
							inner join users 
							on role_permission.role_id = users.role_id
							where users.id = db_user_id;
					END
				 */
		),
		@NamedStoredProcedureQuery(
				name = "register",
				procedureName = "register",
				resultClasses = {User.class},
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
				}
				/*
				 * CREATE DEFINER=`root`@`localhost` PROCEDURE `register`(IN address VARCHAR(250), IN age INT, IN email VARCHAR(250), IN gender VARCHAR(250), IN user_name VARCHAR(250),IN user_password VARCHAR(250), IN phone VARCHAR(250), IN role_id INT, IN archive BOOLEAN)
					BEGIN
					
					INSERT INTO users(`id`,`address`,`age`,`archive`,`email`,`gender`,`name`,`password`,`phone`,`role_id`)
					VALUES(id,address,age,archive,email,gender,user_name ,user_password,phone,role_id);
					
					
					select * from users where id= (select last_insert_id()) and `email`= email;
					
					END
				 */
				
		)
		
	
	
})
@Entity
@Proxy(lazy=false)
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
	
	@ManyToOne(fetch = FetchType.LAZY)
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
