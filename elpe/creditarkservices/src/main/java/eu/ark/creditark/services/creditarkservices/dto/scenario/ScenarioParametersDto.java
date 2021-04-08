package eu.ark.creditark.services.creditarkservices.dto.scenario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ScenarioParametersDto implements Serializable {

	private static final long serialVersionUID = -3324384402608740747L;
	private ScenarioClienteleParametersDto clienteleParameters;
	private List<ScenarioPortfolioParametersDto> portfoliosParameters;
	private List<ScenarioProspectParametersDto> prospectsParameters;
	private List<ScenarioCustomerParametersDto> customersParameters;
	
	public ScenarioClienteleParametersDto getClienteleParameters() {
		if(null == this.clienteleParameters) 
			this.clienteleParameters = new ScenarioClienteleParametersDto();
		return clienteleParameters;
	}
	public void setClienteleParameters(ScenarioClienteleParametersDto clienteleParameters) {
		this.clienteleParameters = clienteleParameters;
	}
	public List<ScenarioPortfolioParametersDto> getPortfoliosParameters() {
		if(null == this.portfoliosParameters) 
			this.portfoliosParameters = new ArrayList<>();
		return portfoliosParameters;
	}
	public void setPortfoliosParameters(List<ScenarioPortfolioParametersDto> portfoliosParameters) {
		this.portfoliosParameters = portfoliosParameters;
	}
	public List<ScenarioProspectParametersDto> getProspectsParameters() {
		if(null == this.prospectsParameters) 
			this.prospectsParameters = new ArrayList<>();
		return prospectsParameters;
	}
	public void setProspectsParameters(List<ScenarioProspectParametersDto> prospectsParameters) {
		this.prospectsParameters = prospectsParameters;
	}
	public List< ScenarioCustomerParametersDto> getCustomersParameters() {
		if(null == this.customersParameters) 
			this.customersParameters = new ArrayList<>();
		return customersParameters;
	}
	public void setCustomersParameters(List<ScenarioCustomerParametersDto> customersParameters) {
		this.customersParameters = customersParameters;
	}
	
}
