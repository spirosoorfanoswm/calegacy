package eu.ark.creditark.services.creditarkservices.dto.scenario.ui;

import java.io.Serializable;

public class ScenarioProspectUIDto extends ScenarioUI implements Serializable {
    private ScenarioProspectParametersUIDto parameters;
    private ScenarioProspectStatisticsUIDto statistics;

    public ScenarioProspectUIDto() {
    }

    public ScenarioProspectParametersUIDto getParameters() {
        if(null == this.parameters) this.parameters = new ScenarioProspectParametersUIDto();
        return parameters;
    }

    public void setParameters(ScenarioProspectParametersUIDto parameters) {
        this.parameters = parameters;
    }

    public ScenarioProspectStatisticsUIDto getStatistics() {
        if(null == this.statistics) this.statistics = new ScenarioProspectStatisticsUIDto();
        return statistics;
    }

    public void setStatistics(ScenarioProspectStatisticsUIDto statistics) {
        this.statistics = statistics;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ScenarioProspectUIDto{");
        sb.append("parameters=").append(parameters);
        sb.append(", statistics=").append(statistics);
        sb.append('}');
        return sb.toString();
    }
}
