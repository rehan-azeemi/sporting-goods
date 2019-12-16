package com.sporting.goods.services;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sporting.goods.model.Employee;
import com.sporting.goods.repositories.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public void saveEmployeeService(Employee e) {
		e.setActive(1);
		e.setLastModified(new Timestamp(System.currentTimeMillis()));
		employeeRepository.save(e);
	}
}
