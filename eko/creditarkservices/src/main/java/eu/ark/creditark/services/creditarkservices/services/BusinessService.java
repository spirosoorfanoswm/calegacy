package eu.ark.creditark.services.creditarkservices.services;

import java.util.List;
import java.util.Map;

import eu.ark.creditark.services.creditarkservices.dto.*;
import eu.ark.creditark.services.creditarkservices.dto.input.ClienteleStatisticsInputDto;
import eu.ark.creditark.services.creditarkservices.dto.input.CustomerInputDto;
import eu.ark.creditark.services.creditarkservices.dto.input.CustomerInputSearchDto;
import eu.ark.creditark.services.creditarkservices.dto.input.CustomersInputDto;
import eu.ark.creditark.services.creditarkservices.exceptions.DatabaseConnectionException;
import eu.ark.creditark.services.creditarkservices.shared.ConfigInfo;
import eu.ark.creditark.services.creditarkservices.shared.ContextInfo;
import eu.ark.creditark.services.creditarkservices.shared.ReportProperties;

public interface BusinessService {
	
	public Map<Integer, ReportProperties> getReportsData() throws DatabaseConnectionException;
	
	public ConfigInfo getConfigInfo(String loginName) throws DatabaseConnectionException;
	
	public ContextInfo getContextInfo(int contextId, String loginName) throws DatabaseConnectionException;
	
	public ClienteleStatisticsDto clienteleStatistics(ClienteleStatisticsInputDto input) throws DatabaseConnectionException;

	public ClienteleStatisticsGraphDto clienteleStatisticsGraph(ClienteleStatisticsInputDto input) throws DatabaseConnectionException;

	public ClienteleDistributionDto clienteleDistribution(ClienteleStatisticsInputDto input, String loginName) throws DatabaseConnectionException;

	public CustomerListDto customers(CustomersInputDto input)  throws DatabaseConnectionException;
	
	public CustomerDetailsDto customer(CustomerInputDto input, String loginName)  throws DatabaseConnectionException;

	public List<CustomerEntityDetailsDto> search(CustomerInputSearchDto input)  throws DatabaseConnectionException;

}
