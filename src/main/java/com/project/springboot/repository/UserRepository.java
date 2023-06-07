package com.project.springboot.repository;

import java.util.List;
import java.util.Optional;

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
	
}
