package eu.ark.creditark.services.creditarkservices.enums;

public enum ReportSectionType {
    TABLE("TABLE"),
    LINEGRAPH("LINEGRAPH"),
    BARGRAPH("BARGRAPH");

    private String type;

    private ReportSectionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
