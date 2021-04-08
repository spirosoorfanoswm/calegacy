package eu.ark.creditark.services.creditarkservices.dto.reports;

public class BarDatasourceDataItem {
    private String id;
    private Double[] value;
    private String[] stringValues;

    public BarDatasourceDataItem(String id, Double[] value) {
        this.id = id;
        this.value = value;
    }

    public BarDatasourceDataItem(String id, String[] stringValues) {
        this.id = id;
        this.stringValues = stringValues;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double[] getValue() {
        return value;
    }

    public void setValue(Double[] value) {
        this.value = value;
    }

    public String[] getStringValues() {
        return stringValues;
    }

    public void setStringValues(String[] stringValues) {
        this.stringValues = stringValues;
    }
}
