package in.ashokit.reports;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import in.ashokit.bindings.response.PlanResponse;

public class ExcelReportGenerator {
	
	public void export(List<PlanResponse> plans, HttpServletResponse response) throws IOException {
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet();
		
		XSSFRow headerrow = sheet.createRow(0);
		
		headerrow.createCell(0).setCellValue("plan ID");
		headerrow.createCell(1).setCellValue("plan Name");
		headerrow.createCell(2).setCellValue("Holder Name");
		headerrow.createCell(3).setCellValue("Holder SSN");
		headerrow.createCell(4).setCellValue("plan Status");
		
		for(int i = 0; i < plans.size(); i++) {
			PlanResponse plan = plans.get(i);
			XSSFRow datarow = sheet.createRow(i+1);
			datarow.createCell(0).setCellValue(plan.getPlanId());
			datarow.createCell(1).setCellValue(plan.getPlanName());
			datarow.createCell(2).setCellValue(plan.getHolderName());
			datarow.createCell(3).setCellValue(plan.getHolderSsn());
			datarow.createCell(4).setCellValue(plan.getPlanStatus());
		}
		
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
	
	

}
