package eu.ark.creditark.services.creditarkservices.enums;


public enum GenerealConstants {
	
	DATE_FORMAT("yyyy-MM-dd"), 
	TIME_FORMAT("HH:mm:ss"),
	DATE_FORMAT_DD_MM_YYYY("dd-MM-yyyy")
	;
	private String value;
	
	private GenerealConstants(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return getValue();
	}
}

