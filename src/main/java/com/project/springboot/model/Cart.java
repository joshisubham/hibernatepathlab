package com.project.springboot.model;



import org.hibernate.annotations.Proxy;

import jakarta.persistence.Column;
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
		resultClasses = {Cart.class},
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
					resultClasses = {Cart.class},
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
					resultClasses = {Cart.class},
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
					resultClasses = {Cart.class},
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


@Entity
@Table(name="cart")
@Proxy(lazy = false)
public class Cart {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  
	private int id;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable = false, referencedColumnName = "id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="itemid", nullable = false, referencedColumnName = "id")
	private Item item;

	@Column(columnDefinition = "TINYINT(1)", nullable = false)
	private boolean archive;
	
	public boolean isArchive() {
		return archive;
	}

	public void setArchive(boolean archive) {
		this.archive = archive;
	}

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

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", user=" + user + ", item=" + item + ", archive=" + archive + "]";
	}

	
	
	
	
	
}
