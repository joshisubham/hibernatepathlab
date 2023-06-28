package com.project.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.project.springboot.model.RolePermission;
import com.project.springboot.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;


@Repository
public class UserRepository {

	@PersistenceContext(name = "entityManagerFactory")
	EntityManager em;
	
	
	
	public List<User> getAllUsers() {
		return em.createNamedStoredProcedureQuery("getUsers").getResultList();	
	}
	
	//Registering User using out parameter  -- Not used
	public int registerUser(User user, int roleId) {
		
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("addUser");	
		query.setParameter("address", user.getAddress()); //procedure param name, its value 
		query.setParameter("age", user.getAge());
		query.setParameter("email", user.getEmail());
		query.setParameter("gender", user.getGender());
		query.setParameter("user_name", user.getName());
		query.setParameter("user_password", user.getPassword());
		query.setParameter("phone", user.getPhone());
		query.setParameter("role_id", roleId);
		query.setParameter("archive", user.isArchive());
		
		Optional<Object> inserted = Optional.ofNullable(query.getOutputParameterValue("result"));
		
		return (Integer) inserted.orElse(0);
		
	}
	//Registering User not using out parameter
		public List<User> registerUserReturningUserList(User user) {
			
			StoredProcedureQuery query = em.createNamedStoredProcedureQuery("register");	
			query.setParameter("address", user.getAddress()); //procedure param name, its value 
			query.setParameter("age", user.getAge());
			query.setParameter("email", user.getEmail());
			query.setParameter("gender", user.getGender());
			query.setParameter("user_name", user.getName());
			query.setParameter("user_password", user.getPassword());
			query.setParameter("phone", user.getPhone());
			query.setParameter("role_id", user.getRole().getId());
			query.setParameter("archive", user.isArchive());
			
			
			
			return query.getResultList();
			
		}
	
	public List<User> checkIfUsernameExists(String email) {
		
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("checkIfUsernameExists");
		query.setParameter("db_email", "subhamjoshi466@gmail.com"); //procedure param name, its value 
		return query.getResultList();
	}
	public List<User> validateLogin(String email, String password) {
		
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("validateLogin");
		query.setParameter("db_email", email); //procedure param name, its value 
		query.setParameter("db_password", password);
//		query.setParameter("db_email", "subhamjoshi466@gmail.com"); //procedure param name, its value 
//		query.setParameter("db_password", "subh");
		return query.getResultList();
	}
	public List<User> getUserById(String userId) {
		
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("getUserById");
		query.setParameter("db_id", userId); //procedure param name, its value 
		return query.getResultList();
	}
	
	public List<User> updateUser(User user) {
	
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("updateUser");	
		//(IN address VARCHAR(250), IN age INT, IN email VARCHAR(250), IN gender VARCHAR(250), IN user_name VARCHAR(250),IN user_password VARCHAR(250), IN phone VARCHAR(250), IN role_id INT, IN archive BOOLEAN, IN db_id INT)
		query.setParameter("db_address", user.getAddress()); //procedure param name, its value 
		query.setParameter("db_age", user.getAge());
		query.setParameter("email", user.getEmail());
		query.setParameter("gender", user.getGender());
		query.setParameter("user_name", user.getName());
		query.setParameter("user_password", user.getPassword());
		query.setParameter("phone", user.getPhone());
		query.setParameter("role_id", user.getRole().getId());
		query.setParameter("archive", user.isArchive());
		query.setParameter("db_id", user.getId()); 
		
		return query.getResultList();
	}
	
	
	public List<RolePermission> getUserPermission(String userId) {
		
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("getUserPermission");
		query.setParameter("db_user_id", userId ); 
		return query.getResultList();
	}
	
	
}
