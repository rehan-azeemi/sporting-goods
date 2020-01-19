package com.sporting.goods.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.sporting.goods.model.AtomicSki;
import com.sporting.goods.model.State;
import com.sporting.goods.repositories.AtomicSkiRepository;
import com.sporting.goods.repositories.StateRepository;
import com.sporting.goods.services.AtomicSkiServices;

@RestController
public class AtomicSkiRentoutHistoryController {
	
	@Autowired
	private AtomicSkiServices atomicSkiServices;
	
	@Autowired
	private StateRepository stateRepository;
	
	@GetMapping("/rentouthistorydata/{id}")
	public String getRentoutHistory(@PathVariable Long id) {
		Gson gson = new Gson();
		String data = "{\"data\":"+gson.toJson(atomicSkiServices.findLogsById(id))+"}";
		return data;
	}
	
	@GetMapping("/getStateName/{id}")
	public State getStateName(@PathVariable Long id) {
		return stateRepository.findByStateId(id);
	}
	
}
