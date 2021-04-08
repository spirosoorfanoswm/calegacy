package eu.ark.creditark.services.creditarkservices.dto.scenario.ui;

import eu.ark.creditark.services.creditarkservices.dto.KeyValueMultipleDto;
import eu.ark.creditark.services.creditarkservices.dto.KeyValuesDto;

import java.util.ArrayList;
import java.util.List;

public class ScenarioMainStatisticsUIDto  extends ScenarioUI {
    private List<KeyValueMultipleDto> scenarioStatistics;
    private List<KeyValuesDto> limits;
    private List<KeyValuesDto> margins;
    private List<KeyValuesDto> allowances;

    public ScenarioMainStatisticsUIDto() {
    }

    public List<KeyValueMultipleDto> getScenarioStatistics() {
        if(null == scenarioStatistics) this.scenarioStatistics = new ArrayList<>();
        return scenarioStatistics;
    }

    public void setScenarioStatistics(List<KeyValueMultipleDto> scenarioStatistics) {
        this.scenarioStatistics = scenarioStatistics;
    }

    public List<KeyValuesDto> getLimits() {
        if(null == limits) this.limits = new ArrayList<>();
        return limits;
    }

    public void setLimits(List<KeyValuesDto> limits) {
        this.limits = limits;
    }

    public List<KeyValuesDto> getMargins() {
        if(null == margins) this.margins = new ArrayList<>();
        return margins;
    }

    public void setMargins(List<KeyValuesDto> margins) {
        this.margins = margins;
    }

    public List<KeyValuesDto> getAllowances() {
        if(null == allowances) this.allowances = new ArrayList<>();
        return allowances;
    }

    public void setAllowances(List<KeyValuesDto> allowances) {
        this.allowances = allowances;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ScenarioMainStatisticsUIDto{");
        sb.append("scenarioStatistics=").append(scenarioStatistics);
        sb.append(", limits=").append(limits);
        sb.append(", margings=").append(margins);
        sb.append(", allowances=").append(allowances);
        sb.append('}');
        return sb.toString();
    }
}
