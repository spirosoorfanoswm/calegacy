package eu.ark.creditark.services.creditarkservices.dto;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import eu.ark.creditark.services.creditarkservices.enums.GenerealConstants;
import eu.ark.creditark.services.creditarkservices.shared.ClienteleStatistics;
import eu.ark.creditark.services.creditarkservices.utils.DateUtils;
import eu.ark.creditark.services.creditarkservices.utils.DtoGenUtils;

public class ClienteleStatisticsDto implements Serializable {

	private String lastPeriod;
	private String previousPeriod;
	private static final long serialVersionUID = -8072855455166962449L;
	private List<ClientStatisticItemDto> clientStatisticItemDtos;
	
	public List<ClientStatisticItemDto> getClientStatisticItemDtos() {
		if(null == this.clientStatisticItemDtos) this.clientStatisticItemDtos = new ArrayList<ClientStatisticItemDto>();
		return clientStatisticItemDtos;
	}

	public void setClientStatisticItemDtos(List<ClientStatisticItemDto> clientStatisticItemDtos) {
		this.clientStatisticItemDtos = clientStatisticItemDtos;
	}

	public String getLastPeriod() {
		return lastPeriod;
	}

	public void setLastPeriod(String lastPeriod) {
		this.lastPeriod = lastPeriod;
	}

	public String getPreviousPeriod() {
		return previousPeriod;
	}

	public void setPreviousPeriod(String previousPeriod) {
		this.previousPeriod = previousPeriod;
	}

