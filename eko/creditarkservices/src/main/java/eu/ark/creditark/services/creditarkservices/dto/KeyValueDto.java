package eu.ark.creditark.services.creditarkservices.dto;

import java.io.Serializable;

import eu.ark.creditark.services.creditarkservices.enums.DtoValueDataType;
import eu.ark.creditark.services.creditarkservices.utils.DtoGenUtils;
import static  eu.ark.creditark.services.creditarkservices.enums.DtoValueDataType.TYPE_AMOUNT;

public class KeyValueDto implements Serializable{

	private static final long serialVersionUID = -3342627858365858707L;
	private String key;
	private String value;
	private String type;

	public KeyValueDto() {
	}

	public KeyValueDto(String key, double value) {
		this.key = key;
		this.value = DtoGenUtils.numberToStringUIFormated(value);
	}
	
	
	
	
	public KeyValueDto(String key, double value, String type) {
		this.key = key;
		switch (type) {
			case "TYPE_AMOUNT": {
				this.value =DtoGenUtils.numberToStringUIFormated(value);
				break;
			}
			case "TYPE_PERCENTAGE": {
				this.value =DtoGenUtils.numberToStringPercentage(value);
				break;
			}
			case "TYPE_INTEGER": {
				this.value =DtoGenUtils.numberToString(value);
				break;
			} case "DOUBLE": {
				this.value =DtoGenUtils.numberToString(value);
				break;
			}
			default:{
				this.value = Double.toString(value);
				break;
			}
		}
		this.type = type;
	}


	public KeyValueDto(String key, String value) {
		this.key = key;
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public String getValue() {
		return value;
	}
	public String getType() {
		return type;
	}
	
}
