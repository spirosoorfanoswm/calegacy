package eu.ark.creditark.services.creditarkservices.shared;

import java.io.Serializable;

public class ReportProperties implements Serializable {

	private static final long serialVersionUID = -3772945543444638997L;
	private String reportName;
	private String description;
	private String[] parametersNames;
	
	public ReportProperties(String reportName, String description, String[] parametersNames) {
		this.reportName = reportName;
		this.description = description;
		this.parametersNames = parametersNames;
	}
	
	public String getReportName() {
		return reportName;
	}

	public String getDescription() {
		return description;
	}

	public String[] getParametersNames() {
		return parametersNames;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((reportName == null) ? 0 : reportName.hashCode());
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
		ReportProperties other = (ReportProperties) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (reportName == null) {
			if (other.reportName != null)
				return false;
		} else if (!reportName.equals(other.reportName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReportProperties [reportName=");
		builder.append(reportName);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}
	
	
}
