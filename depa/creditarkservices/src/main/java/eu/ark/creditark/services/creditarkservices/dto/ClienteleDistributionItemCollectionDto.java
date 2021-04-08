package eu.ark.creditark.services.creditarkservices.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClienteleDistributionItemCollectionDto implements Serializable {


	private static final long serialVersionUID = 1788017185595425276L;
	private String date;
	private int totalCustomers;
	private String totalExposure;
	private List<ClienteleDistributionItemDto> clienteleDistributionItems;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<ClienteleDistributionItemDto> getClienteleDistributionItems() {
		if(null == this.clienteleDistributionItems) 
			this.clienteleDistributionItems = new ArrayList<ClienteleDistributionItemDto>();
		return clienteleDistributionItems;
	}
	public void setClienteleDistributionItems(List<ClienteleDistributionItemDto> clienteleDistributionItems) {
		this.clienteleDistributionItems = clienteleDistributionItems;
	}
	public int getTotalCustomers() {
		return totalCustomers;
	}
	public void setTotalCustomers(int totalCustomers) {
		this.totalCustomers = totalCustomers;
	}
	public String getTotalExposure() {
		return totalExposure;
	}
	public void setTotalExposure(String totalExposure) {
		this.totalExposure = totalExposure;
	}
	
}
