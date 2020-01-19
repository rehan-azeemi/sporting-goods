package com.sporting.goods.services;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.sporting.goods.model.AtomicSki;
import com.sporting.goods.model.AtomicSkiDTO;
import com.sporting.goods.model.AtomicSkiJRBean;
import com.sporting.goods.model.Employee;
import com.sporting.goods.repositories.AtomicSkiRepository;
import com.sporting.goods.transformer.AtomicSkiTransformer;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@Service
public class AtomicSkiServices {

	@Autowired
	private AtomicSkiRepository atomicSkiRepository;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private StateServices stateServices;

	public Long saveAtomicSki(AtomicSki atomicSki) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employee emp = employeeService.findUserByUsername(auth.getName());
		atomicSki.setActive(1);
		atomicSki.setLastModified(new Timestamp(System.currentTimeMillis()));
		atomicSki.setLastModifiedBy(emp);
		AtomicSki savedAtomSki = atomicSkiRepository.save(atomicSki);
		return savedAtomSki.getAtomicSkiId();
	}

	public List<AtomicSkiDTO> getAllLastRecordByEachCustomer() {
		return atomicSkiRepository.fetchLastRecordByCustomers();
	}

	public AtomicSki getAtomicSki(Long atomicSkiId) {
		return atomicSkiRepository.findByAtomicSkiId(atomicSkiId);
	}

	public List<AtomicSki> getAllAtomicSkies() {
		return atomicSkiRepository.findAllAtomicSki();
	}
	
	public List<AtomicSki> findLogsById(Long id){
		return atomicSkiRepository.findLogsById(id);
	}
	
	public void generateForm(Long id) {
		try {
		AtomicSki atomic = atomicSkiRepository.findByAtomicSkiId(id);
		String dest = "C:\\Forms\\"+atomic.getFirstName()+" "+atomic.getLastName()+"-"+System.currentTimeMillis()+"-report.pdf";
		File file = ResourceUtils.getFile("atomicski.jrxml");
		JasperReport jr = JasperCompileManager.compileReport(file.getAbsolutePath());
		AtomicSkiJRBean atomicSkiJRBean = AtomicSkiTransformer.convertAtomicSkiToJRBean(atomic);
		atomicSkiJRBean.setState(stateServices.getStateName(atomic.getStateId()));
		atomicSkiJRBean.setLicState(stateServices.getStateName(atomic.getDrivingLicenseStateId()));
		ArrayList<AtomicSkiJRBean> list = new ArrayList<>();
		list.add(atomicSkiJRBean);
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(list);
		Map<String,Object> map = new HashMap<String,Object>();
		JasperPrint jp = JasperFillManager.fillReport(jr, map,ds);
		JasperExportManager.exportReportToPdfFile(jp,dest);
		JasperPrintManager.printReport(jp, false);
		JasperPrintManager.printReport(jp, false);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public boolean generateExcelForEmails(){
		try {

			Workbook workbook = new XSSFWorkbook(); 
			CreationHelper createHelper = workbook.getCreationHelper();
			Sheet sheet = workbook.createSheet("Customer");
	        Font headerFont = workbook.createFont();
	        headerFont.setBold(true);
	        headerFont.setFontHeightInPoints((short) 14);
	        headerFont.setColor(IndexedColors.RED.getIndex());
	        CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFont(headerFont);
	        int rowNum = 0;
	        for(AtomicSki atomicSki: getAllAtomicSkies()) {
	            Row row = sheet.createRow(rowNum++);
	            row.createCell(0).setCellValue(atomicSki.getEmail());
	        }
	        FileOutputStream fileOut = new FileOutputStream("customer-data.xlsx");
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
