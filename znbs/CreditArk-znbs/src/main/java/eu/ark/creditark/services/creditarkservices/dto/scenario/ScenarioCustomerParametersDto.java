package eu.ark.creditark.services.creditarkservices.dto.scenario;

import java.io.Serializable;

public class ScenarioCustomerParametersDto implements Serializable {

	private static final long serialVersionUID = -5667181452584636015L;
	private String customerName;
	private String customerId;
	private ScenarioPortfolioParametersDto parameters;
	
	
	public ScenarioCustomerParametersDto(String customerId, String customerName) {
		this.customerId = customerId;
		this.customerName = customerName;

	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public ScenarioPortfolioParametersDto getParameters() {
		return parameters;
	}
	public void setParameters(ScenarioPortfolioParametersDto parameters) {
		if(null == this.parameters)
			this.parameters = new ScenarioPortfolioParametersDto();
		this.parameters = parameters;
	}
	
}
