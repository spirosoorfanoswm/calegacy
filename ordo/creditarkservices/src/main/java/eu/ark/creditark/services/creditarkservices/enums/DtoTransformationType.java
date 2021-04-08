package eu.ark.creditark.services.creditarkservices.enums;

public enum DtoTransformationType {
	CLIENT_STATISTICS_GRAPH("CLIENT_STATISTICS_GRAPH"),
	CLIENT_STATISTICS_VIEW("CLIENT_STATISTICS_VIEW"),
	CLIENT_DISTRIBUTION_VIEW("CLIENT_DISTRIBUTION_VIEW"),
	CUSTOMER_VIEW("CUSTOMER_VIEW"),
    CUSTOMER_ENTITY("CUSTOMER_ENTITY"),
	SCENARIOS("SCENARIOS"),
    SCENARIOS_MAIN_PARAMETERS("SCENARIOS_MAIN_PARAMETERS"),
    SCENARIOS_TO_BUS("SCENARIOS_TO_BUS"),
    SCENARIOS_TEMPLATE("SCENARIOS_TEMPLATE"),
    SCENARIOS_STATISTICS_TO_UI_STATISTICS("SCENARIOS_STATISTICS_TO_UI_STATISTICS"),
    SCENARIOS_MAIN_PARAMS_TRANSFORMATION("SCENARIOS_MAIN_PARAMS_TRANSFORMATION"),
    SCENARIOS_MAIN_PARAMS_VALIDATION("SCENARIOS_MAIN_PARAMS_VALIDATION"),
    SCENARIOS_MAIN_PROSPECT_VALIDATION("SCENARIOS_MAIN_PROSPECT_VALIDATION"),
    SCENARIOS_MAIN_CUSTOMER_VALIDATION("SCENARIOS_MAIN_CUSTOMER_VALIDATION"),
    SCENARIOS_MAIN_PORTFOLIO_VALIDATION("SCENARIOS_MAIN_PORTFOLIO_VALIDATION"),
    SCENARIOS_MAIN_PARAMS_BUSINESS_VALIDATION("SCENARIOS_MAIN_PARAMS_BUSINESS_VALIDATION"),
    SCENARIOS_MAIN_PROSPECT_BUSINESS_VALIDATION("SCENARIOS_MAIN_PROSPECT_BUSINESS_VALIDATION"),
    SCENARIOS_MAIN_CUSTOMER_BUSINESS_VALIDATION("SCENARIOS_MAIN_CUSTOMER_BUSINESS_VALIDATION"),
    SCENARIOS_MAIN_PORTFOLIO_BUSINESS_VALIDATION("SCENARIOS_MAIN_PORTFOLIO_BUSINESS_VALIDATION"),
    VALIDATION_RULE_LIST("VALIDATION_RULE_LIST"),
    CLIENT_DISTRIBUTION_REPORT("CLIENT_DISTRIBUTION_REPORT"),
    CLIENT_STATISTICS_REPORT("CLIENT_STATISTICS_REPORT"),
    CUSTOMER_DETAILS_REPORT("CUSTOMER_DETAILS_REPORT");
    private String id;

    private DtoTransformationType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id;
    }
}
