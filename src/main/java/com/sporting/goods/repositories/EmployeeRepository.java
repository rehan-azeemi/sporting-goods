package com.sporting.goods.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sporting.goods.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>{
	public List<Employee> findByActive(Integer active);
	public Employee findByUserId(Long userId);
	public Employee findByUsername(String username);
}
