package eu.ark.creditark.services.creditarkservices.enums;

public enum LabelEnum {
	TOTAL("Total"),
	MITIGANTS("Mitigants")
	;
	private String value;
	
	private LabelEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
