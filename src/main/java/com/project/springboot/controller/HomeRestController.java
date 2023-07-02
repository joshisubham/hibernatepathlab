package com.project.springboot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.springboot.model.Role;
import com.project.springboot.model.RolePermission;
import com.project.springboot.model.User;
import com.project.springboot.repository.ModuleRepository;
import com.project.springboot.service.IModuleService;
import com.project.springboot.service.IRoleService;
import com.project.springboot.service.IUserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/pathLab")
//@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class HomeRestController {

	@Autowired
	IUserService userService;
	
	@Autowired
	IRoleService roleService;
	
	@Autowired
	IModuleService moduleService;
	
	private static String GET_ALL_ROLES_URL= "/roles";
	private static String GET_ALL= "/roles";

	
    @RequestMapping("/home")
    public String home(){
        return "loading";
    }
    
	@GetMapping(path = "/roles", produces= { "application/json" })	//NO SP: JPA DAO(using default method)
	public List<Role> allRoles() {
		List<Role> roles = roleService.findAll();
		return roles;
	}
	

	@GetMapping(path = "/getRoleById/{id}", produces = { "application/json" })	//SP: JPA DAO(using @Query annotation)
	public List<Role> getRoleById(@PathVariable int id) {
		List<Role> roles = roleService.getRoleByIdNativeQuery(id);
		return roles;
	}
	
	@GetMapping(path = "/getAllUsers", produces = { "application/json" })	//SP: USING DAO ENTITY MANAGER AND TRANSACTIONAL(getResultList())
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
  
//	@GetMapping(path = "/register")		//SP: USING DAO ENTITY MANAGER AND TRANSACTIONAL(directly)
//	public int register() {
//		User user = new User();
//		user.setAddress("Dehradun");
//		user.setAge(24);
//		user.setArchive(false);
//		user.setEmail("subhamjoshi466@gmail.com");
//		user.setGender("female");
//		user.setName("Subham");
//		user.setPassword("subh");
//		user.setPhone("99999");
//		int roleId = 1;
//		return userService.registerUser(user, roleId);
//	}
	
//	@Bean
//	public RestTemplate restTemplate(RestTemplateBuilder builder) {
//	   // Do any additional configuration here
//	   return builder.build();
//	}
	
	@PostMapping(path = "/register")		//SP: USING DAO ENTITY MANAGER AND TRANSACTIONAL(directly)
	public List<User> register(@RequestBody User user) {
		
		return userService.registerUserReturningUserList(user);
	}
	@GetMapping(path = "/checkIfUsernameExists", produces = { "application/json" })	
	public List<User> checkIfUsernameExists(@RequestBody User user) {
		//String email = "subhamjoshi466@gmail.com";
		List<User> users = userService.checkIfUsernameExists(user.getEmail());
		users.forEach((x)->{System.out.println(x);});
		return users;
	}
