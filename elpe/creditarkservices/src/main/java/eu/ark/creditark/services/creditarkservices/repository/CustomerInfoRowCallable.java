package eu.ark.creditark.services.creditarkservices.repository;

import eu.ark.creditark.services.creditarkservices.optimizer.LimitsOptimizer;
import eu.ark.creditark.services.creditarkservices.shared.CustomerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;

public class CustomerInfoRowCallable implements Callable<CustomerInfo> {
    private ResultSet rows;
    private LimitsOptimizer limitsOptimizer;
    private double portfolioUl;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public CustomerInfoRowCallable(ResultSet rows) {
        this.rows = rows;
    }

    @Override
    public CustomerInfo call() throws Exception {
        logger.info("Excecute");
        CustomerInfo c = new CustomerInfo();
        c.customerId = rows.getString(1);
        c.turnover = rows.getDouble(2);
        c.projTurnover = rows.getDouble(3);
        c.creditLimit = rows.getDouble(4);
        c.meanLimit = rows.getDouble(5);
        c.meanBalance = rows.getDouble(6);
        c.maxBalance = rows.getDouble(7);
        c.maxPurchases = rows.getDouble(8);
        c.pd = rows.getDouble(9);
        c.profitMargin = rows.getDouble(10);
        c.creditMitigants = getDoubleArray(rows, 11);
        c.maxLimitGrowth = rows.getDouble(12);
        c.maxLimitReduction = rows.getDouble(13);
        c.minLimitGrowth = rows.getDouble(14);
        c.minLimitReduction = rows.getDouble(15);
        c.worstAcceptedPd = rows.getDouble(16);
        c.maxAcceptedDso = rows.getInt(17);
        c.minAcceptedLimitUse = rows.getDouble(18);
        c.minAcceptedRwMargin = rows.getDouble(19);
        return c;
    }

    private double[] getDoubleArray(ResultSet rows, int parameterIndex) throws SQLException {
        Double[] d = (Double[])rows.getArray(parameterIndex).getArray();
        double[] r = new double[d.length];
        for (int i = 0; i < d.length; i++)
            r[i] = d[i].doubleValue();
        return r;
    }

}
