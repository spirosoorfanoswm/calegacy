package eu.ark.creditark.services.creditarkservices.dto.validationmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ModelValidationPortfolioDto implements Serializable {
    private String portfolio;
    private List<ValidationRuleDetailsDto> rules;

    public String getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }

    public List<ValidationRuleDetailsDto> getRules() {
        if(null == this.rules) this.rules = new ArrayList<>(1);
        return rules;
    }

    public void setRules(List<ValidationRuleDetailsDto> rules) {
        this.rules = rules;
    }
}
