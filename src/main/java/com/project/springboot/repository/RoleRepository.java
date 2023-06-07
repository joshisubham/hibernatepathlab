package com.project.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.springboot.model.Role;


//@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
//	Commented doesn't works!!!
//	Query annotation works
//	WITH OUTPUT PARAMETER
//	@Procedure(name = "Role.getRoleById",  outputParameterName = "outParam")
//	@Procedure(name = "getRoleById")
//	public List<Role> getRoleById(@Param("IN_ROLE_ID") Integer roleId);
	
	
	@Query(value = "CALL GET_ROLE_BY_ID(:IN_ROLE_ID);", nativeQuery = true)
	List<Role> getRoleByIdNativeQuery(@Param("IN_ROLE_ID") Integer roleId);
	
}
