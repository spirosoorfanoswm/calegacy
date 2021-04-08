package eu.ark.creditark.services.creditarkservices.services;

import eu.ark.creditark.services.creditarkservices.dto.reports.ReportGraphSection;
import eu.ark.creditark.services.creditarkservices.dto.reports.ReportSectionTable;
import eu.ark.creditark.services.creditarkservices.enums.ReportSectionType;
import net.sf.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.ColumnBuilder;
import net.sf.dynamicreports.report.constant.Orientation;
import org.springframework.stereotype.Service;

import java.util.List;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

@Service
public class PdfReportBuilderServiceImpl implements PdfReportBuilderService {

    @Override
    public JasperConcatenatedReportBuilder clienteleDistributionReport(List<ReportGraphSection> sections) {
        JasperConcatenatedReportBuilder report = concatenatedReport();
        JasperReportBuilder[] reports = new JasperReportBuilder[sections.size()];

        int cnt = 0;

        for(ReportGraphSection section:sections) {
            JasperReportBuilder rp  = report();
            rp.summary(section.create())
                    .columns(
                            section.createTextColumnBuilder(section.fields.get(0), section.labels.get(0), false),
                            section.createTextColumnBuilder(section.fields.get(1), section.labels.get(1), true),
                            section.createTextColumnBuilder(section.fields.get(2), section.labels.get(2), true))
                    .setDataSource(section.createSeries(
                            section.labels,
                            section.series,
                            section.fields.stream().toArray(String[]::new)))
                    .title(cmp.text(section.title));
            reports[cnt++] = rp;
        }

        report.concatenate(reports);
        return report;
    }


    public JasperConcatenatedReportBuilder clienteleStatisticsReport(List<ReportGraphSection> sections) {
        JasperConcatenatedReportBuilder report = concatenatedReport();
        JasperReportBuilder[] reports = new JasperReportBuilder[sections.size()];

        int cnt = 0;
        for(ReportGraphSection section:sections) {
            JasperReportBuilder rp  = report();
            if(section.getReportSectionType().equals(ReportSectionType.TABLE)) {
                rp.columns(
                        createSectionList(section, section.fields.size(), false))
                        .setDataSource(((ReportSectionTable)section).createSeriesTable(
                                section.labels,
                                section.seriesText,
                                section.fields.stream().toArray(String[]::new)))
                        .title(cmp.text(section.title))
                        .setPrintOrder(Orientation.VERTICAL);
            } else {
                rp.summary(section.create())
                        .columns(
                                section.createTextColumnBuilder(section.fields.get(0), section.labels.get(1), false),
                                section.createTextColumnBuilder(section.fields.get(1), section.labels.get(0), true)
                        )
                        .setDataSource(section.createSeries(
                                section.labels,
                                section.series,
                                section.fields.stream().toArray(String[]::new)))
                        .title(cmp.text(section.title))
                        .setPrintOrder(Orientation.HORIZONTAL);
            }
            reports[cnt++] = rp;
        }

        report.concatenate(reports);
        return report;
    }

    public JasperConcatenatedReportBuilder customerDetailsReport(List<ReportGraphSection> sections) {
        JasperConcatenatedReportBuilder report = concatenatedReport();
        JasperReportBuilder[] reports = new JasperReportBuilder[sections.size()];
        int cnt = 0;

        for(ReportGraphSection section:sections) {
            JasperReportBuilder rp  = report();

            rp.columns(createSectionList(section, section.fields.size(), false))
                    .setDataSource(((ReportSectionTable)section).createSeriesTable(
                            section.labels,
                            section.seriesText,
                            section.fields.stream().toArray(String[]::new)))
                    .title(cmp.text(section.title))
                    .setPrintOrder(Orientation.VERTICAL);
            reports[cnt++] = rp;
        }
        report.concatenate(reports);
        return report;
    }

    private ColumnBuilder[] createSectionList(ReportGraphSection section, int colStop,  boolean numeric) {
        ColumnBuilder[] columns = new ColumnBuilder[colStop];
        for(int index = 0; index<colStop; index++) {

            columns[index] = section.createTextColumnBuilder(section.fields.get(index), section.labels.get(index), numeric);
        }
        return columns;
    }
}
