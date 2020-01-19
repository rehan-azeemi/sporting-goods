package com.sporting.goods.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sporting.goods.model.*;

public interface AtomicSkiRepository extends CrudRepository<AtomicSki,Long>{
	@Query(value = "SELECT new com.sporting.goods.model.AtomicSkiDTO(atomicSkiId,firstName,lastName,phone,email) FROM AtomicSki " + 
			"WHERE atomicSkiId IN(SELECT new com.sporting.goods.model.AtomicSkiDTO(MAX(atomicSkiId)) FROM AtomicSki " + 
			"GROUP BY phone,email" + 
			")",nativeQuery = false)
	public List<AtomicSkiDTO> fetchLastRecordByCustomers();
	public AtomicSki findByAtomicSkiId(Long atomicSkiId);
	@Query(value="SELECT * FROM atomic_ski WHERE atomic_ski_id IN(SELECT MAX(atomic_ski_id) FROM atomic_ski GROUP BY phone,email)", nativeQuery=true)
	public List<AtomicSki> findAllAtomicSki();
	@Query("select distinct email from AtomicSki")
	public List<String> findAllEmails();
	@Query(value="SELECT * FROM atomic_ski WHERE phone = (SELECT phone FROM atomic_ski WHERE atomic_ski_id = :id)",nativeQuery=true)
	public List<AtomicSki> findLogsById(@Param("id") Long id); 
}
