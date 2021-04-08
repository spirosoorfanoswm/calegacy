package eu.ark.creditark.services.creditarkservices.dto;

import java.io.Serializable;

public class ScenarioThresholdDto implements Serializable {
    private double min;
    private double max;

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }
}
