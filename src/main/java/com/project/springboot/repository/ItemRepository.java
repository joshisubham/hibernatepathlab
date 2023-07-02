package com.project.springboot.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.springboot.model.Item;
import com.project.springboot.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedStoredProcedureQueries;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.StoredProcedureQuery;

@Repository
public class ItemRepository {
	

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	public List<Item> getAllPathItems() {
		em.clear();
		return em.createNamedStoredProcedureQuery("getAllPathItems").getResultList();	
	}
	
	public List<Item> checkIfItemExists(String db_name) {
		
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("checkIfItemExists");
		query.setParameter("db_name", "Thyroid"); //procedure param name, its value 
		//System.out.println(query.getResultList().get(0).toString());
		return query.getResultList();
	}
	public List<Item> archiveItem(String db_id) {
		
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("archiveItem");
		query.setParameter("db_id", "1"); //procedure param name, its value 
		return query.getResultList();
	}
	public List<Item> getItemById(String db_id) {
		
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("getItemById");
		query.setParameter("db_id", "1"); //procedure param name, its value 
		return query.getResultList();
	}
	public List<Item> insertItem(Item item) {
		
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("insertItem");
		query.setParameter("db_id", "0"); //procedure param name, its value 
		query.setParameter("db_afterfood", item.isAfterfood());
		query.setParameter("db_beforefood", item.isBeforefood());
		query.setParameter("db_name", item.getName());
		query.setParameter("db_normal", item.isNormal());
		query.setParameter("db_offer", item.getOffer());
		query.setParameter("db_price", item.getPrice());
		return query.getResultList();
	}
	public List<Item> updateItem(Item item) {
		
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("updateItem");
		query.setParameter("db_id", item.getId()); //procedure param name, its value 
		query.setParameter("db_afterfood", item.isAfterfood());
		query.setParameter("db_beforefood", item.isBeforefood());
		query.setParameter("db_name", item.getName());
		query.setParameter("db_normal", item.isNormal());
		query.setParameter("db_offer", item.getOffer());
		query.setParameter("db_price", item.getPrice());
		return query.getResultList();
	}
	
	
	
	
	
	
	
	
}
