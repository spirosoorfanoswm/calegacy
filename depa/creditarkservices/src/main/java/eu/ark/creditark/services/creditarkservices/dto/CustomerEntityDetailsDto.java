package eu.ark.creditark.services.creditarkservices.dto;

import java.io.Serializable;

public class CustomerEntityDetailsDto extends CustomerDetailsDto implements Serializable  {
    private String customerId;
    private int portfolioId;
    private String portfolio;
    private String vatNumber;
    private String customerName;

    public CustomerEntityDetailsDto(String customerId, int portfolioId, String portfolio, String vatNumber, String customerName) {
        this.customerId = customerId;
        this.portfolioId = portfolioId;
        this.portfolio = portfolio;
        this.vatNumber = vatNumber;
        this.customerName = customerName;
    }

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