	public ClienteleStatisticsDto(Date snapshotDate,
								  ClienteleStatistics clienteleStatistics,
								  List<String> clientStatisticsDesc, int numberOfScores) {
		int custLast = 0;
		int custPrev = 0;
		double amountLast = 0;
		double amountPrev = 0;
		try {
			this.lastPeriod =  DateUtils.dateToString(snapshotDate, GenerealConstants.DATE_FORMAT_DD_MM_YYYY.getValue());
			this.previousPeriod = DateUtils.dateToString(clienteleStatistics.getSnapshotDatePrev(), GenerealConstants.DATE_FORMAT_DD_MM_YYYY.getValue());

			for(int i=0; i<numberOfScores; i++) {
				custLast += clienteleStatistics.getCustomersPerScore()[i + numberOfScores];
				amountLast += clienteleStatistics.getExposurePerScore()[i + numberOfScores];
				custPrev += clienteleStatistics.getCustomersPerScore()[i];
				amountPrev += clienteleStatistics.getExposurePerScore()[i];
			}
		} catch (ParseException e) {

		}


		this.getClientStatisticItemDtos().add(new ClientStatisticItemDto("balance",
				DtoGenUtils.numberToString(amountLast),
				DtoGenUtils.numberToString(amountPrev),
				clientStatisticsDesc.get(0)));
		this.getClientStatisticItemDtos().add(new ClientStatisticItemDto("pastDue",
				DtoGenUtils.numberToString(clienteleStatistics.getPastDue()),
				DtoGenUtils.numberToString(clienteleStatistics.getPastDuePrev()),
				clientStatisticsDesc.get(1)));
		this.getClientStatisticItemDtos().add(new ClientStatisticItemDto("limits", 
				DtoGenUtils.numberToString(clienteleStatistics.getLimits()), 
				DtoGenUtils.numberToString(clienteleStatistics.getLimitsPrev()),
				clientStatisticsDesc.get(2)));
		this.getClientStatisticItemDtos().add(new ClientStatisticItemDto("overrides", 
				DtoGenUtils.numberToString(clienteleStatistics.getOverrides()), 
				DtoGenUtils.numberToString(clienteleStatistics.getOverridesPrev()),
				clientStatisticsDesc.get(3)));
		this.getClientStatisticItemDtos().add(new ClientStatisticItemDto("unusedLimits", 
				DtoGenUtils.numberToString(clienteleStatistics.getUnusedLimits()), 
				DtoGenUtils.numberToString(clienteleStatistics.getUnusedLimitsPrev()),
				clientStatisticsDesc.get(4)));
		this.getClientStatisticItemDtos().add(new ClientStatisticItemDto("unusedPct", 
				DtoGenUtils.numberToStringPercentage(clienteleStatistics.getUnusedPct()),
				DtoGenUtils.numberToStringPercentage(clienteleStatistics.getUnusedPctPrev()),
				clientStatisticsDesc.get(5)));
		this.getClientStatisticItemDtos().add(new ClientStatisticItemDto("overriddenPct", 
				DtoGenUtils.numberToStringPercentage(clienteleStatistics.getOverriddenPct()),
				DtoGenUtils.numberToStringPercentage(clienteleStatistics.getOverriddenPctPrev()),
				clientStatisticsDesc.get(6)));
		this.getClientStatisticItemDtos().add(new ClientStatisticItemDto("pastDuePct", 
				DtoGenUtils.numberToStringPercentage(clienteleStatistics.getPastPuePct()),
				DtoGenUtils.numberToStringPercentage(clienteleStatistics.getPastPuePctPrev()),
				clientStatisticsDesc.get(7)));
		this.getClientStatisticItemDtos().add(new ClientStatisticItemDto("periodSales",
				DtoGenUtils.numberToString(clienteleStatistics.getAggSales()), 
				DtoGenUtils.numberToString(clienteleStatistics.getAggSalesPrev()),
				clientStatisticsDesc.get(8)));
		this.getClientStatisticItemDtos().add(new ClientStatisticItemDto("periodVat",
				DtoGenUtils.numberToString(clienteleStatistics.getAggVat()), 
				DtoGenUtils.numberToString(clienteleStatistics.getAggVatPrev()), clientStatisticsDesc.get(9)));
		this.getClientStatisticItemDtos().add(new ClientStatisticItemDto("periodTurnover",
				DtoGenUtils.numberToString(clienteleStatistics.getAggTurnover()), 
				DtoGenUtils.numberToString(clienteleStatistics.getAggTurnoverPrev()), clientStatisticsDesc.get(10)));
		this.getClientStatisticItemDtos().add(new ClientStatisticItemDto("periodPayments", 
				DtoGenUtils.numberToString(clienteleStatistics.getAggPayments()), 
				DtoGenUtils.numberToString(clienteleStatistics.getAggPaymentsPrev()), clientStatisticsDesc.get(11)));
		this.getClientStatisticItemDtos().add(new ClientStatisticItemDto("meanSales", 
				DtoGenUtils.numberToString(clienteleStatistics.getMeanSales()), 
				DtoGenUtils.numberToString(clienteleStatistics.getMeanSalesPrev()), clientStatisticsDesc.get(12)));
		this.getClientStatisticItemDtos().add(new ClientStatisticItemDto("meanTurnover", 
				DtoGenUtils.numberToString(clienteleStatistics.getMeanTurnover()), 
				DtoGenUtils.numberToString(clienteleStatistics.getMeanTurnoverPrev()), clientStatisticsDesc.get(13)));
		this.getClientStatisticItemDtos().add(new ClientStatisticItemDto("volSales", 
				DtoGenUtils.numberToStringPercentage(clienteleStatistics.getVolSales()),
				DtoGenUtils.numberToStringPercentage(clienteleStatistics.getVolSalesPrev()), clientStatisticsDesc.get(14)));
		this.getClientStatisticItemDtos().add(new ClientStatisticItemDto("meanPayments", 
				DtoGenUtils.numberToString(clienteleStatistics.getMeanPayments()), 
				DtoGenUtils.numberToString(clienteleStatistics.getMeanPaymentsPrev()), clientStatisticsDesc.get(15)));
		this.getClientStatisticItemDtos().add(new ClientStatisticItemDto("volPayments", 
				DtoGenUtils.numberToString(clienteleStatistics.getVolPayments()), 
				DtoGenUtils.numberToString(clienteleStatistics.getVolPaymentsPrev()), clientStatisticsDesc.get(16)));
		this.getClientStatisticItemDtos().add(new ClientStatisticItemDto("meanExposure", 
				DtoGenUtils.numberToString(clienteleStatistics.getMeanExposure()), 
				DtoGenUtils.numberToString(clienteleStatistics.getMeanExposurePrev()), clientStatisticsDesc.get(17)));
		this.getClientStatisticItemDtos().add(new ClientStatisticItemDto("volExposure", 
				DtoGenUtils.numberToStringPercentage(clienteleStatistics.getVolExposure()),
				DtoGenUtils.numberToStringPercentage(clienteleStatistics.getVolExposurePrev()), clientStatisticsDesc.get(18)));
		this.getClientStatisticItemDtos().add(new ClientStatisticItemDto("meanPastDue", 
				DtoGenUtils.numberToString(clienteleStatistics.getMeanPastDue()), 
				DtoGenUtils.numberToString(clienteleStatistics.getMeanPastDuePrev()), clientStatisticsDesc.get(19)));
		this.getClientStatisticItemDtos().add(new ClientStatisticItemDto("meanLimits", 
				DtoGenUtils.numberToString(clienteleStatistics.getMeanLimits()), 
				DtoGenUtils.numberToString(clienteleStatistics.getMeanLimitsPrev()), clientStatisticsDesc.get(20)));
		this.getClientStatisticItemDtos().add(new ClientStatisticItemDto("meanOverrides", 
				DtoGenUtils.numberToString(clienteleStatistics.getMeanOverrides()), 
				DtoGenUtils.numberToString(clienteleStatistics.getMeanOverridesPrev()), clientStatisticsDesc.get(21)));
		this.getClientStatisticItemDtos().add(new ClientStatisticItemDto("meanOverridesPct", 
				DtoGenUtils.numberToStringPercentage(clienteleStatistics.getMeanOverridesPct()),
				DtoGenUtils.numberToStringPercentage(clienteleStatistics.getMeanOverridesPctPrev()), clientStatisticsDesc.get(22)));
		this.getClientStatisticItemDtos().add(new ClientStatisticItemDto("meanUnusedLimits", 
				DtoGenUtils.numberToString(clienteleStatistics.getMeanUnusedLimits()), 
				DtoGenUtils.numberToString(clienteleStatistics.getMeanUnusedLimitsPrev()), clientStatisticsDesc.get(23)));
		this.getClientStatisticItemDtos().add(new ClientStatisticItemDto("meanUnusedLimitsPct", 
				DtoGenUtils.numberToStringPercentage(clienteleStatistics.getMeanUnusedLimitsPct()),
				DtoGenUtils.numberToStringPercentage(clienteleStatistics.getMeanUnusedLimitsPctPrev()), clientStatisticsDesc.get(24)));
		this.getClientStatisticItemDtos().add(new ClientStatisticItemDto("meanDso",
				DtoGenUtils.numberToString(clienteleStatistics.getMeanDso()), 
				DtoGenUtils.numberToString(clienteleStatistics.getMeanDsoPrev()), clientStatisticsDesc.get(25)));

		this.lastPeriod = this.lastPeriod;
	}


}
