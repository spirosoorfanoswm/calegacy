package eu.ark.creditark.services.creditarkservices.services.transformator.scenarios;

import eu.ark.creditark.services.creditarkservices.enums.DtoTransformationType;

public interface ScenarioStatisticsToUIStatisticsTransformatorFactory {
    public ScenarioStatisticsToUIStatisticsTransformator getTransformatorFactory(
            DtoTransformationType dtoTransformationType);
}
