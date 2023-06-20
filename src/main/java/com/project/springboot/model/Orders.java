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
		name = "insertOrder", 
		procedureName = "insertOrder", 
		parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_id", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_cancel", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_dateofcollection", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_ordertime", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_cart_id", type = String.class)
		}
		/*
		 * CREATE DEFINER=`root`@`localhost` PROCEDURE `insertOrder`(IN db_id TEXT, IN db_cancel TEXT, IN db_dateofcollection timestamp, IN db_ordertime timestamp, IN db_cart_id TEXT)
			BEGIN
				INSERT INTO `pathdb`.`orders`(`id`,`cancel`,`dateofcollection`,`ordertime`,`cart_id`)
					VALUES (db_id, db_cancel, db_dateofcollection, current_timestamp(), db_cart_id);
			END
		 */
	),
	@NamedStoredProcedureQuery(
			name = "deleteOrder", 
			procedureName = "deleteOrder", 
			parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_id", type = String.class)
			}
			/*
			 * CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteOrder`(IN db_id TEXT)
				BEGIN
					DELETE FROM orders where id = db_id;
				END
			 */
		),
	@NamedStoredProcedureQuery(
			name = "getAllOrdersByUserId", 
			procedureName = "getAllOrdersByUserId", 
			parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_user_id", type = String.class)
			}
			/*
			 * CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllOrdersByUserId`(IN db_user_id TEXT)
				BEGIN
					SELECT orders.* FROM orders left join cart 
						on orders.cart_id = cart.id
						where cart.user_id = db_user_id;
				END
			 */
		),
})

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Entity
@Table(name="orders")
public class Orders {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  
	private int id;
	
	@ManyToOne
	@JoinColumn(name="cart_id", nullable = false, referencedColumnName = "id")
	private Cart cart;
	
	@Column(name="ordertime")
	private Date orderTime;
	
	@Column(name="dateofcollection")
	private Date dateOfCollection;
	
	@Column(columnDefinition = "TINYINT(1)")
	private boolean cancel;
}
