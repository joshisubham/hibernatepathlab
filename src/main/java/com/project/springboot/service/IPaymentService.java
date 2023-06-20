package com.project.springboot.service;

import java.util.List;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.project.springboot.model.Item;
import com.project.springboot.model.Payment;

@Service
public interface IPaymentService {
	
	public List<Payment> insertPayment(String db_id, String db_payment_date, String db_order_id, String db_user_id);


	public List<Payment> getAllPaymentByUserId(String db_user_id);

}
