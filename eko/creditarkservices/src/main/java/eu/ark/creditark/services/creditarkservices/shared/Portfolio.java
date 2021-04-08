package eu.ark.creditark.services.creditarkservices.shared;

import java.io.Serializable;

public class Portfolio implements Serializable {
	private static final long serialVersionUID = -5509755995085240888L;
	
	private int portfolioId;
	private String description;
	
	public int getPortfolioId() {
		return portfolioId;
	}
	
	public void setPortfolioId(int portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
