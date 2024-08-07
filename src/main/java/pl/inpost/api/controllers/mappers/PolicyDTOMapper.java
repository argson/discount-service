package pl.inpost.api.controllers.mappers;

import org.mapstruct.Mapper;
import pl.inpost.api.domain.model.Policy;
import pl.inpost.api.dto.model.PolicyDTO;

@Mapper(componentModel = "spring")
public interface PolicyDTOMapper {
    PolicyDTO mapTo(Policy source);
    Policy mapFrom(PolicyDTO source);
}
