package com.project.springboot.model;

import java.io.Serializable;

import org.hibernate.annotations.Proxy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
		name = "checkIfItemExists", 
		procedureName = "checkIfItemExists", 
		resultClasses = {Item.class},
		parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_name", type = String.class)
		}
		/*
		 * CREATE DEFINER=`root`@`localhost` PROCEDURE `checkIfItemExists`(IN db_name TEXT)
		BEGIN
			select * from item where item.`name`= db_name;
		END
		 */
	),
	@NamedStoredProcedureQuery(
			name = "archiveItem", 
			procedureName = "archiveItem", 
			resultClasses = {Item.class},
			parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_id", type = String.class)
			}
			/*
			 *  CREATE DEFINER=`root`@`localhost` PROCEDURE `archiveItem`(IN db_id TEXT)
				BEGIN
					update item set item.archive = 1 where item.id = db_id;
				END
			 */
		) ,
	@NamedStoredProcedureQuery(
			name = "getItemById", 
			procedureName = "getItemById", 
			resultClasses = {Item.class},
			parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_id", type = String.class)
			}
			/*
			 *  CREATE DEFINER=`root`@`localhost` PROCEDURE `getItemById`(IN db_id TEXT)
				BEGIN
					SELECT * FROM item 	WHERE id = db_id;
				END
			 */
	),
	@NamedStoredProcedureQuery(
			name = "insertItem", 
			procedureName = "insertItem", 
			resultClasses = {Item.class},
			parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_id", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_afterfood", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_beforefood", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_name", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_normal", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_offer", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_price", type = String.class)
			}
			/*
			 *  CREATE DEFINER=`root`@`localhost` PROCEDURE `insertItem`(IN db_id TEXT, IN db_afterfood TEXT, IN db_beforefood TEXT, IN db_name TEXT, IN db_normal TEXT, IN db_offer TEXT, IN db_price TEXT)
				BEGIN
					INSERT INTO `pathdb`.`item`(`id`,`afterfood`,`archive`,`beforefood`,`name`,`normal`,`offer`,`price`)
					VALUES (db_id, db_afterfood, 0, db_beforefood, db_name, db_normal, db_offer, db_price);
				    
				    SELECT * FROM item WHERE `name` =  db_name;
				END
			 */
	),
	@NamedStoredProcedureQuery(
			name = "updateItem", 
			procedureName = "updateItem", 
			resultClasses = {Item.class},
			parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_id", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_afterfood", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_beforefood", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_name", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_normal", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_offer", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "db_price", type = String.class)
			}
			/*
			 *  CREATE DEFINER=`root`@`localhost` PROCEDURE `updateItem`(IN db_id TEXT, IN db_afterfood TEXT, IN db_beforefood TEXT, IN db_name TEXT, IN db_normal TEXT, IN db_offer TEXT, IN db_price TEXT)
				BEGIN
						UPDATE item SET `afterfood`= db_afterfood,`archive`= 0,`beforefood`= db_beforefood,
				        `name` = db_name,`normal`= db_normal,`offer`= db_offer,`price`= db_price
				        WHERE  `id`= db_id;
					
				END
			 */
	),
})



@Entity
@Proxy(lazy=false)
@Table(name = "item")
public class Item implements Serializable {
	
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, unique= true)
	private String name;
	
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private boolean beforefood;
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private boolean afterfood;
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private boolean normal;
	
	@Column(nullable = false)
	private double price;
	
	@Column(nullable = false)
	private int offer;
	@Column(columnDefinition = "TINYINT(1)")
	private boolean archive;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isBeforefood() {
		return beforefood;
	}
	public void setBeforefood(boolean beforefood) {
		this.beforefood = beforefood;
	}
	public boolean isAfterfood() {
		return afterfood;
	}
	public void setAfterfood(boolean afterfood) {
		this.afterfood = afterfood;
	}
	public boolean isNormal() {
		return normal;
	}
	public void setNormal(boolean normal) {
		this.normal = normal;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getOffer() {
		return offer;
	}
	public void setOffer(int offer) {
		this.offer = offer;
	}
	public boolean isArchive() {
		return archive;
	}
	public void setArchive(boolean archive) {
		this.archive = archive;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", beforefood=" + beforefood + ", afterfood=" + afterfood
				+ ", normal=" + normal + ", price=" + price + ", offer=" + offer + ", archive=" + archive + "]";
	}
	
	
	
	
	
}
