package eu.ark.creditark.services.creditarkservices.dto.reports;

import net.sf.jasperreports.engine.JRDataSource;

import java.util.List;

public interface ReportSectionTableActions {
    public JRDataSource createSeriesTable(List<String> labels,
                                              List<List<String>> series,
                                              String[] fields);
}
