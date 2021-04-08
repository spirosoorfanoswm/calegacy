package eu.ark.creditark.services.creditarkservices.services.optimizer;

import eu.ark.creditark.services.creditarkservices.exceptions.ScenarioException;
import eu.ark.creditark.services.creditarkservices.optimizer.LimitsOptimizer;

public interface OptimizerService {

    public void calcPortfolio(LimitsOptimizer limitsOptimizer) throws ScenarioException, CloneNotSupportedException, InterruptedException;
}
