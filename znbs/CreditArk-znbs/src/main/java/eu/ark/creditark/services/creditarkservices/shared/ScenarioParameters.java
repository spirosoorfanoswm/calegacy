package eu.ark.creditark.services.creditarkservices.shared;

import java.io.Serializable;
import java.util.LinkedHashMap;

public class ScenarioParameters implements Serializable {
	private static final long serialVersionUID = 8202474066207550435L;
	
	private ScenarioClienteleParameters clienteleParameters = new ScenarioClienteleParameters();
	private LinkedHashMap<Integer, ScenarioPortfolioParameters> portfoliosParameters;
	private LinkedHashMap<Integer, ScenarioProspectParameters> prospectsParameters;
	private LinkedHashMap<String, ScenarioCustomerParameters> customersParameters;

	public ScenarioClienteleParameters getClienteleParameters() {
		return clienteleParameters;
	}

	public void setClienteleParameters(ScenarioClienteleParameters scenarioClienteleParameters) {
		this.clienteleParameters = scenarioClienteleParameters;
	}

	public ScenarioPortfolioParameters getPortfolioParameters(int portfolioId) {
		if (portfoliosParameters == null) return clienteleParameters;
		return portfoliosParameters.get(portfolioId);
	}
	
	public void setPortfolioParameters(int portfolioId, ScenarioPortfolioParameters portfolioParameters) {
		if (portfoliosParameters == null)
			portfoliosParameters = new LinkedHashMap<Integer, ScenarioPortfolioParameters>();
		portfoliosParameters.put(portfolioId, portfolioParameters);
	}
	
	public LinkedHashMap<Integer, ScenarioPortfolioParameters> getPortfoliosParams() {
		return portfoliosParameters;
	}

	public void setPortfoliosParams(LinkedHashMap<Integer, ScenarioPortfolioParameters> portfoliosParams) {
		this.portfoliosParameters = portfoliosParams;
	}

	public ScenarioProspectParameters getProspectParameters(int prospectId) {
		return prospectsParameters.get(prospectId);
	}
	
	public void setProspectParameters(int prospectId, ScenarioProspectParameters prospectParameters) {
		if (prospectsParameters == null)
			prospectsParameters = new LinkedHashMap<Integer, ScenarioProspectParameters>();
		prospectsParameters.put(prospectId, prospectParameters);
	}
	
	public LinkedHashMap<Integer, ScenarioProspectParameters> getProspectsParams() {
		return prospectsParameters;
	}

	public void setProspectsParams(LinkedHashMap<Integer, ScenarioProspectParameters> prospectsParams) {
		this.prospectsParameters = prospectsParams;
	}


	public LinkedHashMap<String, ScenarioCustomerParameters> getCustomersParams() {
		return customersParameters;
	}

	public void setCustomersParams(LinkedHashMap<String, ScenarioCustomerParameters> customersParams) {
		this.customersParameters = customersParams;
	}
	
	public ScenarioPortfolioParameters getCustomerParameters(String customerId) {
		return customersParameters.get(customerId).getParameters();
	}
	
	public void setCustomerParameters(String customerId, ScenarioCustomerParameters customerParameters) {
		if (customersParameters == null)
			customersParameters = new LinkedHashMap<String, ScenarioCustomerParameters>();
		customersParameters.put(customerId, customerParameters);
	}
}
