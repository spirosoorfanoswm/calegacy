package eu.ark.creditark.services.creditarkservices.dto;

import java.io.Serializable;

import eu.ark.creditark.services.creditarkservices.dto.graph.LineGraphValuesCollection;

public class ClienteleStatisticsGraphDto implements Serializable{

	private static final long serialVersionUID = -1940545284406341736L;
	
	private LineGraphValuesCollection lineDataPointCollection;

	public LineGraphValuesCollection getLineDataPointCollection() {
		return lineDataPointCollection;
	}

	public void setLineDataPointCollection(LineGraphValuesCollection lineDataPointCollection) {
		this.lineDataPointCollection = lineDataPointCollection;
	}
	
}
