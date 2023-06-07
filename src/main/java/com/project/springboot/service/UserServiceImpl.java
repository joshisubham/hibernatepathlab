package com.project.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.springboot.model.User;
import com.project.springboot.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	UserRepository userRepo;
	
	public List<User> getAllUsers() {
		return userRepo.getAllUsers();
	}
	
	@Transactional // (value = "transactionManager", propagation = Propagation.REQUIRED)
	public int registerUser(User user, int roleId) {
		return userRepo.registerUser(user, roleId);
	}
}
