package eu.ark.creditark.services.creditarkservices.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CustomerScenarioStatisticsDto implements Serializable {

    private KeyValuesDto data;

    public KeyValuesDto getData() {
        if(null == data) this.data = new KeyValuesDto();
        return data;
    }

    public void setData(KeyValuesDto data) {
        this.data = data;
    }
}
