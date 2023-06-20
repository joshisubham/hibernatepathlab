package com.project.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.springboot.model.Cart;
import com.project.springboot.model.Item;

import jakarta.persistence.StoredProcedureQuery;

@Service
public interface ICartItemService {

	public List<Cart> checkIfCartItemExists(String db_id, String db_userId);
	public List<Cart> insertCartItem(String db_id, String db_itemid, String db_userId);
	public List<Cart> deleteCartItem(String db_id, String db_itemid, String db_userId);
	public List<Cart> getAllCartItemsByUserId(String db_userId);
}
