package eu.ark.creditark.services.creditarkservices.dto.input;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import eu.ark.creditark.services.creditarkservices.shared.*;

public class ClienteleTransformatorInputDto implements Serializable{

	private static final long serialVersionUID = -1649365743948560509L;
	private ClienteleStatisticsInputDto input;
	private ClienteleStatistics clienteleStatistics;
	private ConfigInfo configInfo;
	private ContextInfo contextInfo;
	private Customer customer;
	private List<CustomerIdentity> customerEntities;
	private CustomerDetails customerDetails;
	private Date snapshotDate;
	private ScenarioCustomerStatistics scenarioCustomerStatistics;
	private PortfolioAgingOverall portfolioAgingOverall;

	public ClienteleTransformatorInputDto(ContextInfo contextInfo, CustomerDetails customerDetails) {
		this.contextInfo = contextInfo;
		this.customerDetails = customerDetails;
	}

	public ClienteleTransformatorInputDto(List<CustomerIdentity> customerEntities) {
		this.customerEntities = customerEntities;
	}


	public ClienteleTransformatorInputDto(
			ClienteleStatisticsInputDto input,
			ClienteleStatistics clienteleStatistics,
			ConfigInfo configInfo, 
			ContextInfo contextInfo) {
		this.input = input;
		this.clienteleStatistics = clienteleStatistics;
		this.configInfo = configInfo;
		this.contextInfo = contextInfo;
	}

	public ClienteleTransformatorInputDto(
			ClienteleStatisticsInputDto input,
			ClienteleStatistics clienteleStatistics,
			ConfigInfo configInfo,
			ContextInfo contextInfo,
			PortfolioAgingOverall averagePortfolioAging) {
		this.input = input;
		this.clienteleStatistics = clienteleStatistics;
		this.configInfo = configInfo;
		this.contextInfo = contextInfo;
		this.portfolioAgingOverall = averagePortfolioAging;
	}

	public ClienteleStatisticsInputDto getInput() {
		return input;
	}
	public ClienteleStatistics getClienteleStatistics() {
		return clienteleStatistics;
	}
	public ConfigInfo getConfigInfo() {
		return configInfo;
	}
	public ContextInfo getContextInfo() {
		return contextInfo;
	}

	public Customer getCustomer() {
		return customer;
	}

	public CustomerDetails getCustomerDetails() {
		return customerDetails;
	}

	public Date getSnapshotDate() {
		return snapshotDate;
	}

	public void setSnapshotDate(Date snapshotDate) {
		this.snapshotDate = snapshotDate;
	}

	public List<CustomerIdentity> getCustomerEntities() {
		return customerEntities;
	}

	public void setCustomerEntities(List<CustomerIdentity> customerEntities) {
		this.customerEntities = customerEntities;
	}

	public ScenarioCustomerStatistics getScenarioCustomerStatistics() {
		return scenarioCustomerStatistics;
	}

	public void setScenarioCustomerStatistics(ScenarioCustomerStatistics scenarioCustomerStatistics) {
		this.scenarioCustomerStatistics = scenarioCustomerStatistics;
	}

	public PortfolioAgingOverall getPortfolioAgingOverall() {
		return portfolioAgingOverall;
	}

	public void setPortfolioAgingOverall(PortfolioAgingOverall portfolioAgingOverall) {
		this.portfolioAgingOverall = portfolioAgingOverall;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("ClienteleTransformatorInputDto{");
		sb.append("input=").append(input);
		sb.append(", clienteleStatistics=").append(clienteleStatistics);
		sb.append(", configInfo=").append(configInfo);
		sb.append(", contextInfo=").append(contextInfo);
		sb.append(", customer=").append(customer);
		sb.append(", customerEntities=").append(customerEntities);
		sb.append(", customerDetails=").append(customerDetails);
		sb.append(", snapshotDate=").append(snapshotDate);
		sb.append('}');
		return sb.toString();
	}
}
