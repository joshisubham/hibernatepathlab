package com.project.springboot.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.project.springboot.model.Orders;

@Service
public interface IOrderService {
	
	public void insertMultipleOrder(int userId);
	
	public List<Orders> insertOrder(String db_id, 	String db_cancel, String db_dateofcollection, String db_ordertime, String  db_cart_id);

	public List<Orders> deleteOrder(String db_id);

	public List<Orders> getAllOrdersByUserId(String db_user_id);
	
}
