package eu.ark.creditark.services.creditarkservices.services.transformator.clientele;

import eu.ark.creditark.services.creditarkservices.dto.input.ClienteleTransformatorInputDto;

public interface ClienteleTransformator {

    public <T extends Object> T transform(ClienteleTransformatorInputDto input);
}
