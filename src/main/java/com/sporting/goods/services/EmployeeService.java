package com.sporting.goods.services;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.sporting.goods.model.Employee;
import com.sporting.goods.model.Role;
import com.sporting.goods.repositories.EmployeeRepository;
import com.sporting.goods.repositories.RoleRepository;

@Service
public class EmployeeService {
	
	private EmployeeRepository employeeRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository, 
						RoleRepository roleRepository,BCryptPasswordEncoder bCryptPasswordEncoder) {
		
		this.employeeRepository = employeeRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	public Employee findUserByUsername(String username) {
		return employeeRepository.findByUsername(username);
	}
	
	public void saveEmployeeService(Employee e) {
		if(e.getUserId() != null) {
			Employee emp = employeeRepository.findByUserId(e.getUserId());
			emp.setLastModified(new Timestamp(System.currentTimeMillis()));
			emp.setName(e.getName());
			emp.setPassword(bCryptPasswordEncoder.encode(e.getPassword()));
			emp.setUsername(e.getUsername());
			employeeRepository.save(emp);
		}
		else {
			e.setActive(1);
			e.setLastModified(new Timestamp(System.currentTimeMillis()));
			e.setPassword(bCryptPasswordEncoder.encode(e.getPassword()));
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
