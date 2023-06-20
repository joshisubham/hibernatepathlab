package com.project.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
	
	//Registering User
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
	public List<User> checkIfUsernameExists(String email) {
		
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("checkIfUsernameExists");
		query.setParameter("db_email", "subhamjoshi466@gmail.com"); //procedure param name, its value 
		return query.getResultList();
	}
	public List<User> validateLogin(String email, String password) {
		
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("validateLogin");
		query.setParameter("db_email", "subhamjoshi466@gmail.com"); //procedure param name, its value 
		query.setParameter("db_password", "subh");
		return query.getResultList();
	}
	public List<User> getUserById(String userId) {
		
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("getUserById");
		query.setParameter("db_id", userId); //procedure param name, its value 
		return query.getResultList();
	}
	
	public int updateUser(User user, int roleId) {
	
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("updateUser");
		
		query.setParameter("address", user.getAddress()); //procedure param name, its value 
		query.setParameter("age", user.getAge());
		query.setParameter("email", user.getEmail());
		query.setParameter("gender", user.getGender());
		query.setParameter("user_name", user.getName());
		query.setParameter("user_password", user.getPassword());
		query.setParameter("phone", user.getPhone());
		query.setParameter("role_id", roleId);
		query.setParameter("archive", user.isArchive());
		query.setParameter("db_id", "25"); //procedure param name, its value 
		
		Optional<Object> inserted = Optional.ofNullable(query.getOutputParameterValue("result"));
		
		return (Integer) inserted.orElse(0);
	}
	
	
	public List<Object> getUserPermission(String userId) {
		
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("getUserPermission");
		query.setParameter("db_user_id", userId ); 
		return query.getResultList();
	}
	
	
}
