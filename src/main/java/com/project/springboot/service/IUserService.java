package com.project.springboot.service;

import java.util.List;

import com.project.springboot.model.User;

public interface IUserService {

	public List<User> getAllUsers();
	
	public int registerUser(User user, int roleId);
	
	public List<User> checkIfUsernameExists(String email);
	
	public List<User> validateLogin(String email, String password);
	
	public List<User> getUserById(String userId);
	
	public List<Object> getUserPermission(String userId);
	
	public int updateUser(User user, int roleId);
	
}
