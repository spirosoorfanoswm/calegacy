package eu.ark.creditark.services.creditarkservices.shared;

import java.io.Serializable;

public class ScenarioCustomerParameters implements Serializable {
	private static final long serialVersionUID = 507285016151450620L;
	private String customerName;
	private ScenarioPortfolioParameters parameters;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public ScenarioPortfolioParameters getParameters() {
		return parameters;
	}

	public void setParameters(ScenarioPortfolioParameters parameters) {
		this.parameters = parameters;
	}
}
