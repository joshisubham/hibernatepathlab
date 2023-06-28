package com.project.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.springboot.model.Orders;
import com.project.springboot.repository.OrderRepository;

@Service
public class IOrderServiceImpl implements IOrderService {

	@Autowired
	OrderRepository orderRepo;
	
	@Override
	public List<Orders> insertOrder(String db_id, String db_cancel, String db_dateofcollection, String db_ordertime,String db_cart_id) {
		// TODO Auto-generated method stub
		
		return orderRepo.insertOrder(db_id, db_cancel, new java.sql.Date(Long.valueOf(db_dateofcollection)), new java.sql.Date(Long.valueOf(db_ordertime)), db_cart_id);
	}

	@Override
	public List<Orders> deleteOrder(String db_id) {
		// TODO Auto-generated method stub
		return orderRepo.deleteOrder(db_id);

	}

	@Override
	public List<Orders> getAllOrdersByUserId(String db_user_id) {
		// TODO Auto-generated method stub
		return orderRepo.getAllOrdersByUserId(db_user_id);
	}

	@Override
	public void insertMultipleOrder(int userId) {
		// TODO Auto-generated method stub
		orderRepo.insertMultipleOrder(userId);
	}

}
