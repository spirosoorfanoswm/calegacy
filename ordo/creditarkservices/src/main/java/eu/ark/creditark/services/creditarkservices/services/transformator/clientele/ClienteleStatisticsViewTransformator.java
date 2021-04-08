package eu.ark.creditark.services.creditarkservices.services.transformator.clientele;


import eu.ark.creditark.services.creditarkservices.config.AppPropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.ark.creditark.services.creditarkservices.dto.ClienteleStatisticsDto;
import eu.ark.creditark.services.creditarkservices.dto.input.ClienteleTransformatorInputDto;

@Component("CLIENT_STATISTICS_VIEW")
public class ClienteleStatisticsViewTransformator implements ClienteleTransformator {

	@Autowired
	private AppPropertiesConfig appProperties;

	@SuppressWarnings("unchecked")
	@Override
	public ClienteleStatisticsDto transform(ClienteleTransformatorInputDto input) {
		return new ClienteleStatisticsDto(input.getInput().getSnapshotDate(), 
				input.getClienteleStatistics(),
				appProperties.getClientStatistics(), input.getContextInfo().getScores().length);
	}

    
}
