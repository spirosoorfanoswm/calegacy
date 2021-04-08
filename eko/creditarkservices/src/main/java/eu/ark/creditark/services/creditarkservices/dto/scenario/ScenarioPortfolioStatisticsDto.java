package eu.ark.creditark.services.creditarkservices.dto.scenario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ScenarioPortfolioStatisticsDto implements Serializable {

	private static final long serialVersionUID = 1132615529117008810L;
	private int portfolioId;
	private ScenarioPartStatisticsDto partStatistics;
	private List<ScenarioPartStatisticsDto> statDistribution;
	
	public int getPortfolioId() {
		return portfolioId;
	}
	public void setPortfolioId(int portfolioId) {
		this.portfolioId = portfolioId;
	}
	public ScenarioPartStatisticsDto getPartStatistics() {
		return partStatistics;
	}
	public void setPartStatistics(ScenarioPartStatisticsDto partStatistics) {
		this.partStatistics = partStatistics;
	}
	public List<ScenarioPartStatisticsDto> getStatDistribution() {
		if(null==this.statDistribution)
			this.statDistribution = new ArrayList<>();
		return statDistribution;
	}
	public void setStatDistribution(List<ScenarioPartStatisticsDto> statDistribution) {
		this.statDistribution = statDistribution;
	}
	
}
