package eu.ark.creditark.services.creditarkservices.shared;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ConfigInfo implements Serializable {
	private static final long serialVersionUID = -317002995928003428L;
	private String loginName;
	private String fullName;
	private String organization;
	private LinkedHashMap<Integer, String> contexts;
	private int[] roles;
	private HashMap<Integer, String> helpFiles;
	private String pwRegEx;
	private boolean changePwRequired;
	private String publicIp;
	private String version;
	private String copyright;
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public LinkedHashMap<Integer, String> getContexts() {
		return contexts;
	}

	public void setContexts(LinkedHashMap<Integer, String> contexts) {
		this.contexts = contexts;
	}

	public int[] getRoles() {
		return roles;
	}

	public void setRoles(int[] roles) {
		this.roles = roles;
	}

	public HashMap<Integer, String> getHelpFiles() {
		return helpFiles;
	}

	public void setHelpFiles(HashMap<Integer, String> helpFiles) {
		this.helpFiles = helpFiles;
	}

	public String getPwRegEx() {
		return pwRegEx;
	}

	public void setPwRegEx(String pwRegEx) {
		this.pwRegEx = pwRegEx;
	}

	public boolean isChangePwRequired() {
		return changePwRequired;
	}

	public void setChangePwRequired(boolean changePwRequired) {
		this.changePwRequired = changePwRequired;
	}

	public String getPublicIp() {
		return publicIp;
	}

	public void setPublicIp(String publicIp) {
		this.publicIp = publicIp;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
}
