package eu.ark.creditark.services.creditarkservices.shared;

import java.io.Serializable;
import java.util.Date;

public class ContextBus extends SnapshotBus implements Serializable {
	private static final long serialVersionUID = 7006410324981054802L;
	private ContextInfo contextInfo;
	private Date snapshotDate;

	public ContextInfo getContextInfo() {
		return contextInfo;
	}

	public void setContextInfo(ContextInfo contextInfo) {
		this.contextInfo = contextInfo;
	}

	public Date getSnapshotDate() {
		return snapshotDate;
	}

	public void setSnapshotDate(Date snapshotDate) {
		this.snapshotDate = snapshotDate;
	}
}
