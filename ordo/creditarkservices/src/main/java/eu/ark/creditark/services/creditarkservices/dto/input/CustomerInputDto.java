package eu.ark.creditark.services.creditarkservices.dto.input;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class CustomerInputDto implements Serializable {

	private static final long serialVersionUID = 600323763997332721L;
	private String customerId;
	private int scenarioId;
	private Date snapshotDate; 
	private int period; 
	private int contextId;

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public int getScenarioId() {
		return scenarioId;
	}

	public void setScenarioId(int scenarioId) {
		this.scenarioId = scenarioId;
	}

	public void setSnapshotDate(Date snapshotDate) {
		this.snapshotDate = snapshotDate;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public void setContextId(int contextId) {
		this.contextId = contextId;
	}

	public CustomerInputDto(String customerId, Date snapshotDate, int period, int contextId, int scenarioId) {
		this.customerId = customerId;
		this.snapshotDate = snapshotDate;
		this.period = period;
		this.contextId = contextId;
		this.scenarioId = scenarioId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public Date getSnapshotDate() {
		return snapshotDate;
	}
	public int getPeriod() {
		return period;
	}
	public int getContextId() {
		return contextId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomerInputDto [customerId=");
		builder.append(customerId);
		builder.append(", snapshotDate=");
		builder.append(snapshotDate);
		builder.append(", period=");
		builder.append(period);
		builder.append(", contextId=");
		builder.append(contextId);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CustomerInputDto that = (CustomerInputDto) o;
		return scenarioId == that.scenarioId &&
				period == that.period &&
				contextId == that.contextId &&
				customerId.equals(that.customerId) &&
				snapshotDate.equals(that.snapshotDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerId, scenarioId, snapshotDate, period, contextId);
	}
}
