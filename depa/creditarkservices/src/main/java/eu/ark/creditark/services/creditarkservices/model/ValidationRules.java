package eu.ark.creditark.services.creditarkservices.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ValidationRules implements Serializable {
    private List<ValidationRule> rules;

    public List<ValidationRule> getRules() {
        if(null==this.rules) this.rules = new ArrayList<>(1);
        return rules;
    }

    public void setRules(List<ValidationRule> rules) {
        this.rules = rules;
    }
}
