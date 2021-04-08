package eu.ark.creditark.services.creditarkservices.services.transformator.modelvalidation;

import eu.ark.creditark.services.creditarkservices.dto.validationmodel.ModelValidationDto;
import eu.ark.creditark.services.creditarkservices.dto.validationmodel.ModelValidationPortfolioDto;
import eu.ark.creditark.services.creditarkservices.dto.validationmodel.ValidationRuleDetailsDto;
import eu.ark.creditark.services.creditarkservices.model.ValidationRule;
import eu.ark.creditark.services.creditarkservices.model.ValidationRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
@Component("VALIDATION_RULE_LIST")
public class ValidationRuleViewTransformator implements ValidationRuleTransformator {


	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public ModelValidationDto transform(ValidationRules input) {
		ModelValidationDto response = new ModelValidationDto();
		List<ModelValidationPortfolioDto> rules = new ArrayList<>(1);
		input.getRules().stream().collect(groupingBy(x -> x.getPortfolio()))
				.entrySet().forEach(itm -> {
			ModelValidationPortfolioDto portfolioDto = new ModelValidationPortfolioDto();
			portfolioDto.setPortfolio(itm.getKey());
			portfolioDto.setRules(
			itm.getValue().stream()
					.map(rule ->
							new ValidationRuleDetailsDto(
									rule.getDescription(),
									rule.getValue()))
			.collect(Collectors.toList()));
			rules.add(portfolioDto);
		});
		response.setModelValidationRules(rules);
		return response;
	}

}
