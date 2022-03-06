package in.ashokit.bindings.response;

import lombok.Data;

@Data
public class PlanResponse {
	
	private Integer planId;
	private String planName;
	private String holderName;
	private Integer holderSsn;
	private String planStatus;

}
