package in.ashokit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "INSURANCE_PLANS")
public class InsurancePlansEntity {
	
	@Id
	@Column(name = "PLAN_ID")
	@GeneratedValue  // for search operation it is not required to insert it required
	private Integer planId;
	
	@Column(name = "PLAN_NAME")
	private String planName;
	
	@Column(name = "HOLDER_NAME")
	private String holderName;
	
	@Column(name = "HOLDER_SSN")
	private Integer holderSsn;
	
	@Column(name = "PLAN_STATUS")
	private String planStatus;

}
