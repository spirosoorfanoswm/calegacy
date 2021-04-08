package eu.ark.creditark.services.creditarkservices.services;

import eu.ark.creditark.services.creditarkservices.dto.reports.ReportGraphSection;
import net.sf.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder;

import java.util.List;

public interface PdfReportBuilderService {
    JasperConcatenatedReportBuilder clienteleDistributionReport(List<ReportGraphSection> sections);
    JasperConcatenatedReportBuilder clienteleStatisticsReport(List<ReportGraphSection> sections);
    JasperConcatenatedReportBuilder customerDetailsReport(List<ReportGraphSection> sections);
}
