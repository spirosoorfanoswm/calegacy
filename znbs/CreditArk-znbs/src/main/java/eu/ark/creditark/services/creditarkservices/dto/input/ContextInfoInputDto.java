package eu.ark.creditark.services.creditarkservices.dto.input;

import java.io.Serializable;

public class ContextInfoInputDto implements Serializable{

	private static final long serialVersionUID = -7524539951663127118L;
	private short contextId;
	private String user;
	
	
	public ContextInfoInputDto(short contextId, String user) {
		this.contextId = contextId;
		this.user = user;
	}
	public short getContextId() {
		return contextId;
	}
	public void setContextId(short contextId) {
		this.contextId = contextId;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + contextId;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		ContextInfoInputDto other = (ContextInfoInputDto) obj;
		if (contextId != other.contextId)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

}
