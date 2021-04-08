package eu.ark.creditark.services.creditarkservices.model;

import java.io.Serializable;

public class ValidationRule implements Serializable {
    private String portfolio;
    private String description;
    private double value;

    public ValidationRule(String portfolio, String description, double value) {
        this.portfolio = portfolio;
        this.description = description;
        this.value = value;
    }

    public String getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
