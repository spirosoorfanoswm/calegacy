package eu.ark.creditark.services.creditarkservices.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CustomerDetailsStatisticsDto implements Serializable {
    private List<KeyValueMultipleDto> statistics;

    public List<KeyValueMultipleDto> getStatistics() {
        if(null == this.statistics) this.statistics = new ArrayList<>();
        return statistics;
    }

    public void setStatistics(List<KeyValueMultipleDto> statistics) {
        this.statistics = statistics;
    }
}
