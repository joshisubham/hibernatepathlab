package com.project.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.springboot.model.Cart;
import com.project.springboot.model.Item;
import com.project.springboot.service.ICartItemService;
import com.project.springboot.service.IItemService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/pathLab")
@CrossOrigin(origins = "http://localhost:4200")
public class CartController {
	@Autowired
	ICartItemService cartService;

    @PostMapping(path = "/checkIfCartItemExists", produces= { "application/json" })
	public List<Cart> checkIfCartItemExists(@RequestBody Cart cart) {
    	//@RequestBody String db_id, String db_userId
		return cartService.checkIfCartItemExists(Integer.valueOf(cart.getId()).toString(), Integer.valueOf(cart.getUser().getId()).toString());
	}
	
    @PostMapping(path = "/insertCartItem", produces= { "application/json" })
	public List<Cart> insertCartItem(HttpServletRequest request, @RequestBody Cart cart) {//Cart cart){//
		// TODO Auto-generated method stub // String db_id, String db_itemid, String db_userId
//    	return cartService.insertCartItem(String.valueOf(cart.getId()), cart.getItemid(), String.valueOf(cart.getUser().getId()));
    	HttpSession session = request.getSession(false);
		if(session == null) {
			return null;
		}
		return cartService.insertCartItem(Integer.valueOf(cart.getId()).toString(), Integer.valueOf(cart.getItem().getId()).toString(), Integer.valueOf(cart.getUser().getId()).toString());          
	}

    @PostMapping(path = "/deleteCartItem", produces= { "application/json" })
	public List<Cart> deleteCartItem(@RequestBody Cart cart) {
		// TODO Auto-generated method stub
		return cartService.deleteCartItem(Integer.valueOf(cart.getId()).toString(), Integer.valueOf(cart.getItem().getId()).toString(), Integer.valueOf(cart.getUser().getId()).toString());
	}

    @PostMapping(path = "/getAllCartItemsByUserId", produces= { "application/json" })
	public List<Cart> getAllCartItemsByUserId(@RequestBody String db_userId){
		// TODO Auto-generated method stub
		return cartService.getAllCartItemsByUserId("1");
	}
}
