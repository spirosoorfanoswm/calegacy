package eu.ark.creditark.services.creditarkservices.shared;

import java.io.Serializable;


public class ReportDescriptor implements Serializable {
	private static final long serialVersionUID = 498369203933802782L;
	
	public static final int CONTEXT_ID = 0;
	public static final int SNAPSHOT_DATE = 1;
	public static final int PORTFOLIO_ID = 2;
	public static final int SCENARIO_ID = 3;
	public static final int PROSPECT_ID = 4;
	public static final int CUSTOMER_ID = 5;
	
	private int[] objectTypeIds;
	private String description;
	private int[] internalParameters;
	private UserField[] userParameters;

	public int[] getObjectTypeIds() {
		return objectTypeIds;
	}

	public void setObjectTypeId(int[] objectTypeIds) {
		this.objectTypeIds = objectTypeIds;
	}
	
	public boolean hasObjectType(int objectType) {
		if (objectTypeIds == null) return false;
		for (int i = 0; i < objectTypeIds.length; i++) 
			if (objectTypeIds[i] == objectType) return true;
		return false;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int[] getInternalParameters() {
		return internalParameters;
	}

	public void setInternalParameters(int[] internalParameters) {
		this.internalParameters = internalParameters;
	}

	public UserField[] getUserParameters() {
		return userParameters;
	}

	public void setUserParameters(UserField[] userParameters) {
		this.userParameters = userParameters;
	}

	public void setObjectTypeIds(int[] objectTypeIds) {
		this.objectTypeIds = objectTypeIds;
	}

}
