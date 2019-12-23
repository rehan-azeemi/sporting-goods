package com.sporting.goods.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sporting.goods.model.Employee;
import com.sporting.goods.repositories.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public void saveEmployeeService(Employee e) {
		if(e.getUserId() != null) {
			Employee emp = employeeRepository.findByUserId(e.getUserId());
			emp.setLastModified(new Timestamp(System.currentTimeMillis()));
			emp.setName(e.getName());
			emp.setPassword(e.getPassword());
			emp.setUsername(e.getUsername());
			employeeRepository.save(emp);
		}
		else {
			e.setActive(1);
			e.setLastModified(new Timestamp(System.currentTimeMillis()));
			employeeRepository.save(e);
		}
		
	}
	
	public List<Employee> getAllEmployee(Integer active){
		return employeeRepository.findByActive(active);
	}
	
	public void deleteEmployee(Long userId) {
		Employee e = employeeRepository.findByUserId(userId);
		e.setActive(0);
		employeeRepository.save(e);
	}
	
	public Employee getEmployee(Long userId) {
		return employeeRepository.findByUserId(userId);
	}
}