//	@PostMapping(path = "/validateLogin")
	@PostMapping(path = "/test1")
	public List<User> test1( @RequestBody User user, HttpServletRequest request) {
		List<User> users = null;
		String msg = "1";
		List<String> messages = (List<String>) request.getSession().getAttribute("MY_SESSION_MESSAGES");
		if (messages == null) {
			messages = new ArrayList<>();
			request.getSession().setAttribute("MY_SESSION_MESSAGES", messages);
		}
		users = userService.validateLogin(user.getEmail(), user.getPassword());
		messages.add(msg);
		request.getSession().setAttribute("MY_SESSION_MESSAGES", messages);
		return users;
		
	}
	@GetMapping(path = "/getUserById", produces = { "application/json" })
	public List<User> getUserById(@RequestBody User user) {
		List<User> users = userService.getUserById(Integer.valueOf(user.getId()).toString());
		users.forEach((x)->{System.out.println(x);});
		return users;
		
	}
	@PostMapping(path = "/getUserPermission", produces = { "application/json" })	
	public List<RolePermission> getUserPermission(HttpServletRequest request){//@RequestBody User user, HttpServletRequest request) {
		return userService.getUserPermission(Integer.valueOf(1).toString());
	}
	@PostMapping(path = "/updateUser", produces = { "application/json" })	
	public List<User> updateUser(@RequestBody User user) {
		System.out.println(user);
		return userService.updateUser(user);
		
	}
	
	@GetMapping(path = "/CartItem", produces = { "application/json" })	
	public String cartItemTest() {
		JSONArray arr = new JSONArray();
		JSONObject testCartItem = new JSONObject();
		testCartItem.put("id","1");
		testCartItem.put("itemid","1");
		testCartItem.put("user_id","1");
		arr.put(testCartItem);
		String [] arr1 = {"1", "2", "3"};
		return arr.toString();//testCartItem.toString();
		
	}
	
	@PostMapping(path = "/validateLogin")
	public List<User> validateLogin(@RequestBody User user, HttpSession session) {
		List<User> users = null;
		Object loggedInUserId = (Integer) session.getAttribute("LOGGEDINUSERID");
		List<User> loggedInUserList = (List<User>) session.getAttribute("LOGGEDINUSERLIST");
		if (loggedInUserId == null) {
			users = userService.validateLogin(user.getEmail(), user.getPassword());
			loggedInUserId = users.get(0).getId();
			session.setAttribute("LOGGEDINUSERID", loggedInUserId);
			session.setAttribute("LOGGEDINUSERLIST", users);
			System.out.println("Here>>1"+ session.getAttribute("LOGGEDINUSERID"));
			
			
		} else {
			System.out.println("Here>>2"+ session.getAttribute("LOGGEDINUSERID"));
			return loggedInUserList;
		}		
		return users;
	}
		
		/*Integer loggedInUserId = (Integer) request.getSession().getAttribute("LOGGEDINUSERID");
		List<User> loggedInUserList = (List<User>) request.getSession().getAttribute("LOGGEDINUSERLIST");
		if (loggedInUserId == null) {
			loggedInUserId = 0;
			loggedInUserList = new ArrayList<>();
			request.getSession().setAttribute("LOGGEDINUSERID", loggedInUserId);
			request.getSession().setAttribute("LOGGEDINUSERLIST", loggedInUserList);
		} 
		
		List<User> users = userService.validateLogin(user.getEmail(), user.getPassword());
		loggedInUserId = users.get(0).getId();
		request.getSession().setAttribute("LOGGEDINUSERID", loggedInUserId);
		request.getSession().setAttribute("LOGGEDINUSERLIST", users);
		return users;*/
		
		/*Integer loggedInUserId = (Integer) request.getSession().getAttribute("LOGGEDINUSERID");
		if (loggedInUserId == null) {
			loggedInUserId = 0;
			request.getSession().setAttribute("LOGGEDINUSERID", loggedInUserId);
		}
		
		List<User> users = userService.validateLogin(user.getEmail(), user.getPassword());
		loggedInUserId = users.get(0).getId();
		request.getSession().setAttribute("LOGGEDINUSERID", loggedInUserId);
		return users;*/
		/*List<Integer> loggedInUserId = (List<Integer>) request.getSession().getAttribute("LOGGEDINUSERID");
		if (loggedInUserId == null) {
			loggedInUserId = new ArrayList<>();
			request.getSession().setAttribute("LOGGEDINUSERID", loggedInUserId);
		}
		
		List<User> users = userService.validateLogin(user.getEmail(), user.getPassword());
		loggedInUserId.add(users.get(0).getId());
		request.getSession().setAttribute("LOGGEDINUSERID", loggedInUserId);
		return users;*/
		
		
		/*List<String> loggedInUserId = (List<String>) request.getSession().getAttribute("LOGGEDINUSERID");
		if (loggedInUserId == null) {
			loggedInUserId = new ArrayList<>();
			request.getSession().setAttribute("LOGGEDINUSERID", loggedInUserId);
		}
		
		List<User> users = userService.validateLogin(user.getEmail(), user.getPassword());
		String msg1 = Integer.valueOf(users.get(0).getId()).toString();
		System.out.println("loginid:"+ msg1);
		loggedInUserId.add(msg1);
		request.getSession().setAttribute("LOGGEDINUSERID", loggedInUserId);
		return users;*/
		
//	}
	
	@PostMapping(path = "/logout")	
	public void logout(HttpSession session) {
		Integer loggedInUserId = (Integer) session.getAttribute("LOGGEDINUSERID");
		
		System.out.println("Here Logout>>"+ session.getAttribute("LOGGEDINUSERID"));
		session.invalidate();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//		loggedInUserList.forEach(x->{System.out.println(">>"+ x.getEmail());});
		
	/*  List<Integer> loggedInUserId = (List<Integer>) request.getSession().getAttribute("LOGGEDINUSERID");
		loggedInUserId.forEach(x->{System.out.println(">>"+ x);});
		request.getSession().invalidate();*/
	/*	List<String> loggedInUserId = (List<String>) request.getSession().getAttribute("LOGGEDINUSERID");
		loggedInUserId.forEach(x->{System.out.println(">>"+ x);});
		request.getSession().invalidate();*/

		
//	}
	
	@GetMapping("/viewSessionData")                     // it will handle all request for /welcome
    public java.util.Map<String,Integer> start(HttpServletRequest request) {
        Integer integer =(Integer) request.getSession().getAttribute("hitCounter"); /// it will read data from database tables
        if(integer==null){
            integer=new Integer(0);
            integer++;
            request.getSession().setAttribute("hitCounter",integer);  // it will write data to tables
        }else{
            integer++;
            request.getSession().setAttribute("hitCounter",integer);  // it will write data to tables
        }
        java.util.Map<String,Integer> hitCounter=new HashMap<>();
        hitCounter.put("Hit Counter",integer);
        return hitCounter;
    }
	
  
    
}
