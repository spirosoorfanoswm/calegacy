package eu.ark.creditark.services.creditarkservices.services.transformator.reports;

import eu.ark.creditark.services.creditarkservices.dto.input.ClienteleTransformatorInputDto;
import eu.ark.creditark.services.creditarkservices.dto.reports.ReportInput;

public interface ReportTransformator {

    public <T extends Object> T transform(ReportInput input);
}
