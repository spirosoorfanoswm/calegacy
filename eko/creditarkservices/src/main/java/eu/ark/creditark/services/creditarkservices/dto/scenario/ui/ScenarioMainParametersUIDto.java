package eu.ark.creditark.services.creditarkservices.dto.scenario.ui;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value="ScenarioMainParametersUIDto", description="Contain main parameters of scenario")
public class ScenarioMainParametersUIDto  extends ScenarioUI  implements Serializable {
    @ApiModelProperty(value = "Maximum limit growth", notes = "in percentage")
    private String maxLimitGrowth;
    @ApiModelProperty(value = "Maximum limit reduction", notes = "in percentage")
    private String maxLimitReduction;
    @ApiModelProperty(value = "Minimum limit growth", notes = "in percentage")
    private String minLimitGrowth;
    @ApiModelProperty(value = "Minimum limit reduction", notes = "in percentage")
    private String minLimitReduction;
    @ApiModelProperty(value = "Minimum accepted limit use", notes = "in percentage")
    private String minAcceptedLimitUse;
    @ApiModelProperty(value = "Maximum accepted DSO (days)", notes = "")
    private String maxDso;
    @ApiModelProperty(value = "Minimum acceptable Risk Weighted Margin", notes = "in percentage")
    private String minAcceptedRwMargin;
    @ApiModelProperty(value = "Sales change", notes = "in percentage")
    private String salesChange;
    @ApiModelProperty(value = "Change in profit margins", notes = "in percentage")
    private String marginChange;
    @ApiModelProperty(value = "Change in default probabilities", notes = "in percentage")
    private String pdChange;
    @ApiModelProperty(value = "Worst acceptable CAS", notes = "taken from scores")
    private String worstAcceptableScore;
    @ApiModelProperty(value = "userComments", notes = "N/A")
    private String userComments;
    @ApiModelProperty(value = "WACC (Weighted Average Cost of Capital)", notes = "in percentage")
    private String wacc;
    @ApiModelProperty(value = "Intended Return on Equity", notes = "in percentage")
    private String raroc;
    @ApiModelProperty(value = "Target total credit limit", notes = "")
    private String creditAmount;
    @ApiModelProperty(value = "Number of limit's significant digits", notes = "")
    private String significantDigits;
    @ApiModelProperty(value = "Minimum acceptable limit amount", notes = "")
    private String minLimit;
    @ApiModelProperty(value = "Minimum accepted limit over annual turnover", notes = "in percentage")
    private String minLimitPct;

    public ScenarioMainParametersUIDto() {
    }

    public String getMaxLimitGrowth() {
        return maxLimitGrowth;
    }

    public void setMaxLimitGrowth(String maxLimitGrowth) {
        this.maxLimitGrowth = maxLimitGrowth;
    }

    public String getMaxLimitReduction() {
        return maxLimitReduction;
    }

    public void setMaxLimitReduction(String maxLimitReduction) {
        this.maxLimitReduction = maxLimitReduction;
    }

    public String getMinLimitGrowth() {
        return minLimitGrowth;
    }

    public void setMinLimitGrowth(String minLimitGrowth) {
        this.minLimitGrowth = minLimitGrowth;
    }

    public String getMinLimitReduction() {
        return minLimitReduction;
    }

    public void setMinLimitReduction(String minLimitReduction) {
        this.minLimitReduction = minLimitReduction;
    }

    public String getMinAcceptedLimitUse() {
        return minAcceptedLimitUse;
    }

    public void setMinAcceptedLimitUse(String minAcceptedLimitUse) {
        this.minAcceptedLimitUse = minAcceptedLimitUse;
    }

    public String getMaxDso() {
        return maxDso;
    }

    public void setMaxDso(String maxDso) {
        this.maxDso = maxDso;
    }

    public String getMinAcceptedRwMargin() {
        return minAcceptedRwMargin;
    }

    public void setMinAcceptedRwMargin(String minAcceptedRwMargin) {
        this.minAcceptedRwMargin = minAcceptedRwMargin;
    }

    public String getSalesChange() {
        return salesChange;
    }

    public void setSalesChange(String salesChange) {
        this.salesChange = salesChange;
    }

    public String getMarginChange() {
        return marginChange;
    }

    public void setMarginChange(String marginChange) {
        this.marginChange = marginChange;
    }

    public String getPdChange() {
        return pdChange;
    }

    public void setPdChange(String pdChange) {
        this.pdChange = pdChange;
    }

    public String getWorstAcceptableScore() {
        return worstAcceptableScore;
    }

    public void setWorstAcceptableScore(String worstAcceptableScore) {
        this.worstAcceptableScore = worstAcceptableScore;
    }

    public String getUserComments() {
        return userComments;
    }

    public void setUserComments(String userComments) {
        this.userComments = userComments;
    }

    public String getWacc() {
        return wacc;
    }

    public void setWacc(String wacc) {
        this.wacc = wacc;
    }

    public String getRaroc() {
        return raroc;
    }

    public void setRaroc(String raroc) {
        this.raroc = raroc;
    }

    public String getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(String creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getSignificantDigits() {
        return significantDigits;
    }

    public void setSignificantDigits(String significantDigits) {
        this.significantDigits = significantDigits;
    }

    public String getMinLimit() {
        return minLimit;
    }

    public void setMinLimit(String minLimit) {
        this.minLimit = minLimit;
    }

    public String getMinLimitPct() {
        return minLimitPct;
    }

    public void setMinLimitPct(String minLimitPct) {
        this.minLimitPct = minLimitPct;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ScenarioMainParametersUIDto{");
        sb.append("maxLimitGrowth='").append(maxLimitGrowth).append('\'');
        sb.append(", maxLimitReduction='").append(maxLimitReduction).append('\'');
        sb.append(", minLimitGrowth='").append(minLimitGrowth).append('\'');
        sb.append(", minLimitReduction='").append(minLimitReduction).append('\'');
        sb.append(", minAcceptedLimitUse='").append(minAcceptedLimitUse).append('\'');
        sb.append(", maxDso='").append(maxDso).append('\'');
        sb.append(", minAcceptedRwMargin='").append(minAcceptedRwMargin).append('\'');
        sb.append(", salesChange='").append(salesChange).append('\'');
        sb.append(", marginChange='").append(marginChange).append('\'');
        sb.append(", pdChange='").append(pdChange).append('\'');
        sb.append(", worstAcceptableScore='").append(worstAcceptableScore).append('\'');
        sb.append(", userComments='").append(userComments).append('\'');
        sb.append(", wacc='").append(wacc).append('\'');
        sb.append(", raroc='").append(raroc).append('\'');
        sb.append(", creditAmount='").append(creditAmount).append('\'');
        sb.append(", significantDigits='").append(significantDigits).append('\'');
        sb.append(", minLimit='").append(minLimit).append('\'');
        sb.append(", minLimitPct='").append(minLimitPct).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
