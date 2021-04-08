package eu.ark.creditark.services.creditarkservices.dto;

import java.io.Serializable;

public class ComparisonPeriodDto implements Serializable {
	
	private static final long serialVersionUID = -5737344696953538036L;
	private int id;
	private int period;
	private String desc;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComparisonPeriodDto other = (ComparisonPeriodDto) obj;
		if (id != other.id)
			return false;
		return true;
	}
	

}
