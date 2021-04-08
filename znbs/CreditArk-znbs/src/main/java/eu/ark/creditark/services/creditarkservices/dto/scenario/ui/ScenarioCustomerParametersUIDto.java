package eu.ark.creditark.services.creditarkservices.dto.scenario.ui;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
@ApiModel(value="ScenarioCustomerParametersUIDto", description="Contain customer of scenario")
public class ScenarioCustomerParametersUIDto extends ScenarioUI implements Serializable {

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
    @ApiModelProperty(value = "Minimum accepted RW Margin", notes = "in percentage")
    private String minAcceptedRwMargin;
    @ApiModelProperty(value = "Sales change", notes = "in percentage")
    private String salesChange;
    @ApiModelProperty(value = "Change in profit margins", notes = "in percentage")
    private String marginChange;
    @ApiModelProperty(value = "Change in default probabilities", notes = "in percentage")
    private String pdChange;


    @ApiModelProperty(value = "Maximum limit growth", notes = "in percentage on portfolio")
    private String maxLimitGrowthCli;
    @ApiModelProperty(value = "Maximum limit reduction", notes = "in percentage  on portfolio")
    private String maxLimitReductionCli;
    @ApiModelProperty(value = "Minimum limit growth", notes = "in percentage  on portfolio")
    private String minLimitGrowthCli;
    @ApiModelProperty(value = "Minimum limit reduction", notes = "in percentage on portfolio")
    private String minLimitReductionCli;
    @ApiModelProperty(value = "Minimum accepted limit use", notes = "in percentage on portfolio")
    private String minAcceptedLimitUseCli;
    @ApiModelProperty(value = "Maximum accepted DSO (days)", notes = "on portfolio")
    private String maxDsoCli;
    @ApiModelProperty(value = "Minimum accepted RW Margin", notes = "in percentage on portfolio")
    private String minAcceptedRwMarginCli;
    @ApiModelProperty(value = "Sales change", notes = "in percentage on portfolio")
    private String salesChangeCli;
    @ApiModelProperty(value = "Change in profit margins", notes = "in percentage on portfolio")
    private String marginChangeCli;
    @ApiModelProperty(value = "Change in default probabilities", notes = "in percentage on portfolio")
    private String pdChangeCli;

    @ApiModelProperty(value = "worst Acceptable Score")
    private String worstAcceptableScore;

    public ScenarioCustomerParametersUIDto() {
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

    public String getMaxLimitGrowthCli() {
        return maxLimitGrowthCli;
    }

    public void setMaxLimitGrowthCli(String maxLimitGrowthCli) {
        this.maxLimitGrowthCli = maxLimitGrowthCli;
    }

    public String getMaxLimitReductionCli() {
        return maxLimitReductionCli;
    }

    public void setMaxLimitReductionCli(String maxLimitReductionCli) {
        this.maxLimitReductionCli = maxLimitReductionCli;
    }

    public String getMinLimitGrowthCli() {
        return minLimitGrowthCli;
    }

    public void setMinLimitGrowthCli(String minLimitGrowthCli) {
        this.minLimitGrowthCli = minLimitGrowthCli;
    }

    public String getMinLimitReductionCli() {
        return minLimitReductionCli;
    }

    public void setMinLimitReductionCli(String minLimitReductionCli) {
        this.minLimitReductionCli = minLimitReductionCli;
    }

    public String getMinAcceptedLimitUseCli() {
        return minAcceptedLimitUseCli;
    }

    public void setMinAcceptedLimitUseCli(String minAcceptedLimitUseCli) {
        this.minAcceptedLimitUseCli = minAcceptedLimitUseCli;
    }

    public String getMaxDsoCli() {
        return maxDsoCli;
    }

    public void setMaxDsoCli(String maxDsoCli) {
        this.maxDsoCli = maxDsoCli;
    }

    public String getMinAcceptedRwMarginCli() {
        return minAcceptedRwMarginCli;
    }

    public void setMinAcceptedRwMarginCli(String minAcceptedRwMarginCli) {
        this.minAcceptedRwMarginCli = minAcceptedRwMarginCli;
    }

    public String getSalesChangeCli() {
        return salesChangeCli;
    }

    public void setSalesChangeCli(String salesChangeCli) {
        this.salesChangeCli = salesChangeCli;
    }

    public String getMarginChangeCli() {
        return marginChangeCli;
    }

    public void setMarginChangeCli(String marginChangeCli) {
        this.marginChangeCli = marginChangeCli;
    }

    public String getPdChangeCli() {
        return pdChangeCli;
    }

    public void setPdChangeCli(String pdChangeCli) {
        this.pdChangeCli = pdChangeCli;
    }

    public String getWorstAcceptableScore() {
        return worstAcceptableScore;
    }

    public void setWorstAcceptableScore(String worstAcceptableScore) {
        this.worstAcceptableScore = worstAcceptableScore;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ScenarioCustomerParametersUIDto{");
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
        sb.append(", maxLimitGrowthCli='").append(maxLimitGrowthCli).append('\'');
        sb.append(", maxLimitReductionCli='").append(maxLimitReductionCli).append('\'');
        sb.append(", minLimitGrowthCli='").append(minLimitGrowthCli).append('\'');
        sb.append(", minLimitReductionCli='").append(minLimitReductionCli).append('\'');
        sb.append(", minAcceptedLimitUseCli='").append(minAcceptedLimitUseCli).append('\'');
        sb.append(", maxDsoCli='").append(maxDsoCli).append('\'');
        sb.append(", minAcceptedRwMarginCli='").append(minAcceptedRwMarginCli).append('\'');
        sb.append(", salesChangeCli='").append(salesChangeCli).append('\'');
        sb.append(", marginChangeCli='").append(marginChangeCli).append('\'');
        sb.append(", pdChangeCli='").append(pdChangeCli).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
