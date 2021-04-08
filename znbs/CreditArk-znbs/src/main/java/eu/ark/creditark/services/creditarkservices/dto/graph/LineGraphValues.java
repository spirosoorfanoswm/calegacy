package eu.ark.creditark.services.creditarkservices.dto.graph;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LineGraphValues implements Serializable {

	private static final long serialVersionUID = -8461239607177130742L;
	private String description;
	private List<Double> values;
	
	
	public LineGraphValues(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Double> getValues() {
		if(null == this.values) 
			values = new ArrayList<Double>();
		return values;
	}
	public void setValues(List<Double> values) {
		this.values = values;
	}
	
}