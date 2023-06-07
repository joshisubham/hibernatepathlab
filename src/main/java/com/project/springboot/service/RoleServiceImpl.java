package com.project.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.springboot.model.Role;
import com.project.springboot.repository.RoleRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

	@Autowired
    RoleRepository roleRepo;
	
	public List<Role> findAll() {
		List<Role> roles = roleRepo.findAll();
		return roles;
	}
	
	public List<Role> getRoleByIdNativeQuery(int roleId) {
		List<Role> roles = roleRepo.getRoleByIdNativeQuery(roleId);
		return roles;
	}
}
