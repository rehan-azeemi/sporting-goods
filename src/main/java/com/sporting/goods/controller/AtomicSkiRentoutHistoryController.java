package com.sporting.goods.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(value="/pdf/{id}", method=RequestMethod.GET)
	public void getPDF(@PathVariable Long id,HttpServletRequest request, 
            HttpServletResponse response) {
		AtomicSki atomicSki = atomicSkiServices.getAtomicSki(id);
	
	    String filename = atomicSki.getFilePath().substring(atomicSki.getFilePath().indexOf("/")+1,atomicSki.getFilePath().length());
		
		File pdfFile = new File(atomicSki.getFilePath());
		if (pdfFile.exists()) 
        {
			Path file = Paths.get("Ski Rental Contracts/", filename);
            response.setContentType("application/pdf");
            response.addHeader("content-disposition", "inline;filename=" + filename);
            try
            {
            	Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            } 
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
	}
	
}
