package eu.ark.creditark.services.creditarkservices.enums;

public enum DtoValueDataType {
	TYPE_AMOUNT      (1, "TYPE_AMOUNT"),
	TYPE_PERCENTAGE (2, "TYPE_PERCENTAGE"),
	TYPE_INTEGER    (3, "TYPE_INTEGER"),
	TYPE_DISCRETE   (9, "TYPE_DISCRETE");
	
	private int id;
	private String type;
	private DtoValueDataType(int id, String type) {
		this.id = id;
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public String getType() {
		return type;
	}
	public static DtoValueDataType getById(int id) {
		for(DtoValueDataType value:values())
			if(value.id == id)
				return value;
		return null;
	}

}
