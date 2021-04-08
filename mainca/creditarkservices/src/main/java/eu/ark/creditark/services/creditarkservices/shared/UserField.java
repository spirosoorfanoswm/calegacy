package eu.ark.creditark.services.creditarkservices.shared;

import java.util.LinkedHashMap;

public class UserField extends Field {
	private static final long serialVersionUID = 4482586085423122101L;
	
	private int maxLength;
	private LinkedHashMap<Integer, String> discreteValues;
	private String validationRule;
	private String validationMessage;
	private boolean readOnly;
	private String defaultValueFunc;

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public LinkedHashMap<Integer, String> getDiscreteValues() {
		return discreteValues;
	}

	public void setDiscreteValues(LinkedHashMap<Integer, String> discreteValues) {
		this.discreteValues = discreteValues;
	}

	public String getValidationRule() {
		return validationRule;
	}

	public void setValidationRule(String validationRule) {
		this.validationRule = validationRule;
	}

	public String getValidationMessage() {
		return validationMessage;
	}

	public void setValidationMessage(String validationMessage) {
		this.validationMessage = validationMessage;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}
	
	public String getDefaultValueFunc() {
		return this.defaultValueFunc;
	}
	
	public void setDefaultValueFunc(String defaultValueFunc) {
		this.defaultValueFunc = defaultValueFunc;
	}
}
