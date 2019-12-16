package com.sporting.goods.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AtomicSkiController {
	
	@GetMapping("/rentout")
	public ModelAndView getIndex() {
		return new ModelAndView("atomicski");
	}
}
