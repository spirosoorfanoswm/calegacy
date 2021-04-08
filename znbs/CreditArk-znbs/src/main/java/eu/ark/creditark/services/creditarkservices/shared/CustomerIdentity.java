package eu.ark.creditark.services.creditarkservices.shared;

import java.io.Serializable;

public class CustomerIdentity implements Serializable {
	private static final long serialVersionUID = 2982179979136127459L;
	private String customerId;
	private int portfolioId;
	private String portfolio;
	private String vatNumber;
	private String customerName;
	
	public String getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public int getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(int portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(String portfolio) {
		this.portfolio = portfolio;
	}

	public String getVatNumber() {
		return vatNumber;
	}

	public void setVatNumber(String vatNumber) {
		this.vatNumber = vatNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
}
