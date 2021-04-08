package eu.ark.creditark.services.creditarkservices.shared;

import java.io.Serializable;

public class Field implements Serializable {
	private static final long serialVersionUID = -8273189558431925928L;

	public static final int TYPE_STRING = 11;
	public static final int TYPE_AMOUNT = 12;
	public static final int TYPE_PERCENTAGE = 13;
	public static final int TYPE_INTEGER = 14;
	public static final int TYPE_DATE = 17;
	public static final int TYPE_DISCRETE = 19;
	
	private String caption;
	private String description;
	private int fieldType;
	
	public String getCaption() {
		return caption;
	}
	
	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getFieldType() {
		return fieldType;
	}

	public void setFieldType(int fieldType) {
		this.fieldType = fieldType;
	}
}
