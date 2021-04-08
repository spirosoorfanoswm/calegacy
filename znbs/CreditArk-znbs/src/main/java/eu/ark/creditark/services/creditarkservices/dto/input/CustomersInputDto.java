package eu.ark.creditark.services.creditarkservices.dto.input;

import java.io.Serializable;
import java.util.Date;

public class CustomersInputDto implements Serializable{

	private static final long serialVersionUID = -6745487830982813269L;
	private Date snapshotDate;
	private int scenarioId;
	private int portfolioId;
	private int sortColumn;
	private boolean sortDescending;
	private int offset;
	private int length;
	private short contextId;
	
	public CustomersInputDto(Date snapshotDate, short contextId, int scenarioId, int portfolioId, int sortColumn,
			boolean sortDescending, int offset, int length) {
		this.snapshotDate = snapshotDate;
		this.contextId = contextId;
		this.scenarioId = scenarioId;
		this.portfolioId = portfolioId;
		this.sortColumn = sortColumn;
		this.sortDescending = sortDescending;
		this.offset = offset;
		this.length = length;
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
	public int getSortColumn() {
		return sortColumn;
	}
	public boolean isSortDescending() {
		return sortDescending;
	}
	public int getOffset() {
		return offset;
	}
	public int getLength() {
		return length;
	}
	public short getContextId() {
		return contextId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + contextId;
		result = prime * result + length;
		result = prime * result + offset;
		result = prime * result + portfolioId;
		result = prime * result + scenarioId;
		result = prime * result + ((snapshotDate == null) ? 0 : snapshotDate.hashCode());
		result = prime * result + sortColumn;
		result = prime * result + (sortDescending ? 1231 : 1237);
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
		CustomersInputDto other = (CustomersInputDto) obj;
		if (contextId != other.contextId)
			return false;
		if (length != other.length)
			return false;
		if (offset != other.offset)
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
		if (sortColumn != other.sortColumn)
			return false;
		if (sortDescending != other.sortDescending)
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("CustomersInputDto{");
		sb.append("snapshotDate=").append(snapshotDate);
		sb.append(", scenarioId=").append(scenarioId);
		sb.append(", portfolioId=").append(portfolioId);
		sb.append(", sortColumn=").append(sortColumn);
		sb.append(", sortDescending=").append(sortDescending);
		sb.append(", offset=").append(offset);
		sb.append(", length=").append(length);
		sb.append(", contextId=").append(contextId);
		sb.append('}');
		return sb.toString();
	}
}
