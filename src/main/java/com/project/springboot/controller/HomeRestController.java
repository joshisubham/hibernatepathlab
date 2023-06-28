package com.project.springboot.controller;

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
@CrossOrigin(origins = "http://localhost:4200")
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
	@PostMapping(path = "/validateLogin", produces = { "application/json" }, consumes = { "application/json" })
	public List<User> validateLogin( HttpServletRequest request, @RequestBody User user) {
		List<User> users = null;
//		users = userService.validateLogin(user.getEmail(), user.getPassword());
//		users.forEach((x)->{System.out.println(x);});
//		if(!users.isEmpty()) {
			HttpSession session = request.getSession(false);
			if(session == null) {
				session = request.getSession(true);
				users = userService.validateLogin(user.getEmail(), user.getPassword());
				if(!users.isEmpty()) {
					session.setAttribute("loggedUser", users.get(0));
					session.setAttribute("loggedInUserId", users.get(0).getId());
					session.setAttribute("sessionId", session.getId());
				}
			} else {

				User loggedUser = (User)session.getAttribute("loggedUser");
				users.add(loggedUser);
//				users = 
			}
//			List<RolePermission> userPermissions = userService.getUserPermission(Integer.valueOf(users.get(0).getId()).toString());
//			LinkedHashMap<String, Boolean> permissionMap = new LinkedHashMap<String, Boolean>();
//			for(int i = 0; i < userPermissions.size(); i++) {
//				RolePermission rp = userPermissions.get(i);
//				String moduleName = rp.getAppmodule().getName();
//				Boolean viewPermission = rp.isViewpermission();
//				permissionMap.put(moduleName, viewPermission);
//			}
//			permissionMap.forEach((x, y)->{System.out.println(x+">>"+y);});
//			session.setAttribute("userPermissions", userPermissions);
//		}
		
		
		
		return users;
		
	}
	@GetMapping(path = "/getUserById", produces = { "application/json" })
	public List<User> getUserById(@RequestBody User user) {
		List<User> users = userService.getUserById(Integer.valueOf(user.getId()).toString());
		users.forEach((x)->{System.out.println(x);});
		return users;
		
	}
	@PostMapping(path = "/getUserPermission", produces = { "application/json" }, consumes = {"application/json"})	
	public List<RolePermission> getUserPermission(HttpServletRequest request, @RequestBody User user) {
//		Integer userId = 0;
//		HttpSession session = request.getSession(false);
//		if(session != null) {
//			userId = (Integer)session.getAttribute("loggedInUserId");
//			System.out.println("userId>>"+userId);
//		}
//		return userService.getUserPermission(Integer.valueOf(userId).toString());
		return userService.getUserPermission(Integer.valueOf(user.getId()).toString());
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
	@GetMapping(path = "/logout", produces = { "application/json" })	
	public void logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.removeAttribute("loggedUser");
			session.removeAttribute("loggedInUserId");
			session.removeAttribute("sessionId");
			session.invalidate();			
		} 
		
	}
	
  
    
}
