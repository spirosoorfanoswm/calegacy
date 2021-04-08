package eu.ark.creditark.services.creditarkservices.enums;

public enum ContentTypes {
    FILE_TYPE_2_CONTENT_TYPE("application/pdf");

    private String type;

    private ContentTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
