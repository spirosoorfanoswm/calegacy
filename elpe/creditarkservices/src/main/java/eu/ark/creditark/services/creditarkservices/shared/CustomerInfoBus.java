package eu.ark.creditark.services.creditarkservices.shared;

import java.io.Serializable;

public class CustomerInfoBus implements Serializable {
	private static final long serialVersionUID = 5267563038180205829L;
	private CustomerDetails customerDetails;
	private ScenarioCustomerStatistics customerStatistics;
	
	public CustomerDetails getCustomerDetails() {
		return customerDetails;
	}
	
	public void setCustomerDetails(CustomerDetails customerDetails) {
		this.customerDetails = customerDetails;
	}

	public ScenarioCustomerStatistics getCustomerStatistics() {
		return customerStatistics;
	}

	public void setCustomerStatistics(ScenarioCustomerStatistics customerStatistics) {
		this.customerStatistics = customerStatistics;
	}
}
