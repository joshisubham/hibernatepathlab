package com.project.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.springboot.repository.ModuleRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ModuleServiceImpl implements IModuleService{
	
	@Autowired
    private ModuleRepository moduleRepo;
	
	
}
