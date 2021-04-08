package eu.ark.creditark.services.creditarkservices.utils;

import eu.ark.creditark.services.creditarkservices.dto.input.CustomersInputDto;

import java.time.LocalDateTime;

public class ExportUtils {

    public static StringBuilder createExportFileNamePrefix(CustomersInputDto input, Integer scenarioPrefix, String user) {
        StringBuilder exportFilenamePrefix= new StringBuilder("export").append("_customers_")
                .append(user).append("_");
        exportFilenamePrefix.append(DateUtils.dateToString(input.getSnapshotDate())).append("_");
        if(input.getScenarioId()>0)
            exportFilenamePrefix.append("scenario").append("_")
                    .append(input.getScenarioId()).append("_").append(scenarioPrefix).append("_");
        else
            exportFilenamePrefix.append("main").append("_");
        exportFilenamePrefix.append(
                input.getPortfolioId()==-1?"all_portfolios":input.getPortfolioId())
                .append("_");
        return exportFilenamePrefix;
    }
}
