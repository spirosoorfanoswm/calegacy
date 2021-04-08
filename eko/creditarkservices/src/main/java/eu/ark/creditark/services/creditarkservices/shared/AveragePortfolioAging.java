package eu.ark.creditark.services.creditarkservices.shared;

import java.io.Serializable;

public class AveragePortfolioAging implements Serializable  {

    private double value;

    public AveragePortfolioAging(double value) {
        this.value = value;
    }

    public AveragePortfolioAging() {
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
