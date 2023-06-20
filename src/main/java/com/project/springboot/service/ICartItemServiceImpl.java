package com.project.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.springboot.model.Cart;
import com.project.springboot.repository.CartItemRepository;
@Service
public class ICartItemServiceImpl implements ICartItemService {

	@Autowired
	CartItemRepository cartRepo;
	
	@Override
	public List<Cart> checkIfCartItemExists(String db_id, String db_userId) {
		// TODO Auto-generated method stub
		return cartRepo.checkIfCartItemExists(db_id, db_userId);
	}

	@Override
	public List<Cart> insertCartItem(String db_id, String db_itemid, String db_userId) {
		// TODO Auto-generated method stub
		return cartRepo.insertCartItem(db_id, db_itemid, db_userId);
	}

	@Override
	public List<Cart> deleteCartItem(String db_id, String db_itemid, String db_userId) {
		// TODO Auto-generated method stub
		return cartRepo.deleteCartItem(db_id, db_itemid, db_userId);
	}

	@Override
	public List<Cart> getAllCartItemsByUserId(String db_userId) {
		// TODO Auto-generated method stub
		return cartRepo.getAllCartItemsByUserId(db_userId);
	}

}
