package eu.ark.creditark.services.creditarkservices.services.transformator.reports;

import eu.ark.creditark.services.creditarkservices.dto.ClienteleDistributionItemCollectionDto;
import eu.ark.creditark.services.creditarkservices.dto.reports.*;
import eu.ark.creditark.services.creditarkservices.utils.DtoGenUtils;
import eu.ark.creditark.services.creditarkservices.utils.StringGenUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("CLIENT_STATISTICS_REPORT")
public class ClienteleStatisticsReportTransformator implements ReportTransformator {
    @Override
    public List<ReportGraphSection> transform(ReportInput input) {
        List<ReportGraphSection> response = new ArrayList(1);
        input.getClienteleStatisticsGraphDto().getLineDataPointCollection()
                .getValues().stream().forEach(lineItem -> {
            List<List<Double>> seriesExp = new ArrayList<>();
            List<BarDatasourceDataItem> barDatasourceDataItemsExp = new ArrayList<>();

                ReportGraphSection section = new ReportSectionLine();
                section.applyTitle(lineItem.getLabel());

                List<String> labels = new ArrayList<>(1);
                labels.add(lineItem.getLabel());
                labels.add("Date");
                section.applyLabels(labels);
                List<String> fields = new ArrayList<>(1);
                fields.add(lineItem.getDescription());
                fields.add("value");
                section.applyFields(fields);
                Map<String, Double> distro = new HashMap();

                List<Double> serie = new ArrayList<>();

                for(int index = 0; index< input.getClienteleStatisticsGraphDto().getLineDataPointCollection().getLabels().size(); index++) {
                    barDatasourceDataItemsExp.add(new BarDatasourceDataItem(
                            input.getClienteleStatisticsGraphDto().getLineDataPointCollection().getLabels().get(index),
                            new Double[]{lineItem.getValues().get(index)}
                    ));
                    serie.add(lineItem.getValues().get(index));
                }


                seriesExp.add(serie);

            section.applyBarDSItems(barDatasourceDataItemsExp);
            section.applySeries(seriesExp);
            response.add(section);
        });

        List<BarDatasourceDataItem> barDatasourceDataItemsTable = new ArrayList<>();
        List<List<String>> seriesExpTable = new ArrayList<>();
        ReportGraphSection section = new ReportSectionTable();
        input.getClienteleStatisticsDto().getClientStatisticItemDtos().stream().forEach(statistic -> {
            String prev = StringGenUtils.formatStringToDoubleFormat(statistic.getPrev());
            String last = StringGenUtils.formatStringToDoubleFormat(statistic.getLast());
            barDatasourceDataItemsTable.add(new BarDatasourceDataItem(statistic.getDescription(),
                    new String[]{last, prev}));
            List<String> serie = new ArrayList<>();
            serie.add(last);
            serie.add(prev);
            seriesExpTable.add(serie);
        });
        List<String> labels = new ArrayList<>(1);
        labels.add("Statistics");
        labels.add(input.getClienteleStatisticsDto().getLastPeriod());
        labels.add(input.getClienteleStatisticsDto().getPreviousPeriod());
        List<String> fields = new ArrayList<>(1);
        fields.add("stat");
        fields.add("last");
        fields.add("prev");
        section.applyFields(fields);
        section.applyLabels(labels);
        section.applySeriesText(seriesExpTable);
        section.applyBarDSItems(barDatasourceDataItemsTable);
        response.add(section);
        return response;
    }
}
