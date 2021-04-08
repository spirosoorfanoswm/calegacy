package eu.ark.creditark.services.creditarkservices.dto.input;

import java.io.Serializable;
import java.util.Date;

public class CustomersNumInputDto implements Serializable{

	private static final long serialVersionUID = -6216331723567167428L;
	private Date snapshotDate;
	private int scenarioId;
	private int portfolioId;
	private short contextId;
	public CustomersNumInputDto(Date snapshotDate, int scenarioId, int portfolioId, short contextId) {
		this.snapshotDate = snapshotDate;
		this.scenarioId = scenarioId;
		this.portfolioId = portfolioId;
		this.contextId = contextId;
	}
	public Date getSnapshotDate() {
		return snapshotDate;
	}
	public int getScenarioId() {
		return scenarioId;
	}
	public int getPortfolioId() {
		return portfolioId;
	}
	public short getContextId() {
		return contextId;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("CustomersNumInputDto{");
		sb.append("snapshotDate=").append(snapshotDate);
		sb.append(", scenarioId=").append(scenarioId);
		sb.append(", portfolioId=").append(portfolioId);
		sb.append(", contextId=").append(contextId);
		sb.append('}');
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + contextId;
		result = prime * result + portfolioId;
		result = prime * result + scenarioId;
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
		CustomersNumInputDto other = (CustomersNumInputDto) obj;
		if (contextId != other.contextId)
			return false;
		if (portfolioId != other.portfolioId)
			return false;
		if (scenarioId != other.scenarioId)
			return false;
		if (snapshotDate == null) {
			if (other.snapshotDate != null)
				return false;
		} else if (!snapshotDate.equals(other.snapshotDate))
			return false;
		return true;
	}
	
	
	
}
