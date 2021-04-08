package eu.ark.creditark.services.creditarkservices.model;

import java.io.Serializable;

public class ValidationRule implements Serializable {
    private String portfolio;
    private String description;
    private String value;

    public ValidationRule(String portfolio, String description, String value) {
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
