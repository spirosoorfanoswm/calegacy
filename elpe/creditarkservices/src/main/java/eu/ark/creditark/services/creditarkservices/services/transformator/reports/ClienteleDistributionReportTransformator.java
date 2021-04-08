package eu.ark.creditark.services.creditarkservices.services.transformator.reports;

import eu.ark.creditark.services.creditarkservices.dto.ClienteleDistributionItemCollectionDto;
import eu.ark.creditark.services.creditarkservices.dto.reports.BarDatasourceDataItem;
import eu.ark.creditark.services.creditarkservices.dto.reports.ReportInput;
import eu.ark.creditark.services.creditarkservices.dto.reports.ReportGraphSection;
import eu.ark.creditark.services.creditarkservices.dto.reports.ReportSectionBar;
import eu.ark.creditark.services.creditarkservices.utils.DtoGenUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("CLIENT_DISTRIBUTION_REPORT")
public class ClienteleDistributionReportTransformator implements ReportTransformator {
    @Override
    public List<ReportGraphSection> transform(ReportInput input) {
        List<List<Double>> series = new ArrayList<>();
        List<List<Double>> seriesExp = new ArrayList<>();
        ReportGraphSection section = new ReportSectionBar();
        ReportGraphSection sectionExp = new ReportSectionBar();
        List<ReportGraphSection> response = new ArrayList(1);
        List<BarDatasourceDataItem> barDatasourceDataItems = new ArrayList<>();
        List<BarDatasourceDataItem> barDatasourceDataItemsExp = new ArrayList<>();
        input.getClienteleDistributionDto()
                .getClienteleDistributionList().stream().forEach(lnd -> {
                    List<Double> serie = new ArrayList<>();
                    lnd.getClienteleDistributionItems().stream().forEach(cms -> {
                        serie.add(Double.parseDouble(""+cms.getCustomers()));
                    });
            series.add(serie);
        });

        input.getClienteleDistributionDto()
                .getClienteleDistributionList().stream().forEach(lnd -> {
            List<Double> serie = new ArrayList<>();
            lnd.getClienteleDistributionItems().stream().forEach(cms -> {
                serie.add(Double.parseDouble(cms.getExposure()));
            });
            seriesExp.add(serie);
        });
        ClienteleDistributionItemCollectionDto firstDistro = input.getClienteleDistributionDto()
                .getClienteleDistributionList().get(0);

        ClienteleDistributionItemCollectionDto secDistro = input.getClienteleDistributionDto()
                .getClienteleDistributionList().get(1);

        Map<String, Double[]> distro = new HashMap();
        Map<String, Double[]> distroExp = new HashMap();


        firstDistro.getClienteleDistributionItems().stream().forEach(itm -> {
            distro.put(itm.getCas(), new Double[2]);
            distroExp.put(itm.getCas(), new Double[2]);
        });

        firstDistro.getClienteleDistributionItems().stream().forEach(itm -> {
            distro.get(itm.getCas())[0] = Double.parseDouble(""+itm.getCustomers());
            distroExp.get(itm.getCas())[0] = DtoGenUtils.stringToDouble(itm.getExposure());
        });

        secDistro.getClienteleDistributionItems().stream().forEach(itm -> {
            distro.get(itm.getCas())[1] = Double.parseDouble(""+itm.getCustomers());
            distroExp.get(itm.getCas())[1] = DtoGenUtils.stringToDouble(itm.getExposure());
        });

        distro.entrySet().stream().forEach(itm -> {
            barDatasourceDataItems.add(new BarDatasourceDataItem(itm.getKey(), itm.getValue()));
        });

        distroExp.entrySet().stream().forEach(itm -> {
            barDatasourceDataItemsExp.add(new BarDatasourceDataItem(itm.getKey(), itm.getValue()));
        });

        List<String> labels = new ArrayList<>(2);
        labels.add("CAS");
        labels.add(firstDistro.getDate());
        labels.add(secDistro.getDate());

        List<String> fields = new ArrayList<>(2);
        fields.add("cas");
        fields.add("customers");
        fields.add("customers_1");

        List<String> fieldsExp = new ArrayList<>(2);
        fieldsExp.add("cas");
        fieldsExp.add("customers");
        fieldsExp.add("customers_1");

        section.applyLabels(labels);
        section.applyFields(fields);
        section.applyTitles(labels);
        section.applySeries(series);
        section.applyBarDSItems(barDatasourceDataItems);
        section.applyTitle("Customers");

        sectionExp.applyLabels(labels);
        sectionExp.applyFields(fieldsExp);
        sectionExp.applyTitles(labels);
        sectionExp.applySeries(seriesExp);
        sectionExp.applyBarDSItems(barDatasourceDataItemsExp);
        sectionExp.applyTitle("Exposure");

        response.add(section);
        response.add(sectionExp);
        return response;
    }
}
