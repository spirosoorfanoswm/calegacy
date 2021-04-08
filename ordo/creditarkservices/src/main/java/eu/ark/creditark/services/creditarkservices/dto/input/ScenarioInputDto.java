package eu.ark.creditark.services.creditarkservices.dto.input;

import java.util.Date;

public class ScenarioInputDto {

	private Date snapshotDate;
	private String user;
	private short contextId;
	
	
	public ScenarioInputDto(Date snapshotDate, String user, short contextId) {
		this.snapshotDate = snapshotDate;
		this.user = user;
		this.contextId = contextId;
	}
	
	public Date getSnapshotDate() {
		return snapshotDate;
	}
	public void setSnapshotDate(Date snapshotDate) {
		this.snapshotDate = snapshotDate;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

	public short getContextId() {
		return contextId;
	}

	public void setContextId(short contextId) {
		this.contextId = contextId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ScenarioInputDto [snapshotDate=");
		builder.append(snapshotDate);
		builder.append(", user=");
		builder.append(user);
		builder.append(", contextId=");
		builder.append(contextId);
		builder.append("]");
		return builder.toString();
	}
	
}
