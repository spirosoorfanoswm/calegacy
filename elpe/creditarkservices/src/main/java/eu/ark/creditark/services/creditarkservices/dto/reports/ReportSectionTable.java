package eu.ark.creditark.services.creditarkservices.dto.reports;

import eu.ark.creditark.services.creditarkservices.dto.KeyValueDto;
import eu.ark.creditark.services.creditarkservices.enums.ReportSectionType;
import net.sf.dynamicreports.report.builder.chart.LineChartBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

import java.util.List;

import static net.sf.dynamicreports.report.builder.DynamicReports.cht;

public class ReportSectionTable extends ReportGraphSection implements
        ReportSectionTableActions{

    public ReportSectionTable() {
        super(ReportSectionType.TABLE);
    }


    @Override
    public LineChartBuilder create() {

        return null;
    }



    @Override
    public void applyLabels(List<String> labels) {
        this.labels = labels;
    }

    @Override
    public void applySeries(List<List<Double>> series) {
        this.series = series;
    }

    @Override
    public void applySeriesText(List<List<String>> series) {
        this.seriesText = series;
    }

    @Override
    public void applyTableRows(List<KeyValueDto> tableRows) {

    }

    @Override
    public void applyFields(List<String> fields) {
        this.fields = fields;
    }

    @Override
    public void applyTitles(List<String> fields) {
        this.titles = titles;
    }

    @Override
    public void applyBarDSItems(List<BarDatasourceDataItem> barDatasourceDataItems) {
        this.barDSItems = barDatasourceDataItems;
    }

    @Override
    public void applyTitle(String title) {
        this.title = title;
    }

    @Override
    public JRDataSource createSeries(List<String> labels, List<List<Double>> series, String[] fields) {
            DRDataSource dataSource = new DRDataSource(fields);
            barDSItems.stream().forEach(itm -> {
                dataSource.add(itm.getId(),
                        itm.getValue()[0].intValue());
            });
            return dataSource;
    }

    @Override
    public JRDataSource createSeries() {

        DRDataSource dataSource = new DRDataSource(  this.fields.stream().toArray(String[]::new));
        barDSItems.stream().forEach(itm -> {
            dataSource.add(itm.getId(),
                    itm.getValue()[0].intValue());
        });
        return dataSource;
    }


    @Override
    public JRDataSource createSeriesTable(List<String> labels, List<List<String>> series, String[] fields) {
        DRDataSource dataSource = new DRDataSource(  this.fields.stream().toArray(String[]::new));
        barDSItems.stream().forEach(itm -> {
            dataSource.add(itm.getId(),
                    itm.getStringValues()[0],
                    itm.getStringValues()[1]);
        });
        return dataSource;
    }
}
