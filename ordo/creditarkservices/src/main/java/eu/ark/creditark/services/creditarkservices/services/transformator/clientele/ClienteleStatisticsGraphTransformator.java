package eu.ark.creditark.services.creditarkservices.services.transformator.clientele;

import java.text.ParseException;
import java.util.Arrays;
import java.util.stream.IntStream;

import eu.ark.creditark.services.creditarkservices.config.AppPropertiesConfig;
import eu.ark.creditark.services.creditarkservices.enums.GenerealConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.ark.creditark.services.creditarkservices.dto.ClienteleStatisticsGraphDto;
import eu.ark.creditark.services.creditarkservices.dto.graph.LineGraphValues;
import eu.ark.creditark.services.creditarkservices.dto.graph.LineGraphValuesCollection;
import eu.ark.creditark.services.creditarkservices.dto.input.ClienteleTransformatorInputDto;
import eu.ark.creditark.services.creditarkservices.utils.DateUtils;


@Component("CLIENT_STATISTICS_GRAPH")
public class ClienteleStatisticsGraphTransformator implements ClienteleTransformator {

	@Autowired
	private AppPropertiesConfig appProperties;

	@SuppressWarnings("unchecked")
	@Override
	public ClienteleStatisticsGraphDto transform(ClienteleTransformatorInputDto input) {
		ClienteleStatisticsGraphDto response = new ClienteleStatisticsGraphDto();
		LineGraphValuesCollection graph = new LineGraphValuesCollection();
		//Arrays.stream(input.getClienteleStatistics().getPeriodSnapshotDate());

		graph.getValues().add(new LineGraphValues("periodLimit", appProperties.getReportLabels().get(17)));
		graph.getValues().add(new LineGraphValues("periodExposure", appProperties.getReportLabels().get(18)));
		graph.getValues().add(new LineGraphValues("periodTurnover", appProperties.getReportLabels().get(19)));
		graph.getValues().add(new LineGraphValues("periodPayments", appProperties.getReportLabels().get(20)));
		graph.getValues().add(new LineGraphValues("periodPastdue", appProperties.getReportLabels().get(21)));
		if(null!=input.getClienteleStatistics().getPeriodSnapshotDate())
		IntStream.range(0, input.getClienteleStatistics().getPeriodSnapshotDate().length).forEach(index -> {
			try {
				graph.getLabels().add(DateUtils.dateToString(input.getClienteleStatistics().getPeriodSnapshotDate()[index], GenerealConstants.DATE_FORMAT_DD_MM_YYYY.getValue()));
			} catch (ParseException e) {}
			graph.getValues().get(0).getValues().add(Double.parseDouble(String.format("%.2f", input.getClienteleStatistics().getPeriodLimit()[index]/1000)));
			graph.getValues().get(1).getValues().add(Double.parseDouble(String.format("%.2f", input.getClienteleStatistics().getPeriodExposure()[index]/1000)));
			graph.getValues().get(2).getValues().add(Double.parseDouble(String.format("%.2f", input.getClienteleStatistics().getPeriodTurnover()[index]/1000)));
			graph.getValues().get(3).getValues().add(Double.parseDouble(String.format("%.2f", input.getClienteleStatistics().getPeriodPayments()[index]/1000)));
			graph.getValues().get(4).getValues().add(Double.parseDouble(String.format("%.2f", input.getClienteleStatistics().getPeriodPastDue()[index]/1000)));
	});
		response.setLineDataPointCollection(graph);
		return response;
	}

    
}
