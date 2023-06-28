package com.project.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.springboot.model.Item;

import jakarta.persistence.StoredProcedureQuery;

@Service
public interface IItemService {
	public List<Item> getAllPathItems();
	public List<Item> checkIfItemExists(String db_name);
	public List<Item> archiveItem(String db_id);
	public List<Item> getItemById(String db_id);
	public List<Item> insertItem(Item item);
	public List<Item> updateItem(Item item);
}
