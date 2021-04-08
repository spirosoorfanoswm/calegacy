package eu.ark.creditark.services.creditarkservices.services.transformator.clientele;


import eu.ark.creditark.services.creditarkservices.config.AppPropertiesConfig;
import eu.ark.creditark.services.creditarkservices.dto.*;
import eu.ark.creditark.services.creditarkservices.enums.GenerealConstants;
import eu.ark.creditark.services.creditarkservices.utils.DateUtils;
import eu.ark.creditark.services.creditarkservices.utils.DtoGenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.ark.creditark.services.creditarkservices.dto.input.ClienteleTransformatorInputDto;

@Component("CLIENT_DISTRIBUTION_VIEW")
public class ClienteleDistributionViewTransformator implements ClienteleTransformator {

	@Autowired
	private AppPropertiesConfig appProperties;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@SuppressWarnings("unchecked")
	@Override
	public ClienteleDistributionDto transform(ClienteleTransformatorInputDto input) {
		
		int custLast = 0;
		int custPrev = 0;
		double amountLast = 0;
		double amountPrev = 0;
		int numberOfScores = input.getContextInfo().getScores().length;
		ClienteleDistributionDto response = new ClienteleDistributionDto();
		
		ClienteleDistributionItemCollectionDto snapshotGrid = new ClienteleDistributionItemCollectionDto();
		ClienteleDistributionItemCollectionDto previousGrid = new ClienteleDistributionItemCollectionDto();
		

		for(int i=0; i<input.getContextInfo().getScores().length; i++) {

			custLast += input.getClienteleStatistics().getCustomersPerScore()[i + numberOfScores];
			amountLast += input.getClienteleStatistics().getExposurePerScore()[i + numberOfScores];
			custPrev += input.getClienteleStatistics().getCustomersPerScore()[i];
			amountPrev += input.getClienteleStatistics().getExposurePerScore()[i];

			logger.info("\n" + i + " CustomersPerScore : " + input.getClienteleStatistics().getCustomersPerScore()[i] +"\n" +
			i + " ExposurePerScore : " + input.getClienteleStatistics().getExposurePerScore()[i] +"\n" +
			(i + numberOfScores) + " CustomersPerScore : " + input.getClienteleStatistics().getCustomersPerScore()[i+ numberOfScores] +"\n" +
			(i + numberOfScores) + " ExposurePerScore : " +  input.getClienteleStatistics().getExposurePerScore()[i+ numberOfScores]);

		}
		
		for(int i=0; i<input.getContextInfo().getScores().length; i++) {
			snapshotGrid.getClienteleDistributionItems().add(new ClienteleDistributionItemDto(
					input.getContextInfo().getScores()[i],
					input.getClienteleStatistics().getCustomersPerScore()[i+numberOfScores],
					DtoGenUtils.numberToStringPercentageNoPostfix(
					(double)input.getClienteleStatistics().getCustomersPerScore()[i+numberOfScores]/custLast),
					DtoGenUtils.numberToString(input.getClienteleStatistics().getExposurePerScore()[i+numberOfScores]),
					DtoGenUtils.numberToStringPercentageNoPostfix(
					input.getClienteleStatistics().getExposurePerScore()[i+numberOfScores]/amountLast)
					));
			previousGrid.getClienteleDistributionItems().add(new ClienteleDistributionItemDto(
					input.getContextInfo().getScores()[i],
					input.getClienteleStatistics().getCustomersPerScore()[i],
					DtoGenUtils.numberToStringPercentageNoPostfix((double)input.getClienteleStatistics().getCustomersPerScore()[i]/custPrev),
					DtoGenUtils.numberToString(input.getClienteleStatistics().getExposurePerScore()[i]),
					DtoGenUtils.numberToStringPercentageNoPostfix(input.getClienteleStatistics().getExposurePerScore()[i]/amountPrev)
					));
		}

		input.getPortfolioAgingOverall().getPortfolioAgingList().stream().forEach(portfolioAging ->  {
			response.getDataAging().getValues().add(new KeyValueDto(portfolioAging.getPortfolioName(),
					portfolioAging.getAgingValue(), "DOUBLE"));
		});

		input.getPortfolioAgingOverall().getPortfolioDunningAgingList().stream().forEach(portfolioAging ->  {
			response.getDataDunningAging().getValues().add(new KeyValueDto(portfolioAging.getPortfolioName(),
					portfolioAging.getAgingValue(), "DOUBLE"));
		});

		if(null!=input.getPortfolioAgingOverall().getAverageDaysToPay()) {
			response.getPaymentAverages().getValues().add(new KeyValueDto(appProperties.getClientStatistics().get(26), input.getPortfolioAgingOverall().getAverageDaysToPay().getValue()));
		}

		if(null!=input.getPortfolioAgingOverall().getAverageDaysUnpaid()) {
			response.getPaymentAverages().getValues().add(new KeyValueDto(appProperties.getClientStatistics().get(27), input.getPortfolioAgingOverall().getAverageDaysUnpaid().getValue()));
		}

		/*try {
			response.getDistributionStatistics().add(new ClientStatisticItemDto("dates",
					DateUtils.dateToString(input.getInput().getSnapshotDate(),
							GenerealConstants.DATE_FORMAT_DD_MM_YYYY.getValue()),
					DateUtils.dateToString(input.getClienteleStatistics().getSnapshotDatePrev(),
							GenerealConstants.DATE_FORMAT_DD_MM_YYYY.getValue()),
					""));
		} catch (Exception e) {

		}

		response.getDistributionStatistics().add(new ClientStatisticItemDto("pastDue",
				DtoGenUtils.numberToString(input.getClienteleStatistics().getPastDue()),
				DtoGenUtils.numberToString(input.getClienteleStatistics().getPastDuePrev()),
				appProperties.getClientStatistics().get(1)));

		response.getDistributionStatistics().add(new ClientStatisticItemDto("limits",
				DtoGenUtils.numberToString(input.getClienteleStatistics().getLimits()),
				DtoGenUtils.numberToString(input.getClienteleStatistics().getLimitsPrev()),
				appProperties.getClientStatistics().get(2)));

		response.getDistributionStatistics().add(new ClientStatisticItemDto("overrides",
				DtoGenUtils.numberToString(input.getClienteleStatistics().getOverrides()),
				DtoGenUtils.numberToString(input.getClienteleStatistics().getOverridesPrev()),
				appProperties.getClientStatistics().get(3)));

		response.getDistributionStatistics().add(new ClientStatisticItemDto("unusedLimits",
				DtoGenUtils.numberToString(input.getClienteleStatistics().getUnusedLimits()),
				DtoGenUtils.numberToString(input.getClienteleStatistics().getUnusedLimitsPrev()),
				appProperties.getClientStatistics().get(4)));

		response.getDistributionStatistics().add(new ClientStatisticItemDto("periodSales",
				DtoGenUtils.numberToString(input.getClienteleStatistics().getAggSales()),
				DtoGenUtils.numberToString(input.getClienteleStatistics().getAggSalesPrev()),
				appProperties.getClientStatistics().get(8)));

		response.getDistributionStatistics().add(new ClientStatisticItemDto("meanDso",
				DtoGenUtils.numberToString(input.getClienteleStatistics().getMeanDso()),
				DtoGenUtils.numberToString(input.getClienteleStatistics().getMeanDsoPrev()),
				appProperties.getClientStatistics().get(25)));*/



		/*input.getClienteleStatistics().getPastDue();
		input.getClienteleStatistics().getPastDue();
		this.getClientStatisticItemDtos().add(new ClientStatisticItemDto("pastDue",
				DtoGenUtils.numberToString(clienteleStatistics.getPastDue()),
				DtoGenUtils.numberToString(clienteleStatistics.getPastDuePrev()),
				clientStatisticsDesc.get(1)));*/
		snapshotGrid.setDate(DateUtils.dateToString(input.getInput().getSnapshotDate()));
		snapshotGrid.setTotalCustomers(custLast);
		snapshotGrid.setTotalExposure(DtoGenUtils.numberToString(amountLast));
		previousGrid.setDate(DateUtils.dateToString(input.getClienteleStatistics().getSnapshotDatePrev()));
		previousGrid.setTotalCustomers(custPrev);		
		previousGrid.setTotalExposure(DtoGenUtils.numberToString(amountPrev));
		response.getClienteleDistributionList().add(snapshotGrid);
		response.getClienteleDistributionList().add(previousGrid);
		return response;
	}

    
}
