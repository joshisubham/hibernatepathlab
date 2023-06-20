package com.project.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.springboot.model.Item;
import com.project.springboot.service.IItemService;
import com.project.springboot.service.IModuleService;
import com.project.springboot.service.IRoleService;
import com.project.springboot.service.IUserService;

@RestController
@RequestMapping("/pathLab")
public class ItemController {
	
	
	@Autowired
	IItemService itemService;

    @GetMapping(path = "/checkIfItemExists", produces= { "application/json" })
	public List<Item> checkIfItemExists(@RequestBody String db_name) {
		return itemService.checkIfItemExists(db_name);
	}

    @GetMapping(path = "/archiveItem", produces= { "application/json" })
	public List<Item> archiveItem(String db_id) {
		// TODO Auto-generated method stub
		return  itemService.archiveItem(db_id);
	}

    @GetMapping(path = "/getItemById", produces= { "application/json" })
	public List<Item> getItemById(String db_id) {
		// TODO Auto-generated method stub
		return itemService.getItemById(db_id);
	}

    @GetMapping(path = "/insertItem", produces= { "application/json" })
	public List<Item> insertItem(Item item) {
		// TODO Auto-generated method stub
		return itemService.insertItem(item);
	}

    @GetMapping(path = "/updateItem", produces= { "application/json" })
	public List<Item> updateItem(Item item) {
		// TODO Auto-generated method stub
		return itemService.updateItem(item);
	}
    
}
