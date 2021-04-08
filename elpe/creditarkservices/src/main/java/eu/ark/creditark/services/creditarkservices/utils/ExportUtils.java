package eu.ark.creditark.services.creditarkservices.utils;

import eu.ark.creditark.services.creditarkservices.dto.input.CustomersInputDto;

public class ExportUtils {

    public static StringBuilder createExportFileNamePrefix(CustomersInputDto input) {
        StringBuilder exportFilenamePrefix= new StringBuilder("export").append("_customers_");
        exportFilenamePrefix.append(DateUtils.dateToString(input.getSnapshotDate())).append("_");
        if(input.getScenarioId()>0) exportFilenamePrefix.append("scenario")
                .append("_").append(input.getScenarioId()).append("_");
        else exportFilenamePrefix.append("main").append("_");
        exportFilenamePrefix.append(
                input.getPortfolioId()==-1?"m1":input.getPortfolioId())
                .append("_");
        return exportFilenamePrefix;
    }
}
