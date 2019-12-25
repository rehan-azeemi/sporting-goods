package com.sporting.goods.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sporting.goods.model.*;

public interface AtomicSkiRepository extends CrudRepository<AtomicSki,Long>{
	@Query(value = "SELECT new com.sporting.goods.model.AtomicSkiDTO(atomicSkiId,firstName,lastName,phone,email) FROM AtomicSki " + 
			"WHERE atomicSkiId IN(SELECT new com.sporting.goods.model.AtomicSkiDTO(MAX(atomicSkiId)) FROM AtomicSki " + 
			"GROUP BY phone" + 
			")",nativeQuery = false)
	public List<AtomicSkiDTO> fetchLastRecordByCustomers();
	public AtomicSki findByAtomicSkiId(Long atomicSkiId);
	public List<AtomicSki> findAll();
	@Query("select distinct email from AtomicSki")
	public List<String> findAllEmails();
}
