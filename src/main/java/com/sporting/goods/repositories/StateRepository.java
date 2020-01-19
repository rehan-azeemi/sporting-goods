package com.sporting.goods.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sporting.goods.model.State;

public interface StateRepository extends CrudRepository<State, Long>{
	public List<State> findAll();
	public State findByStateId(Long stateId);
}
