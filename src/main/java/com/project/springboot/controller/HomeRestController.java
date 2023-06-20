package com.project.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.springboot.model.Role;
import com.project.springboot.model.User;
import com.project.springboot.repository.ModuleRepository;
import com.project.springboot.service.IModuleService;
import com.project.springboot.service.IRoleService;
import com.project.springboot.service.IUserService;

@RestController
@RequestMapping("/pathLab")
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
  
	@GetMapping(path = "/register")		//SP: USING DAO ENTITY MANAGER AND TRANSACTIONAL(directly)
	public int register() {
		User user = new User();
		user.setAddress("Dehradun");
		user.setAge(24);
		user.setArchive(false);
		user.setEmail("subhamjoshi466@gmail.com");
		user.setGender("female");
		user.setName("Subham");
		user.setPassword("subh");
		user.setPhone("99999");
		int roleId = 1;
		return userService.registerUser(user, roleId);
	}
	
//	@Bean
//	public RestTemplate restTemplate(RestTemplateBuilder builder) {
//	   // Do any additional configuration here
//	   return builder.build();
//	}
	@GetMapping(path = "/checkIfUsernameExists", produces = { "application/json" })	
	public List<User> checkIfUsernameExists(@RequestBody String email) {
		//String email = "subhamjoshi466@gmail.com";
		List<User> users = userService.checkIfUsernameExists(email);
		users.forEach((x)->{System.out.println(x);});
		return users;
	}
	@GetMapping(path = "/validateLogin", produces = { "application/json" })
	public List<User> validateLogin(@RequestBody String email, String password) {
		List<User> users = userService.validateLogin(email, password);
		users.forEach((x)->{System.out.println(x);});
		return users;
		
	}
	@GetMapping(path = "/getUserById", produces = { "application/json" })
	public List<User> getUserById(@RequestBody String userId) {
		List<User> users = userService.getUserById(userId);
		users.forEach((x)->{System.out.println(x);});
		return users;
		
	}
	@GetMapping(path = "/getUserPermission", produces = { "application/json" }, consumes = {"application/json"})	
	public List<Object> getUserPermission(@RequestBody String userId) {
		return userService.getUserPermission("1");
	}
	@GetMapping(path = "/updateUser", produces = { "application/json" })	
	public int updateUser(@RequestBody User user, int roleId) {
		return userService.updateUser(user, roleId);
		
	}
	
	
  
    
}
