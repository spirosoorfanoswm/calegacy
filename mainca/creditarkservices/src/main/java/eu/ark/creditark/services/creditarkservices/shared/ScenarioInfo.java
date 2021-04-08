package eu.ark.creditark.services.creditarkservices.shared;

import java.io.Serializable;

public class ScenarioInfo implements Serializable {
    public CustomerInfo[] customersInfo;
    public double creditAmount;
    public double wacc;
    public double raroc;
    public int significantDigits;
    public double minLimit;
    public double minLimitPct;


}
