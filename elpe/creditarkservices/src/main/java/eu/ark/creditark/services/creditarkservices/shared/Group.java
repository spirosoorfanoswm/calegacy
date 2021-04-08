package eu.ark.creditark.services.creditarkservices.shared;

import java.io.Serializable;

public class Group implements Serializable {
	private static final long serialVersionUID = 8634528894249291856L;
	
	private transient boolean dirty = false;

	private int groupId;
	private String groupName;
	
	public int getGroupId() {
		return groupId;
	}
	
	public void setGroupId(int groupId) {
		this.groupId = groupId;
		dirty = true;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
		dirty = true;
	}

	public boolean isDirty() {
		return dirty;
	}
}
