package in.ashokit.rest;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import in.ashokit.bindings.request.SearchRequest;
import in.ashokit.bindings.response.PlanResponse;
import in.ashokit.reports.ExcelReportGenerator;
import in.ashokit.reports.PdfReportGenarator;
import in.ashokit.service.InsurancePlansService;

@RestController
public class InsurenceRestController {
	
	@Autowired
	private InsurancePlansService service;
	
	@GetMapping("/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Plans_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
        List<PlanResponse> plans = service.searchPlans(null);
         
        PdfReportGenarator exporter = new PdfReportGenarator();
        exporter.exportPdf(plans,response);
         
    }
	
	@GetMapping("/excel")
	public void generateExcel(HttpServletResponse response) throws IOException {
		
		response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename = Plans_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
		
		List<PlanResponse> plans = service.searchPlans(null);
		ExcelReportGenerator excelReport = new ExcelReportGenerator();
		excelReport.export(plans, response);
	}
	
	@PostMapping("/plans")
	public ResponseEntity<List<PlanResponse>> getPlans (@RequestBody SearchRequest request) {
		List<PlanResponse> searchPlans = service.searchPlans(request);
		return new ResponseEntity<>(searchPlans,HttpStatus.OK);
	}
	
	/*		// Data will come in the URL not sequre if data is sensitive
	@GetMapping("/plans")
	public ResponseEntity<List<PlanResponse>> getPlans (SearchRequest request) {
		List<PlanResponse> searchPlans = service.searchPlans(request);
		return new ResponseEntity<>(searchPlans,HttpStatus.OK);
	}
	*/
	@GetMapping("/plannames")
	public ResponseEntity<List<String>> getPlanNames(){
		List<String> planNames = service.getUniquePlanNames();
		return new ResponseEntity<>(planNames, HttpStatus.OK);
	}
	
	@GetMapping("/planstatus")
	public ResponseEntity<List<String>> getPlanStatus(){
		List<String> planStatus = service.getUniquePlanStatus();
		return new ResponseEntity<>(planStatus, HttpStatus.OK);
	}
}
