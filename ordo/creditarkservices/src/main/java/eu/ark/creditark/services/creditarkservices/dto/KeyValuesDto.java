package eu.ark.creditark.services.creditarkservices.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class KeyValuesDto implements Serializable{

	private static final long serialVersionUID = -3342627858365858707L;
	
	private String description;
	private List<KeyValueDto> values;

	public KeyValuesDto() {
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<KeyValueDto> getValues() {
		if(null == this.values)
			this.values = new ArrayList<KeyValueDto>();
		return values;
	}
	public void setValues(List<KeyValueDto> values) {
		this.values = values;
	}
	
}
