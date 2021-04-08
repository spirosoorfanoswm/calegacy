package eu.ark.creditark.services.creditarkservices.dto;

import java.io.Serializable;

public class ScenarioThresholdsDto  implements Serializable {
    private ScenarioThresholdDto targetTotalCreditLimit;
    private ScenarioThresholdDto minimumAcceptableRiskWeightedMargin;
    private ScenarioThresholdDto salesChange;
    private ScenarioThresholdDto changeInProfitMargins;
    private ScenarioThresholdDto changeInDefaultProbabilities;
    private ScenarioThresholdDto maxDso;
    private ScenarioThresholdDto minimumAcceptedLimitUse;
    private ScenarioThresholdDto maximumLimitGrowth;
    private ScenarioThresholdDto maximumLimitReduction;
    private ScenarioThresholdDto minimumLimitGrowth;
    private ScenarioThresholdDto returnOnEquity;
    private ScenarioThresholdDto wacc;
    private ScenarioThresholdDto singn;
    private ScenarioThresholdDto minAcceptedLimitAmount;

    public ScenarioThresholdDto getTargetTotalCreditLimit() {
        return targetTotalCreditLimit;
    }

    public void setTargetTotalCreditLimit(ScenarioThresholdDto targetTotalCreditLimit) {
        this.targetTotalCreditLimit = targetTotalCreditLimit;
    }

    public ScenarioThresholdDto getMinimumAcceptableRiskWeightedMargin() {
        return minimumAcceptableRiskWeightedMargin;
    }

    public void setMinimumAcceptableRiskWeightedMargin(ScenarioThresholdDto minimumAcceptableRiskWeightedMargin) {
        this.minimumAcceptableRiskWeightedMargin = minimumAcceptableRiskWeightedMargin;
    }

    public ScenarioThresholdDto getSalesChange() {
        return salesChange;
    }

    public void setSalesChange(ScenarioThresholdDto salesChange) {
        this.salesChange = salesChange;
    }

    public ScenarioThresholdDto getChangeInProfitMargins() {
        return changeInProfitMargins;
    }

    public void setChangeInProfitMargins(ScenarioThresholdDto changeInProfitMargins) {
        this.changeInProfitMargins = changeInProfitMargins;
    }

    public ScenarioThresholdDto getChangeInDefaultProbabilities() {
        return changeInDefaultProbabilities;
    }

    public void setChangeInDefaultProbabilities(ScenarioThresholdDto changeInDefaultProbabilities) {
        this.changeInDefaultProbabilities = changeInDefaultProbabilities;
    }

    public ScenarioThresholdDto getMaxDso() {
        return maxDso;
    }

    public void setMaxDso(ScenarioThresholdDto maxDso) {
        this.maxDso = maxDso;
    }

    public ScenarioThresholdDto getMinimumAcceptedLimitUse() {
        return minimumAcceptedLimitUse;
    }

    public void setMinimumAcceptedLimitUse(ScenarioThresholdDto minimumAcceptedLimitUse) {
        this.minimumAcceptedLimitUse = minimumAcceptedLimitUse;
    }

    public ScenarioThresholdDto getMaximumLimitGrowth() {
        return maximumLimitGrowth;
    }

    public void setMaximumLimitGrowth(ScenarioThresholdDto maximumLimitGrowth) {
        this.maximumLimitGrowth = maximumLimitGrowth;
    }

    public ScenarioThresholdDto getMaximumLimitReduction() {
        return maximumLimitReduction;
    }

    public void setMaximumLimitReduction(ScenarioThresholdDto maximumLimitReduction) {
        this.maximumLimitReduction = maximumLimitReduction;
    }

    public ScenarioThresholdDto getMinimumLimitGrowth() {
        return minimumLimitGrowth;
    }

    public void setMinimumLimitGrowth(ScenarioThresholdDto minimumLimitGrowth) {
        this.minimumLimitGrowth = minimumLimitGrowth;
    }

    public ScenarioThresholdDto getReturnOnEquity() {
        return returnOnEquity;
    }

    public void setReturnOnEquity(ScenarioThresholdDto returnOnEquity) {
        this.returnOnEquity = returnOnEquity;
    }

    public ScenarioThresholdDto getWacc() {
        return wacc;
    }

    public void setWacc(ScenarioThresholdDto wacc) {
        this.wacc = wacc;
    }

    public ScenarioThresholdDto getSingn() {
        return singn;
    }

    public void setSingn(ScenarioThresholdDto singn) {
        this.singn = singn;
    }

    public ScenarioThresholdDto getMinAcceptedLimitAmount() {
        return minAcceptedLimitAmount;
    }

    public void setMinAcceptedLimitAmount(ScenarioThresholdDto minAcceptedLimitAmount) {
        this.minAcceptedLimitAmount = minAcceptedLimitAmount;
    }
}
