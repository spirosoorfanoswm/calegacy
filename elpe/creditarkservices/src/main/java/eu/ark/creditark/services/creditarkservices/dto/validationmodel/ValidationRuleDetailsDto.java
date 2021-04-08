package eu.ark.creditark.services.creditarkservices.dto.validationmodel;

import java.io.Serializable;

public class ValidationRuleDetailsDto implements Serializable {
    private String description;
    private double value;

    public ValidationRuleDetailsDto() {
    }

    public ValidationRuleDetailsDto(String description, double value) {
        this.description = description;
        this.value = value;
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
