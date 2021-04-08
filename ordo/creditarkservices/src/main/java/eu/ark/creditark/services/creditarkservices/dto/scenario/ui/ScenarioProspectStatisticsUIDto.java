package eu.ark.creditark.services.creditarkservices.dto.scenario.ui;

import eu.ark.creditark.services.creditarkservices.dto.KeyValueMultipleDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ScenarioProspectStatisticsUIDto extends ScenarioUI implements Serializable {
    private List<KeyValueMultipleDto> prospectStatistics;

    public ScenarioProspectStatisticsUIDto() {
    }

    public List<KeyValueMultipleDto> getProspectStatistics() {
        if(null == this.prospectStatistics) this.prospectStatistics = new ArrayList<>();
        return prospectStatistics;
    }

    public void setProspectStatistics(List<KeyValueMultipleDto> prospectStatistics) {
        this.prospectStatistics = prospectStatistics;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ScenarioProspectStatisticsUIDto{");
        sb.append("prospectStatistics=").append(prospectStatistics);
        sb.append('}');
        return sb.toString();
    }
}
