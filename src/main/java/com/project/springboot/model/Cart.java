package com.project.springboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedStoredProcedureQueries;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
		name = "checkIfCartItemExists", 
		procedureName = "checkIfCartItemExists", 
		parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_id", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_userId", type = String.class)
		}
		/*
		 *  CREATE DEFINER=`root`@`localhost` PROCEDURE `checkIfCartItemExists`(IN db_id TEXT, IN db_userId TEXT)
			BEGIN
				select * from cart where itemid = db_id and user_id = db_userId;
			END
		 */
	),
	@NamedStoredProcedureQuery(
			name = "insertCartItem", 
			procedureName = "insertCartItem", 
			parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_id", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_itemid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_userId", type = String.class)
			}
			/*
			 *  CREATE DEFINER=`root`@`localhost` PROCEDURE `insertCartItem`( IN db_id TEXT, IN db_itemid TEXT, IN db_userId TEXT)
				BEGIN
					insert into cart(id, itemid, user_id) VALUES (db_id, db_itemid, db_userId);
				END
			 */
	),
	@NamedStoredProcedureQuery(
			name = "deleteCartItem", 
			procedureName = "deleteCartItem", 
			parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_id", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_userId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_itemId", type = String.class)
				
			}
			/*
			 *  CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteCartItem`(IN db_id TEXT, IN db_userId TEXT, IN db_itemId TEXT)
				BEGIN
					delete from cart where user_id = db_userId and itemid = db_itemId AND id = db_id;
				END
			 */
	),
	@NamedStoredProcedureQuery(
			name = "getAllCartItemsByUserId", 
			procedureName = "getAllCartItemsByUserId", 
			parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_userId", type = String.class)
				
			}
			/*
			 *  CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllCartItemsByUserId`(IN db_userId TEXT)
				BEGIN
					select * from cart where user_id = db_userId;
				END
			 */
	),
})




@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder






@Entity
@Table(name="cart")
public class Cart {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  
	private int id;
	@ManyToOne
	@JoinColumn(name="user_id", nullable = false, referencedColumnName = "id")
	private User user;
	
	private String itemid;
	
}
