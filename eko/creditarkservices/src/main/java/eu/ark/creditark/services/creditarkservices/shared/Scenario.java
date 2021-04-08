package eu.ark.creditark.services.creditarkservices.shared;

import java.io.Serializable;

public class Scenario implements Serializable {
	private static final long serialVersionUID = 7319952755133915984L;
	
	protected transient boolean empty = true;
	protected transient boolean deleted = false;
	protected transient boolean dirty = false;

	private boolean locked = false;
	private String ownerLogin;
	private String ownerName;
	private String description;
	private boolean validResult = false;
	private ScenarioParameters parameters = new ScenarioParameters();
	private ScenarioStatistics statistics = null;

	public boolean isValidResult() {
		return validResult;
	}

	public boolean isDirty() {
		return dirty;
	}

	public void setDirty(boolean dirty) {
		this.dirty = dirty;
		if (dirty) statistics = null;
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean newObject) {
		this.empty = newObject;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
		parameters = null;
		statistics = null;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public String getOwnerLogin() {
		return ownerLogin;
	}

	public void setOwnerLogin(String ownerLogin) {
		this.ownerLogin = ownerLogin;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean hasValidResult() {
		return validResult;
	}

	public void setValidResult(boolean validResult) {
		this.validResult = validResult;
	}

	public ScenarioParameters getParameters() {
		return parameters;
	}
	
	public void setParameters(ScenarioParameters parameters) {
		this.parameters = parameters;
	}

	public ScenarioStatistics getStatistics() {
		return statistics;
	}

	public void setStatistics(ScenarioStatistics statistics) {
		this.statistics = statistics;
	}
}
