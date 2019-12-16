package com.sporting.goods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sporting.goods.model.Employee;
import com.sporting.goods.services.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employee")
	public ModelAndView getEmployee() {
		return new ModelAndView("employee").addObject("employee", new Employee());
	}
	
	@PostMapping("/employee/save")
	public String saveEmployee(@ModelAttribute("employee") Employee e) {
		employeeService.saveEmployeeService(e);
		return "redirect:/employee";
	}
}
