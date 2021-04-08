package eu.ark.creditark.services.creditarkservices.dto.scenario.ui;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value="ScenarioPortfolioParametersUIDto", description="Contain portfolio parameters of scenario")
public class ScenarioPortfolioParametersUIDto extends ScenarioUI  implements Serializable  {

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

    @ApiModelProperty(value = "Maximum limit growth", notes = "in percentage")
    private String maxLimitGrowthCli;
    @ApiModelProperty(value = "Maximum limit reduction", notes = "in percentage")
    private String maxLimitReductionCli;
    @ApiModelProperty(value = "Minimum limit growth", notes = "in percentage")
    private String minLimitGrowthCli;
    @ApiModelProperty(value = "Minimum limit reduction", notes = "in percentage")
    private String minLimitReductionCli;
    @ApiModelProperty(value = "Minimum accepted limit use", notes = "in percentage")
    private String minAcceptedLimitUseCli;
    @ApiModelProperty(value = "Maximum accepted DSO (days)", notes = "")
    private String maxDsoCli;
    @ApiModelProperty(value = "Minimum acceptable Risk Weighted Margin", notes = "in percentage")
    private String minAcceptedRwMarginCli;
    @ApiModelProperty(value = "Sales change", notes = "in percentage")
    private String salesChangeCli;
    @ApiModelProperty(value = "Change in profit margins", notes = "in percentage")
    private String marginChangeCli;
    @ApiModelProperty(value = "Change in default probabilities", notes = "in percentage")
    private String pdChangeCli;
    @ApiModelProperty(value = "Worst acceptable CAS", notes = "taken from scores")
    private String worstAcceptableScoreCli;
    @ApiModelProperty(value = "userComments", notes = "N/A")
    private String userCommentsCli;

    public ScenarioPortfolioParametersUIDto() {
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

    public String getWorstAcceptableScoreCli() {
        return worstAcceptableScoreCli;
    }

    public void setWorstAcceptableScoreCli(String worstAcceptableScoreCli) {
        this.worstAcceptableScoreCli = worstAcceptableScoreCli;
    }

    public String getUserCommentsCli() {
        return userCommentsCli;
    }

    public void setUserCommentsCli(String userCommentsCli) {
        this.userCommentsCli = userCommentsCli;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ScenarioPortfolioParametersUIDto{");
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
        sb.append(", worstAcceptableScoreCli='").append(worstAcceptableScoreCli).append('\'');
        sb.append(", userCommentsCli='").append(userCommentsCli).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
