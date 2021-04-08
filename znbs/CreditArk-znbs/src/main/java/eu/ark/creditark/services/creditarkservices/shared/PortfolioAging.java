package eu.ark.creditark.services.creditarkservices.shared;

import java.io.Serializable;

public class PortfolioAging implements Serializable  {
    private int portfolioId;
    private String portfolioName;
    private double agingValue;
    public PortfolioAging () {}
    public PortfolioAging(int portfolioId, String portfolioName, double agingValue) {
        this.portfolioId = portfolioId;
        this.portfolioName = portfolioName;
        this.agingValue = agingValue;
    }

    public int getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(int portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public double getAgingValue() {
        return agingValue;
    }

    public void setAgingValue(double agingValue) {
        this.agingValue = agingValue;
    }
}
