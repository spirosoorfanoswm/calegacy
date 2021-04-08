package eu.ark.creditark.services.creditarkservices.dto;

import java.util.List;

public class CustomerDetailsIFRSDto {
    private String lifePd;
    private String provisions;
    private String newProvisions;
    private String difference;
    private List<KeyValueMultipleDto> values;

    public String getLifePd() {
        return lifePd;
    }

    public void setLifePd(String lifePd) {
        this.lifePd = lifePd;
    }

    public String getProvisions() {
        return provisions;
    }

    public void setProvisions(String provisions) {
        this.provisions = provisions;
    }

    public String getNewProvisions() {
        return newProvisions;
    }

    public void setNewProvisions(String newProvisions) {
        this.newProvisions = newProvisions;
    }

    public String getDifference() {
        return difference;
    }

    public void setDifference(String difference) {
        this.difference = difference;
    }

    public List<KeyValueMultipleDto> getValues() {
        return values;
    }

    public void setValues(List<KeyValueMultipleDto> values) {
        this.values = values;
    }
    //private

}
