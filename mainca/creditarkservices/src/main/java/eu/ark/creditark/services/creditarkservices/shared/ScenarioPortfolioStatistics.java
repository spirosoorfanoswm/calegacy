package eu.ark.creditark.services.creditarkservices.shared;

import java.io.Serializable;

public class ScenarioPortfolioStatistics implements Serializable {
	private static final long serialVersionUID = -8501431288507524807L;
	
	private ScenarioPartStatistics partStatistics;
	private ScenarioPartStatistics[] statDistribution;
	
	public ScenarioPartStatistics getPartStatistics() {
		return partStatistics;
	}
	
	public void setPartStatistics(ScenarioPartStatistics partStatistics) {
		this.partStatistics = partStatistics;
	}

	public ScenarioPartStatistics[] getStatDistribution() {
		return statDistribution;
	}

	public void setStatDistribution(ScenarioPartStatistics[] statDistribution) {
		this.statDistribution = statDistribution;
	}
}
