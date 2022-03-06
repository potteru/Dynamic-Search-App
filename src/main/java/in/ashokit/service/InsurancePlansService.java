package in.ashokit.service;

import java.util.List;

import in.ashokit.bindings.request.SearchRequest;
import in.ashokit.bindings.response.PlanResponse;

public interface InsurancePlansService {
	
//	public String findByName(String planName,String planStatus);
	
//	public List<InsPlansEntity> search(String search);
	
//	public void excelData(); 
	
//	public void pdfData();
	
//  above methods are my analysis below are expert analysis	
	
	public List<PlanResponse> searchPlans(SearchRequest request);
	
	public List<String> getUniquePlanNames();
		
	public List<String> getUniquePlanStatus(); 

}
