package eu.ark.creditark.services.creditarkservices.dto.reports;

import eu.ark.creditark.services.creditarkservices.dto.KeyValueDto;
import eu.ark.creditark.services.creditarkservices.enums.ReportSectionType;
import net.sf.dynamicreports.report.builder.chart.AbstractCategoryChartBuilder;
import net.sf.dynamicreports.report.builder.chart.BarChartBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;
import org.springframework.security.core.parameters.P;

import java.util.Arrays;
import java.util.List;

import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

public abstract class ReportGraphSection {
    public static final StyleBuilder boldStyle = stl.style().bold();;
    private ReportSectionType reportSectionType;
    public String title;
    public List<String> labels;
    public List<String> fields;
    public List<String> titles;
    public List<List<Double>> series;
    public List<List<String>> seriesText;
    protected List<KeyValueDto> tableRows;
    public List<BarDatasourceDataItem> barDSItems;
    public ReportGraphSection(ReportSectionType reportSectionType) {
        this.reportSectionType = reportSectionType;
    }

    public abstract AbstractCategoryChartBuilder create();
    public abstract void applyLabels(List<String> labels);
    public abstract void applySeries(List<List<Double>> series);
    public abstract void applySeriesText(List<List<String>> series);
    public abstract void applyTableRows(List<KeyValueDto> tableRows);
    public abstract void applyFields(List<String> fields);
    public abstract void applyTitles(List<String> titles);
    public abstract void applyBarDSItems(List<BarDatasourceDataItem> barDatasourceDataItems);
    public abstract void applyTitle(String title);

    public abstract JRDataSource createSeries(List<String> labels,
                              List<List<Double>> series,
                              String[] fields);

    public abstract JRDataSource createSeries();



    public TextColumnBuilder createTextColumnBuilder(String field,
                                              String label,
                                              boolean numeric) {
        if(numeric) {
            return col.column(label, field, type.bigIntegerType());
        } else {
            return col.column(label, field, type.stringType());
        }
    }

    public TextColumnBuilder createTextColumnBuilder(String field,
                                                     String label,
                                                     boolean numeric,
                                                     boolean bold) {
        if(numeric) {
            return col.column(label, field, type.bigIntegerType());
        } else {
            return bold  ? col.column(label, field, type.stringType()).setStyle(boldStyle) :
                    col.column(label, field, type.stringType());
        }
    }

    public ReportSectionType getReportSectionType() {
        return reportSectionType;
    }

}
