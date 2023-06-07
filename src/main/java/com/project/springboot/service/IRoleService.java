package com.project.springboot.service;

import java.util.List;

import com.project.springboot.model.Role;

public interface IRoleService{

	public List<Role> findAll();
	
	public List<Role> getRoleByIdNativeQuery(int roleId);
}
