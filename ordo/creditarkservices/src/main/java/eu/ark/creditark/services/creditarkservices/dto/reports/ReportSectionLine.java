package eu.ark.creditark.services.creditarkservices.dto.reports;

import eu.ark.creditark.services.creditarkservices.dto.KeyValueDto;
import eu.ark.creditark.services.creditarkservices.enums.ReportSectionType;
import net.sf.dynamicreports.report.builder.chart.BarChartBuilder;
import net.sf.dynamicreports.report.builder.chart.LineChartBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.chart.DRIChartCustomizer;
import net.sf.jasperreports.engine.JRDataSource;
import org.codehaus.jackson.node.BigIntegerNode;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static net.sf.dynamicreports.report.builder.DynamicReports.cht;

public class ReportSectionLine extends ReportGraphSection {

    public ReportSectionLine() {
        super(ReportSectionType.BARGRAPH);
    }


    @Override
    public LineChartBuilder create() {
        LineChartBuilder bar2 = cht.lineChart()
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
        bar2.customizers(new LineCustomizer());
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
                        BigDecimal.valueOf(itm.getValue()[0]).toBigInteger());
            });
            return dataSource;
    }

    @Override
    public JRDataSource createSeries() {

        DRDataSource dataSource = new DRDataSource(  this.fields.stream().toArray(String[]::new));
        barDSItems.stream().forEach(itm -> {
            dataSource.add(itm.getId(),
                    BigDecimal.valueOf(itm.getValue()[0]).toBigInteger());
        });
        return dataSource;
    }

    private class LineCustomizer implements DRIChartCustomizer, Serializable {
        public void customize(JFreeChart chart, ReportParameters reportParameters) {
            CategoryAxis domainAxis = chart.getCategoryPlot().getDomainAxis();
            domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));

        }
    }
}
