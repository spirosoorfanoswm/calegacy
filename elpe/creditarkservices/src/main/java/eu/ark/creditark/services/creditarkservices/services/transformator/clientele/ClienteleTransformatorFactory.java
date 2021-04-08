package eu.ark.creditark.services.creditarkservices.services.transformator.clientele;

import org.springframework.stereotype.Component;

import eu.ark.creditark.services.creditarkservices.enums.DtoTransformationType;

@Component
public interface ClienteleTransformatorFactory {
    public ClienteleTransformator getTransformatorFactory(DtoTransformationType dtoTransformationType);
}
