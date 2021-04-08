package eu.ark.creditark.services.creditarkservices.shared;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;



public class InitialBus implements Serializable {
	private static final long serialVersionUID = 5976697636018475491L;
	private ConfigInfo configInfo;
	private ContextInfo contextInfo;
	private ClienteleStatistics clienteleStatistics;
	private HashMap<Integer,ClienteleStatistics> portfoliosStatistics;
	private LinkedHashMap<Integer, Scenario> scenarios;
	
	public ConfigInfo getConfigInfo() {
		return configInfo;
	}
	
	public void setConfigInfo(ConfigInfo configInfo) {
		this.configInfo = configInfo;
	}

	public ContextInfo getContextInfo() {
		return contextInfo;
	}

	public void setContextInfo(ContextInfo contextInfo) {
		this.contextInfo = contextInfo;
	}

	public ClienteleStatistics getClienteleStatistics() {
		return clienteleStatistics;
	}

	public void setClienteleStatistics(ClienteleStatistics clienteleStatistics) {
		this.clienteleStatistics = clienteleStatistics;
	}

	public HashMap<Integer,ClienteleStatistics> getPortfoliosStatistics() {
		return portfoliosStatistics;
	}

	public void setPortfoliosStatistics(HashMap<Integer,ClienteleStatistics> portfoliosStatistics) {
		this.portfoliosStatistics = portfoliosStatistics;
	}

	public LinkedHashMap<Integer, Scenario> getScenarios() {
		return scenarios;
	}

	public void setScenarios(LinkedHashMap<Integer, Scenario> scenarios) {
		this.scenarios = scenarios;
	}
}
