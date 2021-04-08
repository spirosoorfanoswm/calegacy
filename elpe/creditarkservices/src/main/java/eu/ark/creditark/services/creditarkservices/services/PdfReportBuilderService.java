package eu.ark.creditark.services.creditarkservices.services;

import eu.ark.creditark.services.creditarkservices.dto.reports.ReportGraphSection;
import net.sf.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;

import java.util.List;

public interface PdfReportBuilderService {
    public JasperConcatenatedReportBuilder clienteleDistributionReport(List<ReportGraphSection> sections);
    public JasperConcatenatedReportBuilder clienteleStatisticsReport(List<ReportGraphSection> sections);
}
