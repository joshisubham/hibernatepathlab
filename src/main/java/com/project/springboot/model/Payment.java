package com.project.springboot.model;
import java.util.Date;

import org.hibernate.annotations.Proxy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQueries;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NamedNativeQueries({
	@NamedNativeQuery(
			name = "insertAllOrdersToPayment", 
			query = "call insertAllOrdersToPayment();"			
			
			/*
			 * CREATE DEFINER=`root`@`localhost` PROCEDURE `insertPayment`(IN db_id TEXT, IN db_payment_date TEXT, IN db_order_id TEXT, IN db_user_id TEXT)
				BEGIN
					insert into payment(id, payment_date, order_id, user_id) values (db_id, current_timestamp(), db_order_id, db_user_id);
				END
			 */
		),
	@NamedNativeQuery(
		name = "insertPayment", 
		query = "call insertPayment(:db_id, :db_payment_date, :db_order_id, :db_user_id);", 
		resultClass =  Payment.class 
		
		
		/*
		 * CREATE DEFINER=`root`@`localhost` PROCEDURE `insertPayment`(IN db_id TEXT, IN db_payment_date TEXT, IN db_order_id TEXT, IN db_user_id TEXT)
			BEGIN
				insert into payment(id, payment_date, order_id, user_id) values (db_id, current_timestamp(), db_order_id, db_user_id);
			END
		 */
	),
	@NamedNativeQuery(
			name = "getAllPaymentByUserId", 
			query = "call getAllPaymentByUserId(:db_user_id);", 
			resultClass =  Payment.class 
			/*
			 * CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllPaymentByUserId`(IN db_user_id TEXT)
				BEGIN
					select * from payment where user_id = db_user_id;
				END
			 */
		),
})


@Entity
@Table(name = "payment")
@Proxy(lazy = false)
public class Payment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
	private User user;
	
	@Column(name = "payment_date")
	private Date paymentDate;
	
	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false, referencedColumnName = "id")
	private Orders order;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", user=" + user + ", paymentDate=" + paymentDate + ", order=" + order + "]";
	}
	
	
	
}
