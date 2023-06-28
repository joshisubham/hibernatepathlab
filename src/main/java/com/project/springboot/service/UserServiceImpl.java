package com.project.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.springboot.model.RolePermission;
import com.project.springboot.model.User;
import com.project.springboot.repository.UserRepository;

import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	UserRepository userRepo;
	
	public List<User> getAllUsers() {
		return userRepo.getAllUsers();
	}
	@Transactional // (value = "transactionManager", propagation = Propagation.REQUIRED)
	public List<User> registerUserReturningUserList(User user) {
		return userRepo.registerUserReturningUserList(user);
	}
	
	@Transactional // (value = "transactionManager", propagation = Propagation.REQUIRED)
	public int registerUser(User user, int roleId) {
		return userRepo.registerUser(user, roleId);
	}
	@Transactional
	public List<User> checkIfUsernameExists(String email) {
		return userRepo.checkIfUsernameExists(email);
	}
	public List<User> validateLogin(String email, String password) {
		return userRepo.validateLogin(email, password);
		
	}
	public List<User> getUserById(String userId) {
		return userRepo.getUserById(userId);
		
	}
	
	@Transactional
	public List<User> updateUser(User user) {
		return userRepo.updateUser(user);
		
	}
	public List<RolePermission> getUserPermission(String userId) {
		return userRepo.getUserPermission(userId);
	}
	
}
