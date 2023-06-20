package com.project.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.springboot.model.Item;
import com.project.springboot.model.Orders;
import com.project.springboot.model.Payment;
import com.project.springboot.service.IItemService;
import com.project.springboot.service.IOrderService;
import com.project.springboot.service.IPaymentService;

@RestController
@RequestMapping("/pathLab")
public class OrderAndPaymentController {
	
	@Autowired
	IOrderService orderService;
	
	@Autowired
	IPaymentService paymentService;

    @GetMapping(path = "/insertOrder", produces= { "application/json" })
	public List<Orders> insertOrder(@RequestBody String db_id, String db_cancel, String db_dateofcollection, String db_ordertime, String  db_cart_id){
		return orderService.insertOrder("0", "0", "1327282500000", "1687282449317", "1");
	} //1687282449317  //1687282500000
    
    @GetMapping(path = "/deleteOrder", produces= { "application/json" })
   	public List<Orders> deleteOrder(@RequestBody String db_id){
   		return orderService.deleteOrder("3");
   	} 
    @GetMapping(path = "/getAllOrdersByUserId", produces= { "application/json" })
   	public List<Orders> getAllOrdersByUserId(@RequestBody String db_id){
   		return orderService.getAllOrdersByUserId("1");
   	} 
    
    
    @GetMapping(path = "/insertPayment", produces= { "application/json" })
   	public List<Payment> insertPayment(@RequestBody String db_id, String db_payment_date, String db_order_id, String db_user_id) {
   		return paymentService.insertPayment("0", "1321182500000", "1", "1");
   	} 
    @GetMapping(path = "/getAllPaymentByUserId", produces= { "application/json" })
   	public List<Payment> getAllPaymentByUserId(@RequestBody String db_id){
   		return paymentService.getAllPaymentByUserId("1");
   	} 
}
