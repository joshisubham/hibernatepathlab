package com.project.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.project.springboot.model.Item;
import com.project.springboot.model.Payment;

import jakarta.persistence.NamedStoredProcedureQueries;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	
//	@Modifying
	@Query(name = "insertPayment", nativeQuery = true)
	public List<Payment> insertPayment(@Param("db_id") String db_id, @Param("db_payment_date") java.sql.Date db_payment_date, 
			@Param("db_order_id") String db_order_id, @Param("db_user_id") String db_user_id);

	@Query(name = "getAllPaymentByUserId", nativeQuery = true)
	public List<Payment> getAllPaymentByUserId(@Param("db_user_id") String db_user_id);

}
