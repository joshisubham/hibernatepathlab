package com.project.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.springboot.model.Item;
import com.project.springboot.repository.ItemRepository;

import jakarta.transaction.Transactional;

@Service
public class IItemServiceImpl implements IItemService {

	@Autowired
	ItemRepository itemRepo;
	
	@Override
	public List<Item> checkIfItemExists(String db_name) {
		return itemRepo.checkIfItemExists(db_name);
	}

	@Override
	public List<Item> archiveItem(String db_id) {
		// TODO Auto-generated method stub
		return  itemRepo.archiveItem(db_id);
	}

	@Override
	public List<Item> getItemById(String db_id) {
		// TODO Auto-generated method stub
		return itemRepo.getItemById(db_id);
	}

	@Override
	@Transactional
	public List<Item> insertItem(Item item) {
		// TODO Auto-generated method stub
		return itemRepo.insertItem(item);
	}

	@Override
	public List<Item> updateItem(Item item) {
		// TODO Auto-generated method stub
		return itemRepo.updateItem(item);
	}

}
