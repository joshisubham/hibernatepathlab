package com.project.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.springboot.model.Cart;
import com.project.springboot.model.Item;
import com.project.springboot.service.ICartItemService;
import com.project.springboot.service.IItemService;

@RestController
@RequestMapping("/pathLab")
public class CartController {
	@Autowired
	ICartItemService cartService;

    @GetMapping(path = "/checkIfCartItemExists", produces= { "application/json" })
	public List<Cart> checkIfCartItemExists(@RequestBody String db_id, String db_userId) {
		return cartService.checkIfCartItemExists(db_id, db_userId);
	}
	
    @GetMapping(path = "/insertCartItem", produces= { "application/json" })
	public List<Cart> insertCartItem(@RequestBody String db_id, String db_itemid, String db_userId) {//Cart cart){//
		// TODO Auto-generated method stub
//    	return cartService.insertCartItem(String.valueOf(cart.getId()), cart.getItemid(), String.valueOf(cart.getUser().getId()));
		return cartService.insertCartItem("0", "4", "1");
	}

    @GetMapping(path = "/deleteCartItem", produces= { "application/json" })
	public List<Cart> deleteCartItem(@RequestBody String db_id, String db_itemid, String db_userId) {
		// TODO Auto-generated method stub
		return cartService.deleteCartItem("3", "4", "1");
	}

    @GetMapping(path = "/getAllCartItemsByUserId", produces= { "application/json" })
	public List<Cart> getAllCartItemsByUserId(@RequestBody String db_userId){
		// TODO Auto-generated method stub
		return cartService.getAllCartItemsByUserId("1");
	}
}
