package eu.ark.creditark.services.creditarkservices.dto.scenario.ui;

import java.io.Serializable;

public class ScenarioCustomerUIDto extends ScenarioUI implements Serializable {
    private String customerId;
    private String customerName;
    private ScenarioCustomerParametersUIDto parameters;

    public ScenarioCustomerUIDto() {
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ScenarioCustomerParametersUIDto getParameters() {
        if(null == this.parameters) this.parameters = new ScenarioCustomerParametersUIDto();
        return parameters;
    }

    public void setParameters(ScenarioCustomerParametersUIDto parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ScenarioCustomerUIDto{");
        sb.append("customerId='").append(customerId).append('\'');
        sb.append(", customerName='").append(customerName).append('\'');
        sb.append(", parameters=").append(parameters);
        sb.append('}');
        return sb.toString();
    }
}
