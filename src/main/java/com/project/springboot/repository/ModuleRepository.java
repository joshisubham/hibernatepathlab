package com.project.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.springboot.model.RolePermission;

@Repository
public interface ModuleRepository extends JpaRepository<RolePermission, Integer> {
	
	
}
