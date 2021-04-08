package eu.ark.creditark.services.creditarkservices.services.transformator.reports;

import eu.ark.creditark.services.creditarkservices.config.AppPropertiesConfig;
import eu.ark.creditark.services.creditarkservices.dto.reports.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("CUSTOMER_DETAILS_REPORT")
public class CustomerDetailsReportTransformator implements ReportTransformator {

    @Autowired
    AppPropertiesConfig appPropertiesConfig;
    @Override
    public List<ReportGraphSection> transform(ReportInput input) {
        List<ReportGraphSection> response = new ArrayList(1);
        //---------------HIGHLIGHTS--------------------
        ReportGraphSection section = new ReportSectionTable();
        section.applyTitle(appPropertiesConfig.getReportLabels().get(0));
        List<String> labels = new ArrayList<>(1);
        labels.add("");
        labels.add("");
        List<String> fields = new ArrayList<>(1);
        fields.add("key");
        fields.add("val");
        section.applyFields(fields);
        section.applyLabels(labels);
        List<BarDatasourceDataItem> barDatasourceDataItemsTable = new ArrayList<>();
        List<List<String>> seriesExpTable = new ArrayList<>();
        barDatasourceDataItemsTable.add(new BarDatasourceDataItem(appPropertiesConfig.getReportLabels().get(24),
                new String[]{""}));
        input.getCustomerDetailsDto().getCustomerDetailsSnapshotDto().getIdData().getValues().stream().forEach(itm -> {
            barDatasourceDataItemsTable.add(new BarDatasourceDataItem(itm.getKey(),
                    new String[]{itm.getValue()}));
            List<String> serie = new ArrayList<>();
            serie.add(itm.getValue());
            seriesExpTable.add(serie);
        });
        section.applySeriesText(seriesExpTable);
        section.applyBarDSItems(barDatasourceDataItemsTable);
        response.add(section);

        section = new ReportSectionTable();
        labels = new ArrayList<>(1);
        labels.add("");
        labels.add("");
        fields = new ArrayList<>(1);
        fields.add("key");
        fields.add("val");
        section.applyFields(fields);
        section.applyLabels(labels);
        List<BarDatasourceDataItem> barDatasourceDataItemsTable2 = new ArrayList<>();
        List<List<String>> seriesExpTable2 = new ArrayList<>();
        barDatasourceDataItemsTable2.add(new BarDatasourceDataItem(
                appPropertiesConfig.getReportLabels().get(22),
                new String[]{""}));
        input.getCustomerDetailsDto().getCustomerDetailsSnapshotDto().getBusinessData().getValues().stream().forEach(itm -> {

            barDatasourceDataItemsTable2.add(new BarDatasourceDataItem(itm.getKey(),
                    new String[]{itm.getValue()}));
            List<String> serie = new ArrayList<>();
            serie.add(itm.getValue());
            seriesExpTable2.add(serie);
        });
        section.applySeriesText(seriesExpTable2);
        section.applyBarDSItems(barDatasourceDataItemsTable2);
        response.add(section);


        section = new ReportSectionTable();
        labels = new ArrayList<>(1);
        labels.add("");
        labels.add("");
        fields = new ArrayList<>(1);
        fields.add("key");
        fields.add("val");
        section.applyFields(fields);
        section.applyLabels(labels);
        List<BarDatasourceDataItem> barDatasourceDataItemsTable3 = new ArrayList<>();
        List<List<String>> seriesExpTable3 = new ArrayList<>();
        barDatasourceDataItemsTable3.add(new BarDatasourceDataItem(
                appPropertiesConfig.getReportLabels().get(23),
                new String[]{""}));
        input.getCustomerDetailsDto().getCustomerDetailsSnapshotDto().getCreditScoreData().getValues().stream().forEach(itm -> {
            barDatasourceDataItemsTable3.add(new BarDatasourceDataItem(itm.getKey(),
                    new String[]{itm.getValue()}));
            List<String> serie = new ArrayList<>();
            serie.add(itm.getValue());
            seriesExpTable3.add(serie);
        });
        section.applySeriesText(seriesExpTable3);
        section.applyBarDSItems(barDatasourceDataItemsTable3);
        response.add(section);


        //----------------ANALYSIS-------------------
        section = new ReportSectionTable();
        labels = new ArrayList<>(1);
        labels.add("");
        labels.add("");
        fields = new ArrayList<>(1);
        fields.add("key");
        fields.add("val");
        section.applyFields(fields);
        section.applyLabels(labels);
        section.applyTitle(appPropertiesConfig.getReportLabels().get(25));
        List<BarDatasourceDataItem> barDatasourceDataItemsTableAnalysis = new ArrayList<>();
        List<List<String>> seriesExpTableAnalysis = new ArrayList<>();
        input.getCustomerDetailsDto().getCustomerDetailsSnapshotDto().getBuckets().getValues().stream().forEach(itm -> {
            barDatasourceDataItemsTableAnalysis.add(new BarDatasourceDataItem(itm.getKey(),
                    new String[]{itm.getValue()}));
            List<String> serie = new ArrayList<>();
            serie.add(itm.getValue());
            seriesExpTableAnalysis.add(serie);
        });
        section.applySeriesText(seriesExpTableAnalysis);
        section.applyBarDSItems(barDatasourceDataItemsTableAnalysis);
        response.add(section);

        //----------------STATISTICS-------------------
        section = new ReportSectionTable();
        labels = new ArrayList<>(1);
        labels.add("");
        labels.add("");
        labels.add("");
        fields = new ArrayList<>(1);
        fields.add("key");
        fields.add("val");
        fields.add("val1");
        section.applyFields(fields);
        section.applyLabels(labels);
        section.applyTitle(appPropertiesConfig.getReportLabels().get(26));
        List<BarDatasourceDataItem> barDatasourceDataItemsTableStats = new ArrayList<>();
        List<List<String>> seriesExpTableStats = new ArrayList<>();
        input.getCustomerDetailsDto().getCustomerDetailsStatisticsDto().getStatistics().forEach(itm -> {
            barDatasourceDataItemsTableStats.add(new BarDatasourceDataItem(itm.getKey(),
                    new String[]{itm.getValues()[0], itm.getValues()[1]}));
            List<String> serie = new ArrayList<>();
            serie.add(itm.getValues()[0]);
            serie.add(itm.getValues()[1]);
            seriesExpTableStats.add(serie);
        });
        section.applySeriesText(seriesExpTableStats);
        section.applyBarDSItems(barDatasourceDataItemsTableStats);
        response.add(section);
        //----------------PROVISIONS-------------------
        section = new ReportSectionTable();
        section.applyFields(fields);
        section.applyLabels(labels);
        section.applyTitle(appPropertiesConfig.getReportLabels().get(32));
        List<BarDatasourceDataItem> barDatasourceDataItemsTableProv = new ArrayList<>();
        List<List<String>> seriesExpTableProv = new ArrayList<>();
        labels = new ArrayList<>(1);
        labels.add(appPropertiesConfig.getReportLabels().get(27));
        labels.add(appPropertiesConfig.getReportLabels().get(28));
        labels.add(appPropertiesConfig.getReportLabels().get(29));
        labels.add(appPropertiesConfig.getReportLabels().get(30));
        labels.add(appPropertiesConfig.getReportLabels().get(31));
        fields = new ArrayList<>(1);
        fields.add("key");
        fields.add("val");
        fields.add("val1");
        fields.add("val2");
        fields.add("val3");
        section.applyFields(fields);
        section.applyLabels(labels);
        input.getCustomerDetailsDto().getCustomerDetailsIFRSDto().getValues().forEach(itm -> {
            barDatasourceDataItemsTableProv.add(new BarDatasourceDataItem(itm.getKey(),
                    new String[]{itm.getValues()[0], itm.getValues()[1], itm.getValues()[2], itm.getValues()[3]}));
            List<String> serie = new ArrayList<>();
            serie.add(itm.getValues()[0]);
            serie.add(itm.getValues()[1]);
            serie.add(itm.getValues()[2]);
            serie.add(itm.getValues()[3]);
            seriesExpTableProv.add(serie);
        });
        section.applySeriesText(seriesExpTableProv);
        section.applyBarDSItems(barDatasourceDataItemsTableProv);
        response.add(section);

        return response;
    }
}
