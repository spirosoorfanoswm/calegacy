package eu.ark.creditark.services.creditarkservices.dto.scenario.ui;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value="ScenarioPortfolioUIDto", description="Contain portfolio parameters and statistics of scenario")
public class ScenarioPortfolioUIDto extends ScenarioUI implements Serializable {
    @ApiModelProperty(value = "portfolio id", notes = "")
    private String portfolioId;
    @ApiModelProperty(value = "portfolio name", notes = "")
    private String portfolioName;
    private ScenarioPortfolioParametersUIDto parameters;
    private ScenarioPortfolioStatisticsUIDto statistics;

    public ScenarioPortfolioUIDto() {
    }

    public ScenarioPortfolioParametersUIDto getParameters() {
        if (null == this.parameters) this.parameters = new ScenarioPortfolioParametersUIDto();
        return parameters;
    }

    public void setParameters(ScenarioPortfolioParametersUIDto parameters) {
        this.parameters = parameters;
    }

    public ScenarioPortfolioStatisticsUIDto getStatistics() {
        if (null == this.statistics) this.statistics = new ScenarioPortfolioStatisticsUIDto();
        return statistics;
    }

    public void setStatistics(ScenarioPortfolioStatisticsUIDto statistics) {
        this.statistics = statistics;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ScenarioPortfolioUIDto{");
        sb.append("portfolioId='").append(portfolioId).append('\'');
        sb.append(", portfolioName='").append(portfolioName).append('\'');
        sb.append(", parameters=").append(parameters);
        sb.append(", statistics=").append(statistics);
        sb.append('}');
        return sb.toString();
    }
}
