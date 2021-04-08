package eu.ark.creditark.services.creditarkservices.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClienteleDistributionDto implements Serializable {

	private static final long serialVersionUID = 2075008369405618011L;
	private List<ClienteleDistributionItemCollectionDto> clienteleDistributionList;
	private KeyValuesDto dataAging;
	private KeyValuesDto dataDunningAging;
	private KeyValuesDto paymentAverages;
	private List<ClientStatisticItemDto> distributionStatistics;

	public KeyValuesDto getDataDunningAging() {
		if(null == this.dataDunningAging) this.dataDunningAging = new KeyValuesDto();
		return dataDunningAging;
	}

	public void setDataDunningAging(KeyValuesDto dataDunningAging) {
		this.dataDunningAging = dataDunningAging;
	}

	public List<ClienteleDistributionItemCollectionDto> getClienteleDistributionList() {
		if(null == this.clienteleDistributionList)
			this.clienteleDistributionList = new ArrayList<>();
		return clienteleDistributionList;
	}

	public void setClienteleDistributionList(List<ClienteleDistributionItemCollectionDto> clienteleDistributionList) {
		this.clienteleDistributionList = clienteleDistributionList;
	}

	public KeyValuesDto getDataAging() {
		if(null == this.dataAging) this.dataAging = new KeyValuesDto();
		return dataAging;
	}

	public void setDataAging(KeyValuesDto dataAging) {
		this.dataAging = dataAging;
	}

	public KeyValuesDto getPaymentAverages() {
		if(null == this.paymentAverages) this.paymentAverages = new KeyValuesDto();
		return paymentAverages;
	}

	public void setPaymentAverages(KeyValuesDto paymentAverages) {
		this.paymentAverages = paymentAverages;
	}

	public List<ClientStatisticItemDto> getDistributionStatistics() {
		if(null == this.distributionStatistics) this.distributionStatistics = new ArrayList<>(1);
		return distributionStatistics;
	}

	public void setDistributionStatistics(List<ClientStatisticItemDto> distributionStatistics) {
		this.distributionStatistics = distributionStatistics;
	}
}
