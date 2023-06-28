package com.project.springboot.service;

import java.util.List;

import com.project.springboot.model.RolePermission;
import com.project.springboot.model.User;

public interface IUserService {

	public List<User> getAllUsers();
	
	public List<User> registerUserReturningUserList(User user);
	
	public int registerUser(User user, int roleId);
	
	public List<User> checkIfUsernameExists(String email);
	
	public List<User> validateLogin(String email, String password);
	
	public List<User> getUserById(String userId);
	
	public List<RolePermission> getUserPermission(String userId);
	
	public List<User> updateUser(User user);
	
}
