package in.ashokit.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.entity.InsurancePlansEntity;

public interface InsurancePlansRepository extends JpaRepository<InsurancePlansEntity, Serializable> {
	
	@Query("select distinct planName from InsurancePlansEntity")
	public List<String> getPlanNames();
	
	@Query("select distinct planStatus from InsurancePlansEntity")
	public List<String> getPlanStatus();

}
