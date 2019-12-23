package com.sporting.goods.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sporting.goods.model.State;
import com.sporting.goods.repositories.StateRepository;

@Service
public class StateServices {
	@Autowired
	private StateRepository stateRepository;
	
	public List<State> getAllStates(){
		return stateRepository.findAll();
	}
}
