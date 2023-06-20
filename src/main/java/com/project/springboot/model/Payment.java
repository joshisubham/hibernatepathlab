package com.project.springboot.model;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedStoredProcedureQueries;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
		name = "insertPayment", 
		procedureName = "insertPayment", 
		parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_id", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_payment_date", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_order_id", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_user_id", type = String.class)
		}
		/*
		 * CREATE DEFINER=`root`@`localhost` PROCEDURE `insertPayment`(IN db_id TEXT, IN db_payment_date TEXT, IN db_order_id TEXT, IN db_user_id TEXT)
			BEGIN
				insert into payment(id, payment_date, order_id, user_id) values (db_id, current_timestamp(), db_order_id, db_user_id);
			END
		 */
	),
	@NamedStoredProcedureQuery(
			name = "getAllPaymentByUserId", 
			procedureName = "getAllPaymentByUserId", 
			parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_user_id", type = String.class)
			}
			/*
			 * CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllPaymentByUserId`(IN db_user_id TEXT)
				BEGIN
					select * from payment where user_id = db_user_id;
				END
			 */
		),
})

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Entity
@Table(name = "payment")
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
}
