package com.sporting.goods.model;

import javax.persistence.Column;

public class AtomicSkiDTO {
	private Long atomicSkiId;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private Long maxId;
	
	public AtomicSkiDTO(Long atomicSkiId, String firstName, String lastName, String phone, String email) {
		this.atomicSkiId = atomicSkiId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
	}
	
	public AtomicSkiDTO(Long maxId) {
		this.maxId = maxId;
	}

	public Long getAtomicSkiId() {
		return atomicSkiId;
	}

	public void setAtomicSkiId(Long atomicSkiId) {
		this.atomicSkiId = atomicSkiId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}
