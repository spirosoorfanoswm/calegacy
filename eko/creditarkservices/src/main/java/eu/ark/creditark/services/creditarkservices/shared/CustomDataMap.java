package eu.ark.creditark.services.creditarkservices.shared;

import java.io.Serializable;

public class CustomDataMap implements Serializable {
	private static final long serialVersionUID = 8915708181986081779L;
	
	public static final int TYPE_AMOUNT = 1;
	public static final int TYPE_PERCENTAGE = 2;
	public static final int TYPE_INTEGER = 3;
	public static final int TYPE_DISCRETE = 9;
	
	
	private String description;
	private int dataType;
	private String[] discreteValues;
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public String[] getDiscreteValues() {
		return discreteValues;
	}

	public void setDiscreteValues(String[] discreteValues) {
		this.discreteValues = discreteValues;
	}

}
