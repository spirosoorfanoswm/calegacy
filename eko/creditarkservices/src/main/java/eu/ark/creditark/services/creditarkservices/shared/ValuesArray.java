package eu.ark.creditark.services.creditarkservices.shared;

import java.io.Serializable;

public class ValuesArray implements Serializable {
	private static final long serialVersionUID = -3215702761203552899L;
	private transient Object[] values;
	
	public Object[] getValues() {
		return values;
	}
	
	public void setValues(Object[] values) {
		this.values = values;
	}
}
