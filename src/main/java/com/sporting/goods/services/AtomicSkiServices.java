package com.sporting.goods.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sporting.goods.model.AtomicSki;
import com.sporting.goods.model.AtomicSkiDTO;
import com.sporting.goods.repositories.AtomicSkiRepository;

@Service
public class AtomicSkiServices {
	
	@Autowired
	private AtomicSkiRepository atomicSkiRepository;
	
	public void saveAtomicSki(AtomicSki atomicSki) {
		atomicSki.setActive(1);
		atomicSki.setLastModified(new Timestamp(System.currentTimeMillis()));
		atomicSkiRepository.save(atomicSki);
	}
	
	public List<AtomicSkiDTO> getAllLastRecordByEachCustomer() {
		return atomicSkiRepository.fetchLastRecordByCustomers();
	}
}
