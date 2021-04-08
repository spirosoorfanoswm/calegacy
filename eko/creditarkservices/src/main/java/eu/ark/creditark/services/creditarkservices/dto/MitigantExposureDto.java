package eu.ark.creditark.services.creditarkservices.dto;

import java.io.Serializable;

public class MitigantExposureDto implements Serializable {
	private int inx;
	private double mitigantValue;
	private double lgd;
	public int getInx() {
		return inx;
	}
	public void setInx(int inx) {
		this.inx = inx;
	}
	public double getMitigantValue() {
		return mitigantValue;
	}
	public void setMitigantValue(double mitigantValue) {
		this.mitigantValue = mitigantValue;
	}
	public double getLgd() {
		return lgd;
	}
	public void setLgd(double lgd) {
		this.lgd = lgd;
	}

	public MitigantExposureDto() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + inx;
		long temp;
		temp = Double.doubleToLongBits(lgd);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(mitigantValue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		MitigantExposureDto other = (MitigantExposureDto) obj;
		if (inx != other.inx)
			return false;
		if (Double.doubleToLongBits(lgd) != Double.doubleToLongBits(other.lgd))
			return false;
		if (Double.doubleToLongBits(mitigantValue) != Double.doubleToLongBits(other.mitigantValue))
			return false;
		return true;
	}
	
}
