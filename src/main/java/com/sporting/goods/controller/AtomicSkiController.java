package com.sporting.goods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.sporting.goods.model.AtomicSki;
import com.sporting.goods.services.AtomicSkiServices;
import com.sporting.goods.services.StateServices;

@Controller
public class AtomicSkiController {
	
	@Autowired
	private AtomicSkiServices atomicSkiServices;
	
	@Autowired
	private StateServices stateService;
	
	@GetMapping("/rentout")
	public ModelAndView getIndex() {
		ModelAndView view = new ModelAndView("atomicski");
		view.addObject("atomicski", new AtomicSki());
		view.addObject("states", stateService.getAllStates());
		view.addObject("atomicskies", atomicSkiServices.getAllLastRecordByEachCustomer());
		
		return view;
	}
	
	@GetMapping("/rentouthistory")
	public ModelAndView getRentoutHistory() {
		ModelAndView view = new ModelAndView("rentouthistory");
		view.addObject("atomicskies",atomicSkiServices.getAllLastRecordByEachCustomer());
		return view;
	}
	
	@PostMapping("/atomicski/save")
	public String saveAtomicSki(@ModelAttribute("atomicski") AtomicSki atomicSki) {
		Long id = atomicSkiServices.saveAtomicSki(atomicSki);
		atomicSkiServices.generateForm(id);
		return "redirect:/rentout";
	}
	
	@GetMapping("/atomicski/fetch/{atomicSkiId}")
	public String fetchLatestRecord(Model model,@PathVariable Long atomicSkiId) {
		model.addAttribute("atomicski", atomicSkiServices.getAtomicSki(atomicSkiId));
		model.addAttribute("states", stateService.getAllStates());
		model.addAttribute("atomicskies", atomicSkiServices.getAllLastRecordByEachCustomer());
		return "atomicski";
	}
	
	@GetMapping("/atomicski/logs/{atomicSkiId}")
	public ModelAndView fetchLogsOfCustomers(@PathVariable Long atomicSkiId) {
		ModelAndView view = new ModelAndView("rentouthistoryofcustomer");
		view.addObject("atomicski", atomicSkiId);
		return view;
	}
	
	@GetMapping("/atomicski/export")
	public ModelAndView generateExcelForAllEmails() {	
//		if(!atomicSkiServices.generateExcelForEmails()) {
//			
//		}
		ModelAndView view = new ModelAndView("exportexcel");
		return view;
	}
}
