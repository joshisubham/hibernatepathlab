package com.project.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.project.springboot.model.Item;
import com.project.springboot.model.Orders;

import jakarta.transaction.Transactional;



public interface OrderRepository extends JpaRepository<Orders, Integer> {
	
	@Transactional
	@Modifying
	@Query(name = "insertMultipleOrder", nativeQuery = true )
//	@Query(value = "call insertOrder(:db_id, :db_cancel, :db_dateofcollection, :db_ordertime, :db_cart_id);", nativeQuery = true)
	public void insertMultipleOrder(@Param("db_id") int db_id);
	
	
	@Modifying
	@Query(name = "insertOrder", nativeQuery = true )
//	@Query(value = "call insertOrder(:db_id, :db_cancel, :db_dateofcollection, :db_ordertime, :db_cart_id);", nativeQuery = true)
	public List<Orders> insertOrder(	@Param("db_id") String db_id, 		@Param("db_cancel") String db_cancel, 
										@Param("db_dateofcollection") java.sql.Date db_dateofcollection, 		@Param("db_ordertime") java.sql.Date db_ordertime,
										@Param("db_cart_id") String  db_cart_id
									);

	@Query(name = "deleteOrder", nativeQuery = true )
//	@Query(value = "call deleteOrder(:db_id);", nativeQuery = true)
	public List<Orders> deleteOrder(@Param("db_id") String db_id);
	
	@Query(name = "getAllOrdersByUserId", nativeQuery = true)
//	@Query(value = "call getAllOrdersByUserId(:db_user_id);", nativeQuery = true)
	public List<Orders> getAllOrdersByUserId(@Param("db_user_id") String db_user_id);
	
	@Transactional
	@Modifying
	@Query(name = "cancelOrder", nativeQuery = true)
	public void cancelOrder(@Param("db_id") String db_id);
	
	
}
	