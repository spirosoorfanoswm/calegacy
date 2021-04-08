package eu.ark.creditark.services.creditarkservices.dto.scenario.ui;

import eu.ark.creditark.services.creditarkservices.dto.KeyValueMultipleDto;
import eu.ark.creditark.services.creditarkservices.dto.KeyValuesDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ScenarioPortfolioStatisticsUIDto  extends ScenarioUI implements Serializable {
    private List<KeyValueMultipleDto> portfolioStatistics;
    private List<KeyValuesDto> limits;
    private List<KeyValuesDto> margins;
    private List<KeyValuesDto> allowances;

    public ScenarioPortfolioStatisticsUIDto() {
    }

    public List<KeyValuesDto> getMargins() {
        if(null == margins) this.margins = new ArrayList<>();
        return margins;
    }

    public void setMargins(List<KeyValuesDto> margins) {
        this.margins = margins;
    }

    public List<KeyValueMultipleDto> getPortfolioStatistics() {
        if(null == this.portfolioStatistics) this.portfolioStatistics = new ArrayList<>();
        return portfolioStatistics;
    }

    public void setPortfolioStatistics(List<KeyValueMultipleDto> portfolioStatistics) {
        this.portfolioStatistics = portfolioStatistics;
    }

    public List<KeyValuesDto> getLimits() {
        if(null == this.limits) this.limits = new ArrayList<>();
        return limits;
    }

    public void setLimits(List<KeyValuesDto> limits) {
        this.limits = limits;
    }


    public List<KeyValuesDto> getAllowances() {
        if(null == this.allowances) this.allowances = new ArrayList<>();
        return allowances;
    }

    public void setAllowances(List<KeyValuesDto> allowances) {
        this.allowances = allowances;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ScenarioPortfolioStatisticsUIDto{");
        sb.append("portfolioStatistics=").append(portfolioStatistics);
        sb.append(", limits=").append(limits);
        sb.append(", margings=").append(margins);
        sb.append(", allowances=").append(allowances);
        sb.append('}');
        return sb.toString();
    }
}
