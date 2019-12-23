package com.sporting.goods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
		ModelAndView view =  new ModelAndView("employee");
		view.addObject("employee", new Employee());
		view.addObject("employees", employeeService.getAllEmployee(1));
		return view;
		
	}
	
	@GetMapping("/employee/delete/{userId}")
	public String deleteEmployee(@PathVariable Long userId) {
		employeeService.deleteEmployee(userId);
		return "redirect:/employee";
		
	}
	
	@GetMapping("/employee/edit/{userId}")
	public String editEmployee(Model model,@PathVariable Long userId) {
		
		model.addAttribute("employee", employeeService.getEmployee(userId));
		model.addAttribute("employees", employeeService.getAllEmployee(1));
		
		return "employee";
		
	}
	
	
	@PostMapping("/employee/save")
	public String saveEmployee(@ModelAttribute("employee") Employee e) {
		employeeService.saveEmployeeService(e);
		return "redirect:/employee";
	}
}
