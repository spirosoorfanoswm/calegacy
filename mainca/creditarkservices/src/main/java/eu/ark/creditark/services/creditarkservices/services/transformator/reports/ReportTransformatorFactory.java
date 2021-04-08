package eu.ark.creditark.services.creditarkservices.services.transformator.reports;

import eu.ark.creditark.services.creditarkservices.enums.DtoTransformationType;
import org.springframework.stereotype.Component;

@Component
public interface ReportTransformatorFactory {
    public ReportTransformator getTransformatorFactory(DtoTransformationType dtoTransformationType);
}
