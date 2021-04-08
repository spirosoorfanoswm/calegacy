package eu.ark.creditark.services.creditarkservices.services.transformator.clientele;

import eu.ark.creditark.services.creditarkservices.config.AppPropertiesConfig;
import eu.ark.creditark.services.creditarkservices.dto.CustomerEntityDetailsDto;
import eu.ark.creditark.services.creditarkservices.dto.input.ClienteleTransformatorInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("CUSTOMER_ENTITY")
public class CustomerEntityViewTransformator implements ClienteleTransformator {

	@Autowired
	private AppPropertiesConfig appProperties;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerEntityDetailsDto> transform(ClienteleTransformatorInputDto input) {

		List<CustomerEntityDetailsDto> response = new ArrayList<>();
		input.getCustomerEntities().stream().forEach(item -> {
			response.add(new CustomerEntityDetailsDto(
					item.getCustomerId(),
					item.getPortfolioId(),
					item.getPortfolio(),
					item.getVatNumber(),
					item.getCustomerName()));
		});
		return  response;
	}

    
}
