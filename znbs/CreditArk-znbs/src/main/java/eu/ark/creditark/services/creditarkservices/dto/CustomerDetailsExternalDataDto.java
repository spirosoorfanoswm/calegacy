package eu.ark.creditark.services.creditarkservices.dto;

import java.io.Serializable;

public class CustomerDetailsExternalDataDto implements Serializable {
    private String dateOfAssesment;
    private String score;
    private KeyValuesDto data;

    public CustomerDetailsExternalDataDto() {
    }

    public String getDateOfAssesment() {
        return dateOfAssesment;
    }

    public void setDateOfAssesment(String dateOfAssesment) {
        this.dateOfAssesment = dateOfAssesment;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public KeyValuesDto getData() {
        return data;
    }

    public void setData(KeyValuesDto data) {
        this.data = data;
    }
}
