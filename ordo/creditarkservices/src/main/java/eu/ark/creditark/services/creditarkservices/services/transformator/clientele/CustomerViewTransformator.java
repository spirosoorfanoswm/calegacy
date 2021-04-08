package eu.ark.creditark.services.creditarkservices.services.transformator.clientele;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.ark.creditark.services.creditarkservices.config.AppPropertiesConfig;
import eu.ark.creditark.services.creditarkservices.dto.CustomerDetailsDto;
import eu.ark.creditark.services.creditarkservices.dto.input.ClienteleTransformatorInputDto;
import eu.ark.creditark.services.creditarkservices.utils.DtoUtils;

@Component("CUSTOMER_VIEW")
public class CustomerViewTransformator implements ClienteleTransformator {

	@Autowired
	private AppPropertiesConfig appProperties;

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@SuppressWarnings("unchecked")
	@Override
	public CustomerDetailsDto transform(ClienteleTransformatorInputDto input) {
		CustomerDetailsDto response = new CustomerDetailsDto();
		DtoUtils.createSnapshotMainData(response, input, appProperties.getCustomerSnapshotData());
		DtoUtils.createStatistics(response, appProperties.getStatistics(), input);
		DtoUtils.createBehavioralData(response, input);
		DtoUtils.createQualitivelData(response, input);
		DtoUtils.createExternalData(response, input);
		logger.info("Start IFRS creation {}", input);
		DtoUtils.createIfrs(response, input);

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

    
}
