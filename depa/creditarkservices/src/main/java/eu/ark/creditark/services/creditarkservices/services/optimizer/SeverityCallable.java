package eu.ark.creditark.services.creditarkservices.services.optimizer;

import eu.ark.creditark.services.creditarkservices.optimizer.LimitsOptimizer;
import eu.ark.creditark.services.creditarkservices.shared.CustomerInfo;

import java.util.concurrent.Callable;

public class SeverityCallable implements Callable<Double> {
    private CustomerInfo cust;
    private LimitsOptimizer limitsOptimizer;

    public SeverityCallable(CustomerInfo cust, LimitsOptimizer limitsOptimizer) {
        this.cust = cust;
        this.limitsOptimizer = limitsOptimizer;
    }

    @Override
    public Double call() throws Exception {
        double severity = this.cust.meanBalance * getWeightedLgd(this.cust.meanBalance, this.cust.creditMitigants);
        return cust.num * cust.pd * severity * severity;
    }

    private double getWeightedLgd(double exposure, double[] mitigants) {
        if (exposure <= 0) {
            double minLgd = 1;
            for (int i = 0; i < this.limitsOptimizer.getMitSortOrder().length; i++) {
                if (this.limitsOptimizer.getMitSortOrder()[i] == 0 || mitigants[this.limitsOptimizer.getMitSortOrder()[i] - 1] > 0)
                    minLgd = Math.min(minLgd, this.limitsOptimizer.getPar().mitLgds[this.limitsOptimizer.getMitSortOrder()[i]]);
            }
            return minLgd;
        }

        // Get uncovered limit and weighted average lgd
        double uncovered = exposure;
        double loss = 0;
        for (int i = 0; i < this.limitsOptimizer.getMitSortOrder().length; i++) {
            if (this.limitsOptimizer.getMitSortOrder()[i] == 0) continue; // If uncovered then do not take into account
            double cover = mitigants[this.limitsOptimizer.getMitSortOrder()[i] - 1];
            if (uncovered >= cover) {
                loss += cover * this.limitsOptimizer.getPar().mitLgds[this.limitsOptimizer.getMitSortOrder()[i]];
                uncovered -= cover;
            } else {
                loss += uncovered * this.limitsOptimizer.getPar().mitLgds[this.limitsOptimizer.getMitSortOrder()[i]];
                uncovered = 0;
                break;
            }
        }
        if (uncovered > 0) loss += uncovered * this.limitsOptimizer.getPar().mitLgds[0];
        return loss / exposure;
    }
}
