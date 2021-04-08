package eu.ark.creditark.services.creditarkservices.dto;

import java.io.Serializable;

public class ClientStatisticItemDto implements Serializable{

	private static final long serialVersionUID = -1809680981435140619L;
	private String statistic;
	private String description;
	private String last;
	private String prev;
	
	public ClientStatisticItemDto() { }
	
	public ClientStatisticItemDto(String statistic,
								  String last,
								  String prev,
								  String description) {
		this.statistic = statistic;
		this.last = last;
		this.prev = prev;
		this.description = description.replaceAll("^\"|\"$", "");
	}

	public String getStatistic() {
		return statistic;
	}
	public void setStatistic(String statistic) {
		this.statistic = statistic;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public String getPrev() {
		return prev;
	}
	public void setPrev(String prev) {
		this.prev = prev;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
