package eu.ark.creditark.services.creditarkservices.shared;

import java.io.Serializable;

public class ScenarioBus implements Serializable {
	private static final long serialVersionUID = -5100683442050449761L;
	
	// Scenarios parameters
	public String description; 
	public ScenarioClienteleParameters scenarioParameters;
	
	// Portfolios parameters
	public int[] portfoliosIds;
	public ScenarioPortfolioParameters[] portfoliosParameters;
	public int[] deletedPortfoliosIds;
	
	// Prospects parameters
	public int[] prospectsIds;
	public ScenarioProspectParameters[] prospectsParameters;
	public int[] deletedProspectsIds;
	
	// Customers parameters
	public String[] customersIds;
	public ScenarioPortfolioParameters[] customersParameters;
	public String[] deletedCustomersIds;
}
