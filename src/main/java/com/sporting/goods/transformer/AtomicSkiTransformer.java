package com.sporting.goods.transformer;

import com.sporting.goods.model.AtomicSki;
import com.sporting.goods.model.AtomicSkiJRBean;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
public class AtomicSkiTransformer {
	public static AtomicSkiJRBean convertAtomicSkiToJRBean(AtomicSki atomicSki) {
		AtomicSkiJRBean jrBean = new AtomicSkiJRBean();
		if(atomicSki.getFirstName() != null) {
			jrBean.setFirstName(atomicSki.getFirstName());
		}
		if(atomicSki.getLastName() != null) {
			jrBean.setLastName(atomicSki.getLastName());
		}
		if(atomicSki.getAddress() != null) {
			jrBean.setAddress(atomicSki.getAddress());
		}
		if(atomicSki.getCity() != null) {
			jrBean.setCity(atomicSki.getCity());
		}
		if(atomicSki.getDateDue() != null) {
			jrBean.setDateDue(atomicSki.getDateDue());
		}
		if(atomicSki.getDateOut() != null) {
			jrBean.setDateOut(atomicSki.getDateOut());
			LocalDate dateOut = LocalDate.parse(convertDateToFormat(atomicSki.getDateOut()));
			LocalDate dateDue = LocalDate.parse(convertDateToFormat(atomicSki.getDateDue()));
			jrBean.setTotalRentalDays(String.valueOf(ChronoUnit.DAYS.between(dateOut, dateDue)));
		}
		if(atomicSki.getPhone() != null) {
			jrBean.setPhone(atomicSki.getPhone());
		}
		if(atomicSki.getBootIdNo() != null) {
			jrBean.setBootId(atomicSki.getBootIdNo());
		}
		if(atomicSki.getPoleCode() != null) {
			jrBean.setPoleCode(atomicSki.getPoleCode());
		}
		if(atomicSki.getSkiBoardIdNo() != null) {
			jrBean.setSkiBoard(atomicSki.getSkiBoardIdNo());
		}
		if(atomicSki.getSoleLength() != null) {
			jrBean.setSoleLength(atomicSki.getSoleLength());
		}
		if(atomicSki.getZipCode()!= null) {
			jrBean.setZipCode(atomicSki.getZipCode());
		}
		if(atomicSki.getAge() != null) {
			jrBean.setAge(atomicSki.getAge().toString());
		}
		if(atomicSki.getDownHillSkiingLevel()!= null) {
			jrBean.setDownhillsking(atomicSki.getDownHillSkiingLevel().toString());
		}
		if(atomicSki.getSnowboardStance()!= null) {
			jrBean.setSnowboard(atomicSki.getSnowboardStance().toString());
		}
		if(atomicSki.getLocalAccomodation()!= null) {
			jrBean.setLocalAccomodation(atomicSki.getLocalAccomodation());
		}
		if(atomicSki.getDrivingLicenseNo()!= null) {
			jrBean.setLicNo(atomicSki.getDrivingLicenseNo());
		}
		if(atomicSki.getWeight()!= null) {
			jrBean.setWeight(atomicSki.getWeight());
		}
		if(atomicSki.getHeight()!= null) {
			jrBean.setHeight(atomicSki.getHeight());
		}
		if(atomicSki.getRequestedSettings()!= null) {
			jrBean.setRequestedSettings(atomicSki.getRequestedSettings());
		}
		if(atomicSki.getVisualIndicatorSettings()!= null) {
			jrBean.setVisualIndicator(atomicSki.getVisualIndicatorSettings());
		}
		if(atomicSki.getEquipmentSubtotal()!= null) {
			jrBean.setEquipmentTotal(atomicSki.getEquipmentSubtotal().toString());
		}
		if(atomicSki.getEquipmentProtectionDamage()!= null) {
			jrBean.setDamageProtection(atomicSki.getEquipmentProtectionDamage().toString());
		}
		if(atomicSki.getTotal()!= null) {
			jrBean.setTotal(atomicSki.getTotal().toString());
		}
		return jrBean;
	}
	
	private static String convertDateToFormat(String date) {
		return date.substring(6,10)+"-"+date.substring(0,2)+"-"+date.substring(3,5);
	}
	
}
