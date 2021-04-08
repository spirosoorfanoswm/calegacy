package eu.ark.creditark.services.creditarkservices.dto.scenario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ScenarioStatisticsDto implements Serializable {

	private static final long serialVersionUID = -2175765326807071181L;
	private ScenarioPartStatisticsDto stats;
	private List<ScenarioPartStatisticsDto> clienteleDistrib;
	private List<ScenarioPartStatisticsDto> prospectsDistrib;
	private List<ScenarioPortfolioStatisticsDto> portfoliosStats;
	private List<ScenarioCustomerStatisticsDto> prospectsStats;
	public ScenarioPartStatisticsDto getStats() {
		return stats;
	}
	public void setStats(ScenarioPartStatisticsDto stats) {
		this.stats = stats;
	}
	public List<ScenarioPartStatisticsDto> getClienteleDistrib() {
		if(null == this.clienteleDistrib) 
			this.clienteleDistrib = new ArrayList<>();
		return clienteleDistrib;
	}
	public void setClienteleDistrib(List<ScenarioPartStatisticsDto> clienteleDistrib) {
		this.clienteleDistrib = clienteleDistrib;
	}
	public List<ScenarioPartStatisticsDto> getProspectsDistrib() {
		if(null == this.prospectsDistrib) 
			this.prospectsDistrib = new ArrayList<>();
		return prospectsDistrib;
	}
	public void setProspectsDistrib(List<ScenarioPartStatisticsDto> prospectsDistrib) {
		this.prospectsDistrib = prospectsDistrib;
	}
	public List<ScenarioPortfolioStatisticsDto> getPortfoliosStats() {
		if(null == this.portfoliosStats) 
			this.portfoliosStats = new ArrayList<>();
		return portfoliosStats;
	}
	public void setPortfoliosStats(List<ScenarioPortfolioStatisticsDto> portfoliosStats) {
		this.portfoliosStats = portfoliosStats;
	}
	public List<ScenarioCustomerStatisticsDto> getProspectsStats() {
		if(null == this.prospectsStats) 
			this.prospectsStats = new ArrayList<>();
		return prospectsStats;
	}
	public void setProspectsStats(List<ScenarioCustomerStatisticsDto> prospectsStats) {
		this.prospectsStats = prospectsStats;
	}

}
