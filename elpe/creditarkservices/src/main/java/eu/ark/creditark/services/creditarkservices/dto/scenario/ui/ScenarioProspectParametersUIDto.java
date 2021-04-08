package eu.ark.creditark.services.creditarkservices.dto.scenario.ui;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Arrays;

@ApiModel(value="ScenarioProspectParametersUIDto", description="Contain prospect parameters of scenario")
public class ScenarioProspectParametersUIDto extends ScenarioUI implements Serializable {

    @ApiModelProperty(value = "The id of prospect", notes = "")
    private String prospectId;
    @ApiModelProperty(value = "Prospect description", notes = "")
    private String description;
    @ApiModelProperty(value = "Number of prospective customers", notes = "")
    private String customersNum;
    @ApiModelProperty(value = "Expected annual turnover per customer", notes = "")
    private String purchases;
    @ApiModelProperty(value = "Maximum accepted DSO (days)", notes = "")
    private String maxDso;
    @ApiModelProperty(value = "Prospective customers' risk level", notes = "id")
    private String gradeInx;
    @ApiModelProperty(value = "Prospective customers' risk level", notes = "value")
    private String grade;
    @ApiModelProperty(value = "Profit margin", notes = "")
    private String profitMargin;
    @ApiModelProperty(value = "Mitigants requested", notes = "ids")
    private String[] mitigants;
    @ApiModelProperty(value = "Mitigants requested", notes = "values")
    private String[] mitigantsValues;
    @ApiModelProperty(value = "Minimum accepted Risk Weighted Margin", notes = "")
    private String minAcceptedRwMargin;
    @ApiModelProperty(value = "Comments", notes = "N/A")
    private String comments;

    public ScenarioProspectParametersUIDto() {
    }

    public String[] getMitigantsValues() {
        return mitigantsValues;
    }

    public void setMitigantsValues(String[] mitigantsValues) {
        this.mitigantsValues = mitigantsValues;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getProspectId() {
        return prospectId;
    }

    public void setProspectId(String prospectId) {
        this.prospectId = prospectId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomersNum() {
        return customersNum;
    }

    public void setCustomersNum(String customersNum) {
        this.customersNum = customersNum;
    }

    public String getPurchases() {
        return purchases;
    }

    public void setPurchases(String purchases) {
        this.purchases = purchases;
    }

    public String getMaxDso() {
        return maxDso;
    }

    public void setMaxDso(String maxDso) {
        this.maxDso = maxDso;
    }

    public String getGradeInx() {
        return gradeInx;
    }

    public void setGradeInx(String gradeInx) {
        this.gradeInx = gradeInx;
    }

    public String getProfitMargin() {
        return profitMargin;
    }

    public void setProfitMargin(String profitMargin) {
        this.profitMargin = profitMargin;
    }

    public String[] getMitigants() {
        return mitigants;
    }

    public void setMitigants(String[] mitigants) {
        this.mitigants = mitigants;
    }

    public String getMinAcceptedRwMargin() {
        return minAcceptedRwMargin;
    }

    public void setMinAcceptedRwMargin(String minAcceptedRwMargin) {
        this.minAcceptedRwMargin = minAcceptedRwMargin;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ScenarioProspectParametersUIDto{");
        sb.append("prospectId='").append(prospectId).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", customersNum='").append(customersNum).append('\'');
        sb.append(", purchases='").append(purchases).append('\'');
        sb.append(", maxDso='").append(maxDso).append('\'');
        sb.append(", gradeInx='").append(gradeInx).append('\'');
        sb.append(", grade='").append(grade).append('\'');
        sb.append(", profitMargin='").append(profitMargin).append('\'');
        sb.append(", mitigants=").append(Arrays.toString(mitigants));
        sb.append(", mitigantsValues=").append(Arrays.toString(mitigantsValues));
        sb.append(", minAcceptedRwMargin='").append(minAcceptedRwMargin).append('\'');
        sb.append(", comments='").append(comments).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
