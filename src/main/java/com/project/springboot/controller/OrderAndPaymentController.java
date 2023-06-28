package com.project.springboot.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.springboot.model.Item;
import com.project.springboot.model.Orders;
import com.project.springboot.model.Payment;
import com.project.springboot.model.User;
import com.project.springboot.service.IItemService;
import com.project.springboot.service.IOrderService;
import com.project.springboot.service.IPaymentService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/pathLab")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderAndPaymentController {
	
	@Autowired
	IOrderService orderService;
	
	@Autowired
	IPaymentService paymentService;

	@PostMapping(path = "/insertMultipleOrder", produces= { "application/json" })
	public int insertMultipleOrder(@RequestBody User user) {//)String db_id, String db_cancel, String db_dateofcollection, String db_ordertime, String  db_cart_id){
		orderService.insertMultipleOrder(user.getId());
		return 1;
	}
    @GetMapping(path = "/insertOrder", produces= { "application/json" })
	public List<Orders> insertOrder(@RequestBody String db_id) {//)String db_id, String db_cancel, String db_dateofcollection, String db_ordertime, String  db_cart_id){
		return orderService.insertOrder("0", "0", "1327282500000", "1687282449317", "1");
	} //1687282449317  //1687282500000
    
    @GetMapping(path = "/deleteOrder", produces= { "application/json" })
   	public List<Orders> deleteOrder(@RequestBody String db_id){
   		return orderService.deleteOrder(db_id);
   	} 
    @PostMapping(path = "/getAllOrdersByUserId", produces= { "application/json" })
   	public List<Orders> getAllOrdersByUserId(@RequestBody String db_id){
   		return orderService.getAllOrdersByUserId("1");
   	} 
    
    
    @GetMapping(path = "/insertPayment", produces= { "application/json" })
   	public void insertPayment() {
    	paymentService.insertAllOrdersToPayment();
//   		return paymentService.insertPayment("0", "1321182500000", "1", "1");
   	} 
    @PostMapping(path = "/getAllPaymentByUserId", produces= { "application/json" })
   	public List<Payment> getAllPaymentByUserId(HttpServletRequest request, @RequestBody String db_id){
//    	HttpSession session = request.getSession(false);
//    	int userId;
//		if(session == null) {
//			return null;
//		} else {
//			userId = (Integer)session.getAttribute("loggedInUserId");
//		}
		
   		return paymentService.getAllPaymentByUserId(Integer.valueOf(1).toString()); //"1");
   	} 
}
