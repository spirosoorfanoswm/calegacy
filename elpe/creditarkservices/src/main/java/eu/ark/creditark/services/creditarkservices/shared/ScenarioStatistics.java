package eu.ark.creditark.services.creditarkservices.shared;

import java.io.Serializable;
import java.util.HashMap;

public class ScenarioStatistics implements Serializable {
	private static final long serialVersionUID = -7390403070097760413L;
	
	private ScenarioPartStatistics stats;
	private ScenarioPartStatistics[] clienteleDistrib;
	private ScenarioPartStatistics[] prospectsDistrib;
	private HashMap<Integer, ScenarioPortfolioStatistics> portfoliosStats;
	private HashMap<Integer, ScenarioCustomerStatistics> prospectsStats;

	public ScenarioPartStatistics getStats() {
		return stats;
	}

	public void setStats(ScenarioPartStatistics stats) {
		this.stats = stats;
	}

	public ScenarioPartStatistics[] getClienteleDistribution() {
		return clienteleDistrib;
	}

	public void setClienteleDistribution(ScenarioPartStatistics[] clienteleDistrib) {
		this.clienteleDistrib = clienteleDistrib;
	}

	public ScenarioPartStatistics[] getProspectsDistribution() {
		return prospectsDistrib;
	}

	public void setProspectsDistribution(ScenarioPartStatistics[] prospectsDistrib) {
		this.prospectsDistrib = prospectsDistrib;
	}

	public HashMap<Integer, ScenarioPortfolioStatistics> getPortfoliosStats() {
		return portfoliosStats;
	}

	public void setPortfoliosStats(HashMap<Integer, ScenarioPortfolioStatistics> portfoliosStats) {
		this.portfoliosStats = portfoliosStats;
	}

	public HashMap<Integer, ScenarioCustomerStatistics> getProspectsStats() {
		return prospectsStats;
	}
	
	public ScenarioCustomerStatistics getProspectStatistics(int prospectId) {
		return prospectsStats.get(prospectId);
	}

	public void setProspectsStats(HashMap<Integer, ScenarioCustomerStatistics> prospectsStats) {
		this.prospectsStats = prospectsStats;
	}
}
