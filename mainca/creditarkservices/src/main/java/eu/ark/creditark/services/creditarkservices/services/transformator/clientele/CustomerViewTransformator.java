package eu.ark.creditark.services.creditarkservices.services.transformator.clientele;

import eu.ark.creditark.services.creditarkservices.enums.GenerealConstants;
import eu.ark.creditark.services.creditarkservices.model.mongo.CustomerKpis;
import eu.ark.creditark.services.creditarkservices.model.mongo.Kpi;
import eu.ark.creditark.services.creditarkservices.repository.CustomerKPIRepository;
import eu.ark.creditark.services.creditarkservices.repository.KPIRepository;
import eu.ark.creditark.services.creditarkservices.utils.DateUtils;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import eu.ark.creditark.services.creditarkservices.config.AppPropertiesConfig;
import eu.ark.creditark.services.creditarkservices.dto.CustomerDetailsDto;
import eu.ark.creditark.services.creditarkservices.dto.input.ClienteleTransformatorInputDto;
import eu.ark.creditark.services.creditarkservices.utils.DtoUtils;

@Component("CUSTOMER_VIEW")
public class CustomerViewTransformator implements ClienteleTransformator {

	@Autowired
	private AppPropertiesConfig appProperties;

	@Autowired
	private CustomerKPIRepository customerKPIRepository;
	@Autowired
	private KPIRepository kpiRepository;

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@SuppressWarnings("unchecked")
	@Override
	public CustomerDetailsDto transform(ClienteleTransformatorInputDto input) {
		kpiRepository.findAll();
		List<CustomerKpis> customerKpis = customerKPIRepository.findOnSnapshot(input.getCustomerDetails().getContextId()+"",
				input.getCustomerDetails().getCustomerId(), DateUtils.dateToStringNoExc(input.getSnapshotDate(),
						GenerealConstants.DATE_FORMAT.getValue()));

		CustomerDetailsDto response = new CustomerDetailsDto();
		DtoUtils.createSnapshotMainData(response, input, appProperties.getCustomerSnapshotData());
		DtoUtils.createStatistics(response, appProperties.getStatistics(), input);
		DtoUtils.createBehavioralData(response, input);
		DtoUtils.createQualitivelData(response, input);
		DtoUtils.createExternalData(response, input);
		logger.info("Start IFRS creation {}", input);
		DtoUtils.createIfrs(response, customerKpis, getKpis());

		DtoUtils.createScenarioStatistics(response, input);
		/*DtoUtils.createBuckets(response, input);
		DtoUtils.createMitigants(response, input);
		DtoUtils.createStatistics(response, appProperties.getStatistics(), input);
		DtoUtils.createBehavioralData(response, input);
		DtoUtils.createQualitivelData(response, input);
		DtoUtils.createExternalData(response, input);
		DtoUtils.createIfrs(response, input);*/
		return response;
	}


	@Cacheable(value = "kpis")
	public Set<Kpi> getKpis() {
		return new HashSet(kpiRepository.findAll());
	}
    
}
