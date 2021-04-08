package eu.ark.creditark.services.creditarkservices.config;

import eu.ark.creditark.services.creditarkservices.services.transformator.modelvalidation.ValidationRuleTransformatorFactory;
import eu.ark.creditark.services.creditarkservices.services.transformator.scenarios.ScenarioStatisticsToUIStatisticsTransformatorFactory;
import eu.ark.creditark.services.creditarkservices.services.transformator.scenarios.ScenarioUIToBusTransformatorFactory;
import eu.ark.creditark.services.creditarkservices.services.transformator.scenarios.ScenarioUITransformatorFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import eu.ark.creditark.services.creditarkservices.services.transformator.clientele.ClienteleTransformatorFactory;
import eu.ark.creditark.services.creditarkservices.services.transformator.scenarios.ScenarioTransformatorFactory;

@Configuration
public class TransformatorConfig {

    /** clienteleTransformatorFactory.
     *
     * @return the bean
     */
    @SuppressWarnings("rawtypes")
	@Bean("clienteleTransformatorFactory")
    public FactoryBean clienteleTransformatorLocatorFactoryBean() {
        ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
        factoryBean.setServiceLocatorInterface(ClienteleTransformatorFactory.class);
        return factoryBean;
    }
    
    @SuppressWarnings("rawtypes")
	@Bean("scenarioTransformatorFactory")
    public FactoryBean scenarioTransformatorLocatorFactoryBean() {
        ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
        factoryBean.setServiceLocatorInterface(ScenarioTransformatorFactory.class);
        return factoryBean;
    }

    @Bean("scenarioUITransformatorFactory")
    public FactoryBean scenarioUITransformatorLocatorFactoryBean() {
        ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
        factoryBean.setServiceLocatorInterface(ScenarioUITransformatorFactory.class);
        return factoryBean;
    }

    @Bean("scenarioUIToBusTransformatorFactory")
    public FactoryBean scenarioUIToBusTransformatorFactoryBean() {
        ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
        factoryBean.setServiceLocatorInterface(ScenarioUIToBusTransformatorFactory.class);
        return factoryBean;
    }

    @Bean("scenarioStatisticsToUIStatisticsTransformatorFactoryFactoryBean")
    public FactoryBean ScenarioStatisticsToUIStatisticsTransformatorFactoryFactoryBean() {
        ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
        factoryBean.setServiceLocatorInterface(ScenarioStatisticsToUIStatisticsTransformatorFactory.class);
        return factoryBean;
    }


    @SuppressWarnings("rawtypes")
    @Bean("validationRuleTransformatorFactory")
    public FactoryBean validationRuleTransformatorFactoryBean() {
        ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
        factoryBean.setServiceLocatorInterface(ValidationRuleTransformatorFactory.class);
        return factoryBean;
    }
}
