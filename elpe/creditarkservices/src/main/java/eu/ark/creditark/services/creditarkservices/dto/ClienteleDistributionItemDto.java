package eu.ark.creditark.services.creditarkservices.dto;

import java.io.Serializable;

public class ClienteleDistributionItemDto implements Serializable {

	private static final long serialVersionUID = -1813144630462167607L;
	private String cas;
	private int customers;
	private String customersPercentage;
	private String exposure;
	private String exposurePercentage;
	
	
	public ClienteleDistributionItemDto(String cas, int customers,
										String customersPercentage, String exposure, String exposurePercentage) {
		this.cas = cas;
		this.customers = customers;
		this.customersPercentage = customersPercentage;
		this.exposure = exposure;
		this.exposurePercentage = exposurePercentage;
	}
	public String getCas() {
		return cas;
	}
	public void setCas(String cas) {
		this.cas = cas;
	}
	public int getCustomers() {
		return customers;
	}
	public void setCustomers(int customers) {
		this.customers = customers;
	}
	public String getCustomersPercentage() {
		return customersPercentage;
	}
	public void setCustomersPercentage(String customersPercentage) {
		this.customersPercentage = customersPercentage;
	}
	public String getExposure() {
		return exposure;
	}
	public void setExposure(String exposure) {
		this.exposure = exposure;
	}
	public String getExposurePercentage() {
		return exposurePercentage;
	}
	public void setExposurePercentage(String exposurePercentage) {
		this.exposurePercentage = exposurePercentage;
	}
	
	

}
