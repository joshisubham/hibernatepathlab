package com.project.springboot.model;

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


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Entity
@Table(name = "item")
public class Item {
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
	
}
