package com.project.springboot.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.project.springboot.model.Item;
import com.project.springboot.model.Payment;
import com.project.springboot.repository.PaymentRepository;

import jakarta.transaction.Transactional;
@Service
public class IPaymentServiceImpl implements IPaymentService {
	
	@Autowired
	PaymentRepository paymentRepo;

	@Override
	@Transactional
//	@Scheduled(cron = "0 0 23 * *")
	@Scheduled(cron = "*/20 * * * * *") //every two sec
	public void insertAllOrdersToPayment() {
//		long millis = System.currentTimeMillis();  
//		Date d = (Date) new java.util.Date(millis);      
//		System.out.println("Run>>"+ d.toString());
		paymentRepo.insertAllOrdersToPayment();
	}
	public List<Payment> insertPayment(String db_id, String db_payment_date, String db_order_id, String db_user_id) {
		// TODO Auto-generated method stub
		
		return paymentRepo.insertPayment(db_id, new java.sql.Date(Long.valueOf(db_payment_date)), db_order_id, db_user_id);
	}

	@Override
	public List<Payment> getAllPaymentByUserId(String db_user_id) {
		// TODO Auto-generated method stub
		return paymentRepo.getAllPaymentByUserId(db_user_id);
	}

}
