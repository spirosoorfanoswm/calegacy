package eu.ark.creditark.services.creditarkservices.dto.graph;

import java.io.Serializable;
import java.util.*;

public class LineGraphValuesCollection implements Serializable {

	private static final long serialVersionUID = -8747387582157414590L;
	//http://gionkunz.github.io/chartist-js/examples.html#example-multiline-bar
	private List<String> labels = new ArrayList<String>();
	private List<LineGraphValues> values = new ArrayList<>();;
	
	public List<LineGraphValues> getValues() {
		return values;
	}
	public void setValues(List<LineGraphValues> values) {
		this.values = values;
	}
	public List<String> getLabels() {
		return labels;
	}
	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	
	
	
}

