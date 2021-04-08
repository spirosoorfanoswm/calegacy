package eu.ark.creditark.services.creditarkservices.dto.validationmodel;

import java.io.Serializable;

public class ValidationRuleDetailsDto implements Serializable {
    private String description;
    private String value;

    public ValidationRuleDetailsDto() {
    }

    public ValidationRuleDetailsDto(String description, String value) {
        this.description = description;
        this.value = value;
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
