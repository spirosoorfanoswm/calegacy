package eu.ark.creditark.services.creditarkservices.config;

import eu.ark.creditark.services.creditarkservices.services.transformator.clientele.ClienteleTransformatorFactory;
import eu.ark.creditark.services.creditarkservices.services.transformator.scenarios.ScenarioStatisticsToUIStatisticsTransformatorFactory;
import eu.ark.creditark.services.creditarkservices.services.transformator.scenarios.ScenarioTransformatorFactory;
import eu.ark.creditark.services.creditarkservices.services.transformator.scenarios.ScenarioUIToBusTransformatorFactory;
import eu.ark.creditark.services.creditarkservices.services.transformator.scenarios.ScenarioUITransformatorFactory;
import eu.ark.creditark.services.creditarkservices.services.validation.BusinessValidatorFactory;
import eu.ark.creditark.services.creditarkservices.services.validation.ValidatorFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorConfig {

    @SuppressWarnings("rawtypes")
	@Bean("validatorFactory")
    public FactoryBean validatorLocatorFactoryBean() {
        ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
        factoryBean.setServiceLocatorInterface(ValidatorFactory.class);
        return factoryBean;
    }


    @Bean("businessValidatorFactory")
    public FactoryBean businessValidatorFactoryBean() {
        ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
        factoryBean.setServiceLocatorInterface(BusinessValidatorFactory.class);
        return factoryBean;
    }
    

}
