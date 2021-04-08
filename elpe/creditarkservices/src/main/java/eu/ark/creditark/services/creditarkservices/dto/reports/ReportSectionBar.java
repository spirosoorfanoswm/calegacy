package eu.ark.creditark.services.creditarkservices.dto.reports;

import eu.ark.creditark.services.creditarkservices.dto.KeyValueDto;
import eu.ark.creditark.services.creditarkservices.enums.ReportSectionType;
import net.sf.dynamicreports.report.builder.chart.BarChartBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

import java.util.List;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

public class ReportSectionBar extends ReportGraphSection {

    public ReportSectionBar() {
        super(ReportSectionType.BARGRAPH);
    }


    @Override
    public BarChartBuilder create() {
        BarChartBuilder bar2 = cht.barChart()
                .setCategory(createTextColumnBuilder(this.fields.get(0),
                        this.labels.get(0), false))
                .series(cht.serie(createTextColumnBuilder(this.fields.get(1),
                        this.labels.get(1), true)))
                .setTitle(null==this.title ? "" : this.title)
                .setShowValues(false)
                .setDataSource(createSeries(
                        this.labels,
                        this.series,
                        this.fields.stream().toArray(String[]::new)));
        if(fields.size() > 1)
            for(int i=2; i<fields.size(); i++) {
                bar2.addSerie(cht.serie(
                        createTextColumnBuilder(this.fields.get(i),
                                this.labels.get(i), true)));
            }

        return bar2;
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
                        itm.getValue()[0].intValue(),
                        itm.getValue()[1].intValue());
            });
            return dataSource;
    }

    @Override
    public JRDataSource createSeries() {

        DRDataSource dataSource = new DRDataSource(  this.fields.stream().toArray(String[]::new));
        barDSItems.stream().forEach(itm -> {
            dataSource.add(itm.getId(),
                    itm.getValue()[0].intValue(),
                    itm.getValue()[1].intValue());
        });
        return dataSource;
    }


}
