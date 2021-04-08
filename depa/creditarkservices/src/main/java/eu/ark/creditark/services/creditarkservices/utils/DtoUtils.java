package eu.ark.creditark.services.creditarkservices.utils;

import eu.ark.creditark.services.creditarkservices.model.mongo.CustomerKpis;
import eu.ark.creditark.services.creditarkservices.model.mongo.Kpi;
import eu.ark.creditark.services.creditarkservices.model.mongo.KpiKV;
import java.text.ParseException;
import java.util.*;
import java.util.stream.IntStream;

import eu.ark.creditark.services.creditarkservices.dto.*;
import eu.ark.creditark.services.creditarkservices.enums.GenerealConstants;
import org.apache.logging.log4j.util.Strings;

import eu.ark.creditark.services.creditarkservices.dto.input.ClienteleTransformatorInputDto;
import eu.ark.creditark.services.creditarkservices.enums.DtoValueDataType;
import eu.ark.creditark.services.creditarkservices.enums.LabelEnum;
import eu.ark.creditark.services.creditarkservices.shared.CustomerDetails;

public class DtoUtils {
	private DtoUtils() {}
	public static void createStatistics(CustomerDetailsDto response,
										List<String> statistics,
										ClienteleTransformatorInputDto input) {
		CustomerDetailsStatisticsDto customerDetailsStatisticsDto = new CustomerDetailsStatisticsDto();
		customerDetailsStatisticsDto.getStatistics().add(new KeyValueMultipleDto(statistics.get(14), statistics.get(14),
				new Date[] {input.getSnapshotDate(), input.getCustomerDetails().getPrevDate()}));
		customerDetailsStatisticsDto.getStatistics().add(new KeyValueMultipleDto(statistics.get(0), statistics.get(0),
				new int[] {input.getCustomerDetails().getPeriods0(), input.getCustomerDetails().getPeriods1()}));
		customerDetailsStatisticsDto.getStatistics().add(new KeyValueMultipleDto(statistics.get(1), statistics.get(1),
				new double[] {input.getCustomerDetails().getMaxBalance0(), input.getCustomerDetails().getMaxBalance1()}));
		customerDetailsStatisticsDto.getStatistics().add(new KeyValueMultipleDto(statistics.get(2), statistics.get(2),
				new Date[] {input.getCustomerDetails().getMaxBalanceDate0(), input.getCustomerDetails().getMaxBalanceDate1()}));
		customerDetailsStatisticsDto.getStatistics().add(new KeyValueMultipleDto(statistics.get(3), statistics.get(3),
				new double[] {input.getCustomerDetails().getMinBalance0(), input.getCustomerDetails().getMinBalance1()}));
		customerDetailsStatisticsDto.getStatistics().add(new KeyValueMultipleDto(statistics.get(4), statistics.get(4),
				new Date[] {input.getCustomerDetails().getMinBalanceDate0(), input.getCustomerDetails().getMinBalanceDate1()}));
		customerDetailsStatisticsDto.getStatistics().add(new KeyValueMultipleDto(statistics.get(5), statistics.get(5),
				new double[] {input.getCustomerDetails().getMaxPastDue0(), input.getCustomerDetails().getMaxPastDue1()}));
		customerDetailsStatisticsDto.getStatistics().add(new KeyValueMultipleDto(statistics.get(6), statistics.get(6),
				new Date[] {input.getCustomerDetails().getMaxPastDueDate0(), input.getCustomerDetails().getMaxPastDueDate1()}));
		customerDetailsStatisticsDto.getStatistics().add(new KeyValueMultipleDto(statistics.get(7), statistics.get(7),
				new double[] {input.getCustomerDetails().getAvgBalance0(), input.getCustomerDetails().getAvgBalance0()}));
		customerDetailsStatisticsDto.getStatistics().add(new KeyValueMultipleDto(statistics.get(8), statistics.get(8),
				new double[] {input.getCustomerDetails().getAvgTurnover0(), input.getCustomerDetails().getAvgTurnover1()}));
		customerDetailsStatisticsDto.getStatistics().add(new KeyValueMultipleDto(statistics.get(9), statistics.get(9),
				new double[] {input.getCustomerDetails().getAvgPayments0(), input.getCustomerDetails().getAvgPayments1()}));
		customerDetailsStatisticsDto.getStatistics().add(new KeyValueMultipleDto(statistics.get(10), statistics.get(10),
				new String[] {input.getCustomerDetails().getWorstBehavioralScore0(), input.getCustomerDetails().getWorstBehavioralScore1()}));
		customerDetailsStatisticsDto.getStatistics().add(new KeyValueMultipleDto(statistics.get(11), statistics.get(11),
				new Date[] {input.getCustomerDetails().getWorstBehavioralScoreDate0(), input.getCustomerDetails().getWorstBehavioralScoreDate0()}));
		customerDetailsStatisticsDto.getStatistics().add(new KeyValueMultipleDto(statistics.get(12), statistics.get(11),
				new String[] {input.getCustomerDetails().getWorstCreditArkScore0(), input.getCustomerDetails().getWorstCreditArkScore1()}));
		customerDetailsStatisticsDto.getStatistics().add(new KeyValueMultipleDto(statistics.get(13), statistics.get(11),
				new Date[] {input.getCustomerDetails().getWorstCreditArkScoreDate0(), input.getCustomerDetails().getWorstCreditArkScoreDate1()}));
		response.setCustomerDetailsStatisticsDto(customerDetailsStatisticsDto);
	}

