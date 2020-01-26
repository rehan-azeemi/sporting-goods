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
import com.sporting.goods.model.ExportExcel;
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
		String dest = atomic.getFirstName()+" "+atomic.getLastName()+"-"+System.currentTimeMillis()+"-report.pdf";
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

	public boolean generateExcel(ExportExcel atomicSkiExcel){
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
	        int rowNum = 1;
	        int colNum = 0;
	        
	        {
	        	Row row = sheet.createRow(0);
	        	if(atomicSkiExcel.isFirstName()) {
	            	row.createCell(colNum++).setCellValue("First Name");
	            }
	            if(atomicSkiExcel.isLastName()) {
	            	row.createCell(colNum++).setCellValue("Last Name");
	            }
	            if(atomicSkiExcel.isAge()) {
	            	row.createCell(colNum++).setCellValue("Age");
	            }
	            if(atomicSkiExcel.isCity()) {
	            	row.createCell(colNum++).setCellValue("City");
	            }
	            if(atomicSkiExcel.isAddress()) {
	            	row.createCell(colNum++).setCellValue("Address");
	            }
	            if(atomicSkiExcel.isPhone()) {
	            	row.createCell(colNum++).setCellValue("Phone");
	            }
	            if(atomicSkiExcel.isEmail()) {
	            	row.createCell(colNum++).setCellValue("Email");
	            }
	            if(atomicSkiExcel.isState()) {
	            	row.createCell(colNum++).setCellValue("State/Province");
	            	row.createCell(colNum++).setCellValue("Other Than USA");
	            }
	            if(atomicSkiExcel.isDateOfBirth()) {
	            	row.createCell(colNum++).setCellValue("Date of Birth");
	            }
	            if(atomicSkiExcel.isZipCode()) {
	            	row.createCell(colNum++).setCellValue("Zip Code");
	            }
	            if(atomicSkiExcel.isBootIdNo()) {
	            	row.createCell(colNum++).setCellValue("Boot Id No");
	            }
	            if(atomicSkiExcel.isPoleCode()) {
	            	row.createCell(colNum++).setCellValue("Pole Code");
	            }
	            if(atomicSkiExcel.isSoleLength()) {
	            	row.createCell(colNum++).setCellValue("Sole Length");
	            }
	            if(atomicSkiExcel.isSkiBoardIdNo()) {
	            	row.createCell(colNum++).setCellValue("Ski Board Id No");
	            }
	            if(atomicSkiExcel.isDateOut()) {
	            	row.createCell(colNum++).setCellValue("Date Out");
	            }
	            if(atomicSkiExcel.isDateDue()) {
	            	row.createCell(colNum++).setCellValue("Date Due");
	            }
	            
	            if(atomicSkiExcel.isLocalAccomodation()) {
	            	row.createCell(colNum++).setCellValue("Local Accomodation");
	            }
	            if(atomicSkiExcel.isDrivingLicenseNo()) {
	            	row.createCell(colNum++).setCellValue("Driving License No");
	            }
	            if(atomicSkiExcel.isDrivingLicenseState()) {
	            	row.createCell(colNum++).setCellValue("Driving License State");
	            }
	            if(atomicSkiExcel.isEquipmentSubtotal()) {
	            	row.createCell(colNum++).setCellValue("Subtotal");
	            }
	            if(atomicSkiExcel.isEquipmentProtectionDamage()) {
	            	row.createCell(colNum++).setCellValue("Damage Protection");
	            }
	            if(atomicSkiExcel.isTotal()) {
	            	row.createCell(colNum++).setCellValue("Total");
	            }
	            if(atomicSkiExcel.isTechnician()) {
	            	row.createCell(colNum++).setCellValue("Technician");
	            }
	            if(atomicSkiExcel.isVisualIndicatorSettings()) {
	            	row.createCell(colNum++).setCellValue("V I Settings");
	            }
	            if(atomicSkiExcel.isRequestedSettings()) {
	            	row.createCell(colNum++).setCellValue("R Settings");
	            }
	            if(atomicSkiExcel.isReleaseOfSignedLiability()) {
	            	row.createCell(colNum++).setCellValue("Release of Signed Liability");
	            }
	            if(atomicSkiExcel.isNote()) {
	            	row.createCell(colNum++).setCellValue("Note");
	            }
	        }
	        
	        for(AtomicSki atomicSki: getAllAtomicSkies()) {
	            Row row = sheet.createRow(rowNum);
	            colNum = 0;
	            	if(atomicSkiExcel.isFirstName()) {
		            	row.createCell(colNum++).setCellValue(atomicSki.getFirstName());
		            }
		            if(atomicSkiExcel.isLastName()) {
		            	row.createCell(colNum++).setCellValue(atomicSki.getLastName());
		            }
		            if(atomicSkiExcel.isAge()) {
		            	row.createCell(colNum++).setCellValue(atomicSki.getAge());
		            }
		            if(atomicSkiExcel.isCity()) {
		            	row.createCell(colNum++).setCellValue(atomicSki.getCity());
		            }
		            if(atomicSkiExcel.isAddress()) {
		            	row.createCell(colNum++).setCellValue(atomicSki.getAddress());
		            }
		            if(atomicSkiExcel.isPhone()) {
		            	row.createCell(colNum++).setCellValue(atomicSki.getPhone());
		            }
		            if(atomicSkiExcel.isEmail()) {
		            	row.createCell(colNum++).setCellValue(atomicSki.getEmail());
		            }
		            if(atomicSkiExcel.isState()) {
		            	if(atomicSki.getOtherThanUsa() != null && atomicSki.getOtherThanUsa()) {
		            		row.createCell(colNum++).setCellValue(atomicSki.getProvince());
		            		row.createCell(colNum++).setCellValue("Yes");
		            	}
		            	else {
		            		row.createCell(colNum++).setCellValue(stateServices.getStateName(atomicSki.getStateId()));
		            		row.createCell(colNum++).setCellValue("No");
		            	}
		            	
		            }
		            if(atomicSkiExcel.isDateOfBirth()) {
		            	row.createCell(colNum++).setCellValue(atomicSki.getDateOfBirth());
		            }
		            if(atomicSkiExcel.isZipCode()) {
		            	row.createCell(colNum++).setCellValue(atomicSki.getZipCode());
		            }
		            if(atomicSkiExcel.isBootIdNo()) {
		            	row.createCell(colNum++).setCellValue(atomicSki.getBootIdNo());
		            }
		            if(atomicSkiExcel.isPoleCode()) {
		            	row.createCell(colNum++).setCellValue(atomicSki.getPoleCode());
		            }
		            if(atomicSkiExcel.isSoleLength()) {
		            	row.createCell(colNum++).setCellValue(atomicSki.getSoleLength());
		            }
		            if(atomicSkiExcel.isSkiBoardIdNo()) {
		            	row.createCell(colNum++).setCellValue(atomicSki.getSkiBoardIdNo());
		            }
		            if(atomicSkiExcel.isDateOut()) {
		            	row.createCell(colNum++).setCellValue(atomicSki.getDateOut());
		            }
		            if(atomicSkiExcel.isDateDue()) {
		            	row.createCell(colNum++).setCellValue(atomicSki.getDateDue());
		            }
		            if(atomicSkiExcel.isLocalAccomodation()) {
		            	row.createCell(colNum++).setCellValue(atomicSki.getLocalAccomodation());
		            }
		            if(atomicSkiExcel.isDrivingLicenseNo()) {
		            	row.createCell(colNum++).setCellValue(atomicSki.getDrivingLicenseNo());
		            }
		            if(atomicSkiExcel.isDrivingLicenseState()) {
		            	row.createCell(colNum++).setCellValue(stateServices.getStateName(atomicSki.getDrivingLicenseStateId()));
		            }
		            if(atomicSkiExcel.isEquipmentSubtotal()) {
		            	row.createCell(colNum++).setCellValue(atomicSki.getEquipmentSubtotal());
		            }
		            if(atomicSkiExcel.isEquipmentProtectionDamage()) {
		            	row.createCell(colNum++).setCellValue(atomicSki.getEquipmentProtectionDamage());
		            }
		            if(atomicSkiExcel.isTotal()) {
		            	row.createCell(colNum++).setCellValue(atomicSki.getTotal());
		            }
		            if(atomicSkiExcel.isTechnician()) {
		            	row.createCell(colNum++).setCellValue(atomicSki.getTechnician());
		            }
		            if(atomicSkiExcel.isVisualIndicatorSettings()) {
		            	row.createCell(colNum++).setCellValue(atomicSki.getVisualIndicatorSettings());
		            }
		            if(atomicSkiExcel.isRequestedSettings()) {
		            	row.createCell(colNum++).setCellValue(atomicSki.getRequestedSettings());
		            }
		            if(atomicSkiExcel.isReleaseOfSignedLiability()) {
		            	row.createCell(colNum++).setCellValue((atomicSki.getReleaseOfSignedLiability())?"Yes":"No");
		            }
		            if(atomicSkiExcel.isNote()) {
		            	row.createCell(colNum++).setCellValue(atomicSki.getNote());
		            }
	            
	            rowNum++;
	        }
	        FileOutputStream fileOut = new FileOutputStream("customer-data-"+System.currentTimeMillis()+".xlsx");
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
