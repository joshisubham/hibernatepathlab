package com.project.springboot.service;

import java.util.List;

import com.project.springboot.model.User;

public interface IUserService {

	public List<User> getAllUsers();
	
	public int registerUser(User user, int roleId);
}
