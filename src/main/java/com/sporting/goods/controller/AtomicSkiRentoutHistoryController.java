package com.sporting.goods.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.sporting.goods.services.AtomicSkiServices;

@RestController
public class AtomicSkiRentoutHistoryController {
	
	@Autowired
	private AtomicSkiServices atomicSkiServices;
	
	@GetMapping("/rentouthistorydata")
	public String getRentoutHistory() {
		Gson gson = new Gson();
		String data = "{\"data\":"+gson.toJson(atomicSkiServices.getAllAtomicSkies())+"}";
		return data;
	}
}
