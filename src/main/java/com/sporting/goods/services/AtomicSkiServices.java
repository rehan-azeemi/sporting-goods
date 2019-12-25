package com.sporting.goods.services;

import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

	public AtomicSki getAtomicSki(Long atomicSkiId) {
		return atomicSkiRepository.findByAtomicSkiId(atomicSkiId);
	}

	public List<AtomicSki> getAllAtomicSkies() {
		return atomicSkiRepository.findAll();
	}

	public boolean generateExcelForEmails(){
		try {

			Workbook workbook = new XSSFWorkbook(); 
			CreationHelper createHelper = workbook.getCreationHelper();
			Sheet sheet = workbook.createSheet("Emails");
	        Font headerFont = workbook.createFont();
	        headerFont.setBold(true);
	        headerFont.setFontHeightInPoints((short) 14);
	        headerFont.setColor(IndexedColors.RED.getIndex());
	        CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFont(headerFont);
	        int rowNum = 0;
	        for(String email: atomicSkiRepository.findAllEmails()) {
	            Row row = sheet.createRow(rowNum++);
	            row.createCell(0)
	                    .setCellValue(email);
	        }
	        FileOutputStream fileOut = new FileOutputStream("customer-emails.xlsx");
	        workbook.write(fileOut);
	        fileOut.close();
	        workbook.close();
			
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		return true;
	}
}
