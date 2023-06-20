package com.project.springboot.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.springboot.model.Cart;
import com.project.springboot.model.Item;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedStoredProcedureQueries;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.StoredProcedureQuery;

@Repository
public class CartItemRepository {
	
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	public List<Cart> checkIfCartItemExists(String db_id, String db_userId) {
		
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("checkIfCartItemExists");
		query.setParameter("db_id", "0"); //procedure param name, its value 
		query.setParameter("db_userId", "thyroid");
		return query.getResultList();
	}
	public List<Cart> insertCartItem(String db_id, String db_itemid, String db_userId) {
		
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("insertCartItem");
		query.setParameter("db_id", db_id); //procedure param name, its value 
		query.setParameter("db_itemid", db_itemid);
		query.setParameter("db_userId", db_userId);
		return query.getResultList();
	}
	public List<Cart> deleteCartItem(String db_id, String db_itemId, String db_userId) {
		
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("deleteCartItem");
		query.setParameter("db_id", db_id); //procedure param name, its value 
		query.setParameter("db_itemId", db_itemId);
		query.setParameter("db_userId", db_userId);
		return query.getResultList();
	}
	public List<Cart> getAllCartItemsByUserId(String db_userId) {
		
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("getAllCartItemsByUserId");
		query.setParameter("db_userId", db_userId);
		return query.getResultList();
	}

}
