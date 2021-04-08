package eu.ark.creditark.services.creditarkservices.services;

import java.util.List;
import java.util.Map;

import eu.ark.creditark.services.creditarkservices.dto.*;
import eu.ark.creditark.services.creditarkservices.dto.input.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import eu.ark.creditark.services.creditarkservices.enums.DtoTransformationType;
import eu.ark.creditark.services.creditarkservices.exceptions.DatabaseConnectionException;
import eu.ark.creditark.services.creditarkservices.repository.BusinessRepository;
import eu.ark.creditark.services.creditarkservices.services.transformator.clientele.ClienteleTransformatorFactory;
import eu.ark.creditark.services.creditarkservices.shared.ConfigInfo;
import eu.ark.creditark.services.creditarkservices.shared.ContextInfo;
import eu.ark.creditark.services.creditarkservices.shared.ReportProperties;

@Service
public class BusinessServiceImpl implements BusinessService {

	private final Logger logger = LoggerFactory.getLogger(getClass());


	@Autowired
	private BusinessRepository repositoryBucket;

	@Autowired
	ClienteleTransformatorFactory clienteleTransformatorFactory;

	@Autowired
	private UtilService utilService;


	@Value("${ca.system.scenario.user}")
	private String systemScenarioUser;

	@Override
	public ClienteleStatisticsDto clienteleStatistics(ClienteleStatisticsInputDto input)
			throws DatabaseConnectionException {
		String schema = utilService.getSchema((short)input.getContextId());
		return clienteleTransformatorFactory.getTransformatorFactory(DtoTransformationType.CLIENT_STATISTICS_VIEW)
				.transform(new ClienteleTransformatorInputDto(
						input, 
						repositoryBucket.getClienteleStatistics(input,
								schema),
						null,
						repositoryBucket.getContextInfo(
								new ContextInfoInputDto((short)input.getContextId(), systemScenarioUser),
								schema)));

	}

	@Override
	public ClienteleStatisticsGraphDto clienteleStatisticsGraph(ClienteleStatisticsInputDto input)
			throws DatabaseConnectionException {
		String schema = utilService.getSchema(input.getContextId());
		return clienteleTransformatorFactory.getTransformatorFactory(DtoTransformationType.CLIENT_STATISTICS_GRAPH)
				.transform(new ClienteleTransformatorInputDto(
						input, 
						repositoryBucket.getClienteleStatistics(input, schema),
						null,
						repositoryBucket.getContextInfo(
								new ContextInfoInputDto((short)input.getContextId(), systemScenarioUser),
								schema)));
	}

	@Override
	public Map<Integer, ReportProperties> getReportsData() throws DatabaseConnectionException {
		return null;//repositoryBucket.getReportsData();
	}

	@Override
	public ConfigInfo getConfigInfo(String user) throws DatabaseConnectionException {
		return repositoryBucket.getConfigInfo(user);
	}

	@Override
	public ContextInfo getContextInfo(int contextId, String loginName) throws DatabaseConnectionException {

		return repositoryBucket.getContextInfo(new ContextInfoInputDto((short) contextId, loginName), utilService.getSchema((short)contextId));
	}

	@Override
	public ClienteleDistributionDto clienteleDistribution(ClienteleStatisticsInputDto input, String loginName) throws DatabaseConnectionException {
		String schema = utilService.getSchema(input.getContextId());
		return clienteleTransformatorFactory.getTransformatorFactory(DtoTransformationType.CLIENT_DISTRIBUTION_VIEW)
		.transform(new ClienteleTransformatorInputDto(
				input, 
				repositoryBucket.getClienteleStatistics(input, schema),
				null, 
				repositoryBucket.getContextInfo(
						new ContextInfoInputDto(
								(short)input.getContextId(), loginName),
						schema),
				repositoryBucket.getPortfolioAging(input, schema)));
		
	}

	@Override
	public CustomerListDto customers(CustomersInputDto input) throws DatabaseConnectionException {
		CustomerListDto response = new CustomerListDto();

		String schema = utilService.getSchema(input.getContextId());
		response.setCustomersCount(repositoryBucket.getCustomersNum(
				new CustomersNumInputDto(
				input.getSnapshotDate(), 
				input.getScenarioId(), 
				input.getPortfolioId(), 
				input.getContextId()), schema));
		response.setCustomers(repositoryBucket.getCustomers(input, schema));
		
		return response;
		
	}

	@Override
	public CustomerDetailsDto customer(CustomerInputDto input, String loginName) throws DatabaseConnectionException {
		try {
			String schema = utilService.getSchema((short)input.getContextId());
			ClienteleTransformatorInputDto clienteleTransformatorInputDto =
					new ClienteleTransformatorInputDto(
							repositoryBucket.getContextInfo(new ContextInfoInputDto((short)input.getContextId(), loginName), schema),
							repositoryBucket.getCustomer(input, schema));
			if(input.getScenarioId()>0) {
				clienteleTransformatorInputDto.setScenarioCustomerStatistics(
				repositoryBucket.getCustomerStatistics(loginName,
						input.getScenarioId(), input.getCustomerId(), schema ));
			}
			clienteleTransformatorInputDto.setSnapshotDate(input.getSnapshotDate());
			return clienteleTransformatorFactory.getTransformatorFactory(
					DtoTransformationType.CUSTOMER_VIEW).transform(clienteleTransformatorInputDto);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public  List<CustomerEntityDetailsDto> search(CustomerInputSearchDto input) throws DatabaseConnectionException {
		return  clienteleTransformatorFactory.getTransformatorFactory(
				DtoTransformationType.CUSTOMER_ENTITY).transform
				(new ClienteleTransformatorInputDto(repositoryBucket.getCustomers(input, utilService.getSchema((short)input.getContextId()))));
	}

}
