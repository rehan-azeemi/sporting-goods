package com.sporting.goods.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sporting.goods.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>{

}
