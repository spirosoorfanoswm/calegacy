package eu.ark.creditark.services.creditarkservices.dto.input;

import java.io.Serializable;
import java.util.Date;

public class ClienteleStatisticsInputDto implements Serializable{
	private static final long serialVersionUID = -1869932832986647634L;
	
	private Date snapshotDate;
	private short portfolioId;
	private short comparisonPeriod;
	private short contextId;
	
	
	public ClienteleStatisticsInputDto(Date snapshotDate, 
			short portfolioId, 
			short comparisonPeriod, 
			short contextId) {

		this.snapshotDate = snapshotDate;
		this.portfolioId = portfolioId;
		this.comparisonPeriod = comparisonPeriod;
		this.contextId = contextId;
	}
	public Date getSnapshotDate() {
		return snapshotDate;
	}
	public void setSnapshotDate(Date snapshotDate) {
		this.snapshotDate = snapshotDate;
	}
	public short getPortfolioId() {
		return portfolioId;
	}
	public void setPortfolioId(short portfolioId) {
		this.portfolioId = portfolioId;
	}
	public short getComparisonPeriod() {
		return comparisonPeriod;
	}
	public void setComparisonPeriod(short comparisonPeriod) {
		this.comparisonPeriod = comparisonPeriod;
	}
	public short getContextId() {
		return contextId;
	}
	public void setContextId(short contextId) {
		this.contextId = contextId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + comparisonPeriod;
		result = prime * result + contextId;
		result = prime * result + portfolioId;
		result = prime * result + ((snapshotDate == null) ? 0 : snapshotDate.hashCode());
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
		ClienteleStatisticsInputDto other = (ClienteleStatisticsInputDto) obj;
		if (comparisonPeriod != other.comparisonPeriod)
			return false;
		if (contextId != other.contextId)
			return false;
		if (portfolioId != other.portfolioId)
			return false;
		if (snapshotDate == null) {
			if (other.snapshotDate != null)
				return false;
		} else if (!snapshotDate.equals(other.snapshotDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("ClienteleStatisticsInputDto{");
		sb.append("snapshotDate=").append(snapshotDate);
		sb.append(", portfolioId=").append(portfolioId);
		sb.append(", comparisonPeriod=").append(comparisonPeriod);
		sb.append(", contextId=").append(contextId);
		sb.append('}');
		return sb.toString();
	}
}
