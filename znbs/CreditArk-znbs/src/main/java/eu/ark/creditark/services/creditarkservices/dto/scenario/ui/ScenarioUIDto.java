package eu.ark.creditark.services.creditarkservices.dto.scenario.ui;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value="ScenarioUIDto", description="Scenario container")
public class ScenarioUIDto  extends ScenarioUI implements Serializable {
    @ApiModelProperty(value = "Scenario id", notes = "")
    private int scenarioId;
    @ApiModelProperty(value = "Scenario name", notes = "")
    private String scenarioName;
    @ApiModelProperty(value = "Scenario owner name", notes = "")
    private String scenarioOwnerName;
    @ApiModelProperty(value = "Scenario owner login name", notes = "")
    private String scenarioOwnerlogin;

    private boolean editable;

    private boolean pending;

    private ScenarioMainStatisticsUIDto statistics;
    private ScenarioMainParametersUIDto parameters;
    private List<ScenarioPortfolioUIDto> portfolios;
    private List<ScenarioProspectUIDto> prospects;
    private List<ScenarioCustomerUIDto> customers;

    private List<String> deletedCustomers;
    private List<String> deletedProspects;

    public ScenarioUIDto() {
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public String getScenarioOwnerlogin() {
        return scenarioOwnerlogin;
    }

    public void setScenarioOwnerlogin(String scenarioOwnerlogin) {
        this.scenarioOwnerlogin = scenarioOwnerlogin;
    }

    public String getScenarioOwnerName() {
        return scenarioOwnerName;
    }

    public void setScenarioOwnerName(String scenarioOwnerName) {
        this.scenarioOwnerName = scenarioOwnerName;
    }

    public List<String> getDeletedCustomers() {
        if(null == this.deletedCustomers) this.deletedCustomers = new ArrayList<>();
        return deletedCustomers;
    }

    public void setDeletedCustomers(List<String> deletedCustomers) {
        this.deletedCustomers = deletedCustomers;
    }

    public List<String> getDeletedProspects() {
        if(null == this.deletedProspects) this.deletedProspects = new ArrayList<>();
        return deletedProspects;
    }

    public void setDeletedProspects(List<String> deletedProspects) {
        this.deletedProspects = deletedProspects;
    }

    public List<ScenarioCustomerUIDto> getCustomers() {
        if(null == this.customers) this.customers = new ArrayList<>();
        return customers;
    }

    public void setCustomers(List<ScenarioCustomerUIDto> customers) {
        this.customers = customers;
    }

    public List<ScenarioProspectUIDto> getProspects() {
        if(null == this.prospects) this.prospects = new ArrayList<>();
        return prospects;
    }

    public void setProspects(List<ScenarioProspectUIDto> prospects) {
        this.prospects = prospects;
    }

    public List<ScenarioPortfolioUIDto> getPortfolios() {
        if(null == this.portfolios) this.portfolios = new ArrayList<>();
        return portfolios;
    }

    public void setPortfolios(List<ScenarioPortfolioUIDto> portfolios) {
        this.portfolios = portfolios;
    }

    public ScenarioMainStatisticsUIDto getStatistics() {
        if(null == this.statistics) this.statistics  = new ScenarioMainStatisticsUIDto();
        return statistics;
    }

    public void setStatistics(ScenarioMainStatisticsUIDto statistics) {
        this.statistics = statistics;
    }

    public ScenarioMainParametersUIDto getParameters() {

        if(null == this.parameters) this.parameters  = new ScenarioMainParametersUIDto();

        return parameters;
    }

    public void setParameters(ScenarioMainParametersUIDto parameters) {
        this.parameters = parameters;
    }

    public int getScenarioId() {
        return scenarioId;
    }

    public void setScenarioId(int scenarioId) {
        this.scenarioId = scenarioId;
    }

    public String getScenarioName() {
        return scenarioName;
    }

    public void setScenarioName(String scenarioName) {
        this.scenarioName = scenarioName;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ScenarioUIDto{");
        sb.append("scenarioId=").append(scenarioId);
        sb.append(", scenarioName='").append(scenarioName).append('\'');
        sb.append(", scenarioOwnerName='").append(scenarioOwnerName).append('\'');
        sb.append(", scenarioOwnerlogin='").append(scenarioOwnerlogin).append('\'');
        sb.append(", editable=").append(editable);
        sb.append(", statistics=").append(statistics);
        sb.append(", parameters=").append(parameters);
        sb.append(", portfolios=").append(portfolios);
        sb.append(", prospects=").append(prospects);
        sb.append(", customers=").append(customers);
        sb.append(", deletedCustomers=").append(deletedCustomers);
        sb.append(", deletedProspects=").append(deletedProspects);
        sb.append('}');
        return sb.toString();
    }
}
