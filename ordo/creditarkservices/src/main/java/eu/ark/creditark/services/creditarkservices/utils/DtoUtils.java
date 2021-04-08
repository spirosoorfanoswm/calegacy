package eu.ark.creditark.services.creditarkservices.utils;

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
		try {
			customerDetailsSnapshotDto.getIdData().getValues().add(new KeyValueDto(labels.get(2), DateUtils.dateToString(DateUtils.formatDate(
					new Date(input.getCustomerDetails().getCustomerSince().getTime()), GenerealConstants.DATE_FORMAT_DD_MM_YYYY.getValue()
			), GenerealConstants.DATE_FORMAT_DD_MM_YYYY.getValue())));

		} catch (ParseException e) {
		}
		customerDetailsSnapshotDto.getIdData().getValues().add(new KeyValueDto(labels.get(3), input.getCustomerDetails().getVatNumber()));
		//customerDetailsSnapshotDto.getIdData().getValues().add(new KeyValueDto(labels.get(4), input.getCustomerDetails().getIndustryId()));
		//customerDetailsSnapshotDto.getIdData().getValues().add(new KeyValueDto(labels.get(5), input.getCustomerDetails().getAreaID()));

		/*BUSINESS RELATED DATA*/
		//customerDetailsSnapshotDto.getBusinessData().getValues().add(new KeyValueDto(labels.get(6), input.getCustomerDetails().getCustomerStatus()));
		customerDetailsSnapshotDto.getBusinessData().getValues().add(new KeyValueDto(labels.get(7), DtoGenUtils.numberToStringPercentage(input.getCustomerDetails().getProfitMargin())));
		customerDetailsSnapshotDto.getBusinessData().getValues().add(new KeyValueDto(labels.get(8), DtoGenUtils.numberToStringUIFormated(input.getCustomerDetails().getExposure()[0])));
		customerDetailsSnapshotDto.getBusinessData().getValues().add(new KeyValueDto(labels.get(9), DtoGenUtils.numberToStringUIFormated(input.getCustomerDetails().getExposure()[1])));
		IntStream.range(0, input.getContextInfo().getActivityDescriptions().length).forEach(index -> {
			customerDetailsSnapshotDto.getBusinessData().getValues().add(new KeyValueDto(input.getContextInfo().getActivityDescriptions()[index],
					input.getCustomerDetails().getActivity()[index]));});
		/*SCORING RELATED DATA*/
		customerDetailsSnapshotDto.getCreditScoreData().getValues().add(new KeyValueDto(labels.get(20), input.getCustomerDetails().getCreditArkScore()));
		customerDetailsSnapshotDto.getCreditScoreData().getValues().add(new KeyValueDto(labels.get(21), input.getCustomerDetails().getExtAssessment()));
		customerDetailsSnapshotDto.getCreditScoreData().getValues().add(new KeyValueDto(labels.get(12), input.getCustomerDetails().getQualitativeScore()));
		customerDetailsSnapshotDto.getCreditScoreData().getValues().add(new KeyValueDto(labels.get(10), input.getCustomerDetails().getBehavioralScore()));
		customerDetailsSnapshotDto.getCreditScoreData().getValues().add(new KeyValueDto(labels.get(22), DtoGenUtils.numberToStringUIFormated(input.getCustomerDetails().getAverageDaysToPay())));
		customerDetailsSnapshotDto.getCreditScoreData().getValues().add(new KeyValueDto(labels.get(23), DtoGenUtils.numberToStringUIFormated(input.getCustomerDetails().getAverageDaysUnpaid())));

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
	
	public static void createIfrs(CustomerDetailsDto response, ClienteleTransformatorInputDto input) {
		if(null== input
				|| null== input.getCustomerDetails()
				|| null== input.getCustomerDetails().getCreditMitigants()
				|| null== input.getCustomerDetails().getProvisions()) return;
		CustomerDetails d = input.getCustomerDetails();
		double pd = d.getPd();
		double lifePD=0, provisions = 0, newProvisions = 0, difference = 0;
		double exposure = d.getExposure()[0];
		double[] creditMitigants = d.getCreditMitigants();
		double[] prov = d.getProvisions();
		MitigantExposureDto cur;
		
		lifePD = (pd);
		provisions = (prov[0]);
		newProvisions = (prov[1]);
		difference = (prov[1] - prov[0]);
		String[] mitigantDescriptions = input.getContextInfo().getMitigantDescriptions();
		double[] mitigantRecoveryRates = input.getContextInfo().getMitigantRecoveryRates();
		
		// Calculate ECLs
		int i;
		MitigantExposureDto[]  mitigantExposures = new MitigantExposureDto[creditMitigants.length + 1];
		for (i = 1; i <= creditMitigants.length; i++) {
			cur = new MitigantExposureDto();
			cur.setInx(i);
			cur.setMitigantValue(creditMitigants[i - 1]);
			cur.setLgd(1 - mitigantRecoveryRates[i]);
			mitigantExposures[i] = cur;
		}
		// Add item for uncovered
		cur = new MitigantExposureDto();
		cur.setInx(0);
		cur.setMitigantValue(exposure);
		cur.setLgd(1 - mitigantRecoveryRates[0]);
		mitigantExposures[0] = cur;

		Arrays.sort(mitigantExposures, new Comparator<MitigantExposureDto>(){
			@Override
			public int compare(MitigantExposureDto o1, MitigantExposureDto o2) {
				return o1.getLgd() < o2.getLgd() ? -1 : o1.getLgd() > o2.getLgd() ? 1 :
					o1.getMitigantValue() > o2.getMitigantValue() ? -1 :
					o1.getMitigantValue() < o2.getMitigantValue() ? 1 : 0;
			}});

		double totalAmount = 0;
		double totalSeverity = 0;
		double totalProvision = 0;
		double temp;
		List<KeyValueMultipleDto> valsd = new ArrayList<>();
		// Set the values on the table
		for (i = 0; i < mitigantExposures.length; i++) {
			cur = mitigantExposures[i];
			totalAmount += cur.getMitigantValue();
			double curExp = Math.max(cur.getMitigantValue() + Math.min(exposure - totalAmount, 0), 0);
			KeyValueMultipleDto vals = new KeyValueMultipleDto(cur.getInx()==0?"Uncovered":mitigantDescriptions[cur.getInx()-1], null, new String[5]);
			vals.getValues()[0] = DtoGenUtils.numberToString(curExp);

			temp = curExp * (1 - mitigantRecoveryRates[cur.getInx()]);
			vals.getValues()[2] = DtoGenUtils.numberToString(temp);
			totalSeverity += temp;
			temp = pd * temp;
			vals.getValues()[3] = DtoGenUtils.numberToString(temp);
			totalProvision += temp;
			vals.getValues()[4] = ""+cur.getInx();//cur.getInx()==0?"Uncovered":mitigantDescriptions[cur.getInx()-1];
			vals.getValues()[1] =DtoGenUtils.numberToStringPercentage(cur.getLgd());// DtoGenUtils.numberToStringPercentage(mitigantRecoveryRates[cur.getLgd())]);//Strings.EMPTY + cur.getInx();
			//vals.getValues()[5] = ""+cur.getInx();
			valsd.add(vals);
		}

		KeyValueMultipleDto vals = new KeyValueMultipleDto("Totals", null, new String[5]);
		vals.getValues()[0] = DtoGenUtils.numberToString(valsd.stream().mapToDouble(X -> Double.parseDouble(X.getValues()[0])).sum());
		vals.getValues()[2] = DtoGenUtils.numberToString(valsd.stream().mapToDouble(X -> Double.parseDouble(X.getValues()[2])).sum());
		vals.getValues()[3] = DtoGenUtils.numberToString(valsd.stream().mapToDouble(X -> Double.parseDouble(X.getValues()[3])).sum());
		vals.getValues()[1] = "";
		vals.getValues()[4] = DtoGenUtils.numberToString(valsd.size()+1);
		//vals.getValues()[5] = DtoGenUtils.numberToString(valsd.size()+1);
		valsd.add(vals);
		valsd.sort(new Comparator<KeyValueMultipleDto>() {
			@Override
			public int compare(KeyValueMultipleDto o1, KeyValueMultipleDto o2) {
				return Integer.valueOf(o1.getValues()[4]).compareTo(Integer.valueOf(o2.getValues()[4]));
			}
		});

		for(int j=0; j<valsd.size()-1; j++) {

			if(null!=valsd.get(j) && null!=valsd.get(j).getValues()) {
				if (null != valsd.get(j).getValues()[0])
					valsd.get(j).getValues()[0] = DtoGenUtils.numberToStringUIFormated(Double.parseDouble(valsd.get(j).getValues()[0]));
				if (null != valsd.get(j).getValues()[2])
					valsd.get(j).getValues()[2] = DtoGenUtils.numberToStringUIFormated(Double.parseDouble(valsd.get(j).getValues()[2]));
				if (null != valsd.get(j).getValues()[3])
					valsd.get(j).getValues()[3] = DtoGenUtils.numberToStringUIFormated(Double.parseDouble(valsd.get(j).getValues()[3]));
			}
		}
		valsd.get(valsd.size()-1).getValues()[0] = DtoGenUtils.numberToStringUIFormated(Double.parseDouble(valsd.get(valsd.size()-1).getValues()[0]));
		valsd.get(valsd.size()-1).getValues()[2] = DtoGenUtils.numberToStringUIFormated(Double.parseDouble(valsd.get(valsd.size()-1).getValues()[2]));
		valsd.get(valsd.size()-1).getValues()[3] = DtoGenUtils.numberToStringUIFormated(Double.parseDouble(valsd.get(valsd.size()-1).getValues()[3]));

		CustomerDetailsIFRSDto  customerDetailsIFRSDto = new CustomerDetailsIFRSDto();
		customerDetailsIFRSDto.setDifference(DtoGenUtils.numberToStringUIFormated(difference));
		customerDetailsIFRSDto.setLifePd(DtoGenUtils.numberToStringPercentage(lifePD));
		customerDetailsIFRSDto.setProvisions(DtoGenUtils.numberToStringUIFormated(provisions));
		customerDetailsIFRSDto.setNewProvisions(DtoGenUtils.numberToStringUIFormated(newProvisions));
		customerDetailsIFRSDto.setValues(valsd);
		response.setCustomerDetailsIFRSDto(customerDetailsIFRSDto);
	}
	

}
