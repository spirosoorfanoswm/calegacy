package eu.ark.creditark.services.creditarkservices.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerDetailsSnapshotDto implements Serializable {
    private String behavioralScore;
    private String externalScore;
    private String qualitiveScore;
    private String casScore;
    private String customerName;
    private String groupName;
    private String customerSince;
    private String industryId;
    private String areaId;
    private String status;
    private String profitMargin;
    private String balance;
    private String limit;
    private KeyValuesDto idData;
    private KeyValuesDto businessData;
    private KeyValuesDto creditScoreData;
    private KeyValuesDto buckets;
    private KeyValuesDto mitigants;
    private List<KeyValueDto> exposures;
    private List<KeyValueDto> activities;

    public String getBehavioralScore() {
        return behavioralScore;
    }

    public void setBehavioralScore(String behavioralScore) {
        this.behavioralScore = behavioralScore;
    }

    public String getExternalScore() {
        return externalScore;
    }

    public void setExternalScore(String externalScore) {
        this.externalScore = externalScore;
    }

    public String getQualitiveScore() {
        return qualitiveScore;
    }

    public void setQualitiveScore(String qualitiveScore) {
        this.qualitiveScore = qualitiveScore;
    }

    public String getCasScore() {
        return casScore;
    }

    public void setCasScore(String casScore) {
        this.casScore = casScore;
    }

    public List<KeyValueDto> getExposures() {
        if(null== this.exposures) this.exposures = new ArrayList<>();
        return exposures;
    }

    public void setExposures(List<KeyValueDto> exposures) {
        this.exposures = exposures;
    }

    public List<KeyValueDto> getActivities() {
        if(null== this.activities) this.activities = new ArrayList<>();
        return activities;
    }

    public void setActivities(List<KeyValueDto> activities) {
        this.activities = activities;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCustomerSince() {
        return customerSince;
    }

    public void setCustomerSince(String customerSince) {
        this.customerSince = customerSince;
    }

    public String getIndustryId() {
        return industryId;
    }

    public void setIndustryId(String industryId) {
        this.industryId = industryId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProfitMargin() {
        return profitMargin;
    }

    public void setProfitMargin(String profitMargin) {
        this.profitMargin = profitMargin;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public KeyValuesDto getBuckets() {
        if(null == this.buckets)
            this.buckets = new KeyValuesDto();
        return buckets;
    }

    public void setBuckets(KeyValuesDto buckets) {
        this.buckets = buckets;
    }

    public KeyValuesDto getMitigants() {
        if(null == this.mitigants)
            this.mitigants = new KeyValuesDto();
        return mitigants;
    }

    public void setMitigants(KeyValuesDto mitigants) {
        this.mitigants = mitigants;
    }

    public KeyValuesDto getIdData() {
        if(null == this.idData)
            this.idData = new KeyValuesDto();
        return idData;
    }

    public void setIdData(KeyValuesDto idData) {
        this.idData = idData;
    }

    public KeyValuesDto getBusinessData() {
        if(null == this.businessData)
            this.businessData = new KeyValuesDto();
        return businessData;
    }

    public void setBusinessData(KeyValuesDto businessData) {
        this.businessData = businessData;
    }

    public KeyValuesDto getCreditScoreData() {
        if(null == this.creditScoreData)
            this.creditScoreData = new KeyValuesDto();
        return creditScoreData;
    }

    public void setCreditScoreData(KeyValuesDto creditScoreData) {
        this.creditScoreData = creditScoreData;
    }
}
