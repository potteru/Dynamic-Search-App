package in.ashokit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.ashokit.bindings.request.SearchRequest;
import in.ashokit.bindings.response.PlanResponse;
import in.ashokit.entity.InsurancePlansEntity;
import in.ashokit.repository.InsurancePlansRepository;

@Service
public class InsurancePlansServiceImpl implements InsurancePlansService {
	
	@Autowired
	private InsurancePlansRepository repo;
	
	InsurancePlansEntity entity = new InsurancePlansEntity();
	

	@Override
	public List<PlanResponse> searchPlans(SearchRequest request) {
		
		InsurancePlansEntity entity = new InsurancePlansEntity();
		
		if(request != null && request.getPlanName() != null && !request.getPlanName().equals("") ) {
			entity.setPlanName(request.getPlanName());
		}
		if(request != null && request.getPlanStatus() != null && !request.getPlanStatus().equals("")) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		
		Example<InsurancePlansEntity> of = Example.of(entity);// this line is used to create queries
		List<InsurancePlansEntity> findAll = repo.findAll(of);
		
		List<PlanResponse> plans = new ArrayList<>();
		
		for(InsurancePlansEntity plan : findAll) {
			PlanResponse planresp = new PlanResponse();
			BeanUtils.copyProperties(plan,planresp);
			plans.add(planresp);
		}	
		return plans;
	}


	@Override
	public List<String> getUniquePlanNames() {
		
		return repo.getPlanNames();
	}


	@Override
	public List<String> getUniquePlanStatus() {
		
		return repo.getPlanStatus();
	}
	
	
}