	public static void createSnapshotMainData(CustomerDetailsDto response, ClienteleTransformatorInputDto input, List<String> labels) {
		CustomerDetailsSnapshotDto customerDetailsSnapshotDto = new CustomerDetailsSnapshotDto();
		customerDetailsSnapshotDto.setAreaId(input.getCustomerDetails().getAreaID());
		customerDetailsSnapshotDto.setLimit(DtoGenUtils.numberToStringUIFormated(input.getCustomerDetails().getExposure()[1]));
		customerDetailsSnapshotDto.setBalance(DtoGenUtils.numberToStringUIFormated(input.getCustomerDetails().getExposure()[0]));
		customerDetailsSnapshotDto.setGroupName(input.getCustomerDetails().getGroupName());
		customerDetailsSnapshotDto.setIndustryId(input.getCustomerDetails().getIndustryId());
		customerDetailsSnapshotDto.setCustomerName(input.getCustomerDetails().getCustomerName());
		customerDetailsSnapshotDto.setStatus(input.getCustomerDetails().getCustomerStatus());
		customerDetailsSnapshotDto.setProfitMargin(DtoGenUtils.numberToStringPercentage(input.getCustomerDetails().getProfitMargin()));
		customerDetailsSnapshotDto.setBehavioralScore(input.getCustomerDetails().getBehavioralScore());
		customerDetailsSnapshotDto.setCasScore(input.getCustomerDetails().getCreditArkScore());
		customerDetailsSnapshotDto.setQualitiveScore(input.getCustomerDetails().getQualitativeScore());
		customerDetailsSnapshotDto.setExternalScore(input.getCustomerDetails().getExtAssessment());

		/*ID RELATED DATA*/
		customerDetailsSnapshotDto.getIdData().getValues().add(new KeyValueDto(labels.get(14), input.getCustomerDetails().getCustomerId()));
		customerDetailsSnapshotDto.getIdData().getValues().add(new KeyValueDto(labels.get(0), input.getCustomerDetails().getCustomerName()));
		//customerDetailsSnapshotDto.getIdData().getValues().add(new KeyValueDto(labels.get(1), input.getCustomerDetails().getGroupName()));
		Optional.ofNullable(input.getCustomerDetails())
				.map(CustomerDetails::getCustomerSince)
				.ifPresent(dt -> {
					try {
						customerDetailsSnapshotDto.getIdData().getValues().add(new KeyValueDto(labels.get(2), DateUtils.dateToString(DateUtils.formatDate(
								new Date(dt.getTime()), GenerealConstants.DATE_FORMAT_DD_MM_YYYY.getValue()
						), GenerealConstants.DATE_FORMAT_DD_MM_YYYY.getValue())));

					} catch (Exception e) {
					}
				});

		customerDetailsSnapshotDto.getIdData().getValues().add(new KeyValueDto(labels.get(3), input.getCustomerDetails().getVatNumber()));
		customerDetailsSnapshotDto.getIdData().getValues().add(new KeyValueDto(labels.get(4), input.getCustomerDetails().getIndustryId()));
		customerDetailsSnapshotDto.getIdData().getValues().add(new KeyValueDto(labels.get(5), input.getCustomerDetails().getAreaID()));

		/*BUSINESS RELATED DATA*/
		customerDetailsSnapshotDto.getBusinessData().getValues().add(new KeyValueDto(labels.get(6), input.getCustomerDetails().getCustomerStatus()));
		customerDetailsSnapshotDto.getBusinessData().getValues().add(new KeyValueDto(labels.get(7), DtoGenUtils.numberToStringPercentage(input.getCustomerDetails().getProfitMargin())));
		customerDetailsSnapshotDto.getBusinessData().getValues().add(new KeyValueDto(labels.get(8), DtoGenUtils.numberToStringUIFormated(input.getCustomerDetails().getExposure()[0])));
		customerDetailsSnapshotDto.getBusinessData().getValues().add(new KeyValueDto(labels.get(9), DtoGenUtils.numberToStringUIFormated(input.getCustomerDetails().getExposure()[1])));
		IntStream.range(0, input.getContextInfo().getActivityDescriptions().length).forEach(index -> {
			customerDetailsSnapshotDto.getBusinessData().getValues().add(new KeyValueDto(input.getContextInfo().getActivityDescriptions()[index],
					input.getCustomerDetails().getActivity()[index]));});
		/*SCORING RELATED DATA*/
		customerDetailsSnapshotDto.getCreditScoreData().getValues().add(new KeyValueDto(labels.get(10), input.getCustomerDetails().getBehavioralScore()));
		customerDetailsSnapshotDto.getCreditScoreData().getValues().add(new KeyValueDto(labels.get(11), input.getCustomerDetails().getExtAssessment()));
		customerDetailsSnapshotDto.getCreditScoreData().getValues().add(new KeyValueDto(labels.get(12), input.getCustomerDetails().getQualitativeScore()));
		customerDetailsSnapshotDto.getCreditScoreData().getValues().add(new KeyValueDto(labels.get(13), input.getCustomerDetails().getCreditArkScore()));

		IntStream.range(0, input.getContextInfo().getExposureDescriptions().length).forEach(index -> { customerDetailsSnapshotDto.getExposures().add(new KeyValueDto(input.getContextInfo().getExposureDescriptions()[index], input.getCustomerDetails().getExposure()[index]));});
		IntStream.range(0, input.getContextInfo().getActivityDescriptions().length).forEach(index -> { customerDetailsSnapshotDto.getActivities().add(new KeyValueDto(input.getContextInfo().getActivityDescriptions()[index], input.getCustomerDetails().getActivity()[index]));});
		customerDetailsSnapshotDto.getMitigants().setDescription(LabelEnum.MITIGANTS.getValue());
		IntStream.range(0, input.getContextInfo().getMitigantDescriptions().length).forEach(index -> {
			customerDetailsSnapshotDto.getMitigants().getValues().add(new KeyValueDto(input.getContextInfo().getMitigantDescriptions()[index],
					input.getCustomerDetails().getCreditMitigants()[index]));
		});
		customerDetailsSnapshotDto.getMitigants().getValues().add(new KeyValueDto(LabelEnum.TOTAL.getValue(), Arrays.stream(input.getCustomerDetails().getCreditMitigants()).reduce((a,b) -> a+b).getAsDouble()));

		customerDetailsSnapshotDto.getBuckets().setDescription(input.getContextInfo().getBucketDescriptions()[0]);
		IntStream.range(1, input.getContextInfo().getBucketDescriptions().length).forEach(index -> {
			customerDetailsSnapshotDto.getBuckets().getValues().add(
					new KeyValueDto(input.getContextInfo().getBucketDescriptions()[index],
							input.getCustomerDetails().getPastDueAmount()[index-1]));
		});
		customerDetailsSnapshotDto.getBuckets().getValues().add(new KeyValueDto(LabelEnum.TOTAL.getValue(), Arrays.stream(input.getCustomerDetails().getPastDueAmount()).reduce((a,b) -> a+b).getAsDouble()));


		response.setCustomerDetailsSnapshotDto(customerDetailsSnapshotDto);
	}
	
	public static void createBehavioralData(CustomerDetailsDto response, ClienteleTransformatorInputDto input) {
		CustomerDetailsBehavioralDto behavioralDto = new CustomerDetailsBehavioralDto();
		/*behavioralDto.getBehavioral().setDescription("Behavioral");
		IntStream.range(0, input.getContextInfo().getCriteria().length).forEach(index -> {
			behavioralDto.getBehavioral().getValues().add(new KeyValueDto(
					input.getContextInfo().getCriteria()[index].getDescription(), 
					input.getCustomerDetails().getCriteria()[index], DtoValueDataType.getById(input.getContextInfo().getCriteria()[index].getDataType()).getType()));
		});*/
		response.setCustomerDetailsBehavioralDto(behavioralDto);
	}
	
	public static void createExternalData(CustomerDetailsDto response, ClienteleTransformatorInputDto input) {
		CustomerDetailsExternalDataDto customerDetailsExternalDataDto = new CustomerDetailsExternalDataDto();
		if(null!=input.getCustomerDetails() && null!=input.getCustomerDetails().getQualitativeData()) {
			KeyValuesDto data = new KeyValuesDto();
			Arrays.stream(input.getCustomerDetails().getQualitativeData()).forEach(qualitive -> {
				data.getValues().add(new KeyValueDto(null, qualitive));
			});
			customerDetailsExternalDataDto.setData(data);
		}
		try {
			customerDetailsExternalDataDto.setDateOfAssesment(DateUtils.dateToString(input.getCustomerDetails().getCustomerSince(),
					GenerealConstants.DATE_FORMAT_DD_MM_YYYY.getValue()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		customerDetailsExternalDataDto.setScore(input.getCustomerDetails().getExtAssessment());
		response.setCustomerDetailsExternalDataDto(customerDetailsExternalDataDto);
	}
	
	public static void createQualitivelData(CustomerDetailsDto response, ClienteleTransformatorInputDto input) {
			CustomerDetailsQualitiveDto customerDetailsQualitiveDto = new CustomerDetailsQualitiveDto();
			if(null!= input.getCustomerDetails() && null!=input.getCustomerDetails().getQualitativeData()) {
				KeyValuesDto data = new KeyValuesDto();
				Arrays.stream(input.getCustomerDetails().getQualitativeData()).forEach(qualitive -> {
					data.getValues().add(new KeyValueDto(null, qualitive));
				});
				customerDetailsQualitiveDto.setData(data);
			}
			try {
				customerDetailsQualitiveDto.setDateOfAssesment(
						DateUtils.dateToString(input.getCustomerDetails().getQualitativeSnapshotDate(),
								GenerealConstants.DATE_FORMAT_DD_MM_YYYY.getValue()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			customerDetailsQualitiveDto.setScore(input.getCustomerDetails().getQualitativeScore());
			response.setCustomerDetailsQualitiveDto(customerDetailsQualitiveDto);
	}

	public static void createScenarioStatistics(CustomerDetailsDto response, ClienteleTransformatorInputDto input) {
		if(null!=input.getScenarioCustomerStatistics()) {
			DtoGenUtils.numberToStringUIFormated(input.getScenarioCustomerStatistics().getProposedLimit());
			response.getCustomerScenarioStatisticsDto().getData().setDescription("Projection on customer's statistics");
			response.getCustomerScenarioStatisticsDto().getData().getValues().add(new KeyValueDto("Description", "Value"));
			response.getCustomerScenarioStatisticsDto().getData().getValues().add(new KeyValueDto("Projected turnover",
					DtoGenUtils.numberToStringUIFormated(input.getScenarioCustomerStatistics().getProjectedPurchases())));
			response.getCustomerScenarioStatisticsDto().getData().getValues().add(new KeyValueDto("Maximum acceptable limit",
					DtoGenUtils.numberToStringUIFormated(input.getScenarioCustomerStatistics().getMaxLimit())));
			response.getCustomerScenarioStatisticsDto().getData().getValues().add(new KeyValueDto("Proposed limit",
					DtoGenUtils.numberToStringUIFormated(input.getScenarioCustomerStatistics().getProposedLimit())));
			response.getCustomerScenarioStatisticsDto().getData().getValues().add(new KeyValueDto("Effective Days Sales Outstanding (DSO)",
					DtoGenUtils.numberToStringUIIntFormated(input.getScenarioCustomerStatistics().getEffectedDso())));
			response.getCustomerScenarioStatisticsDto().getData().getValues().add(new KeyValueDto("One year Probability of Default (PD)",
					DtoGenUtils.numberToStringPercentage(input.getScenarioCustomerStatistics().getPd())));
			response.getCustomerScenarioStatisticsDto().getData().getValues().add(new KeyValueDto("Loss Given Default (LGD)",
					DtoGenUtils.numberToStringPercentage(input.getScenarioCustomerStatistics().getLgd())));
			response.getCustomerScenarioStatisticsDto().getData().getValues().add(new KeyValueDto("Funding cost (as a percentage on turnover)",
					DtoGenUtils.numberToStringPercentage(input.getScenarioCustomerStatistics().getFundingCost())));
			response.getCustomerScenarioStatisticsDto().getData().getValues().add(new KeyValueDto("Risk Weighted profit Margin (RWM)",
					DtoGenUtils.numberToStringPercentage(input.getScenarioCustomerStatistics().getRwMargin())));
			response.getCustomerScenarioStatisticsDto().getData().getValues().add(new KeyValueDto("Required capital cushion",
					DtoGenUtils.numberToStringUIFormated(input.getScenarioCustomerStatistics().getWorkingCapital())));
		}
	}
	
	public static void createIfrs(CustomerDetailsDto response, List<CustomerKpis> customerKpi,
			Set<Kpi> kpis) {
		List<KeyValueMultipleDto> valsd = new ArrayList<>();
		if(customerKpi != null) {
			for(CustomerKpis kpi: customerKpi) {
				if(null != kpi && null != kpi.getKpi())  {
					for(KpiKV kpiKV:kpi.getKpi()) {
						kpis.stream().filter(kpi1 -> kpi1.getKpiId().equalsIgnoreCase(kpiKV.getKpiId()))
								.findFirst()
								.ifPresent(kp -> {
									valsd.add(new KeyValueMultipleDto(kp.getDesc(), kp.getDesc(),
											new String[]{kpiKV.getDesc()}));
								});
					}
				}
			}
		}

		CustomerDetailsIFRSDto  customerDetailsIFRSDto = new CustomerDetailsIFRSDto();
		customerDetailsIFRSDto.setValues(valsd);
		response.setCustomerDetailsIFRSDto(customerDetailsIFRSDto);
	}
	

}
