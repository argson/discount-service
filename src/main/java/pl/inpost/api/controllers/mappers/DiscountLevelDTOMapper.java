package pl.inpost.api.controllers.mappers;

import org.mapstruct.Mapper;
import pl.inpost.api.domain.model.DiscountLevel;
import pl.inpost.api.dto.model.DiscountLevelDTO;

@Mapper(componentModel = "spring")
public interface DiscountLevelDTOMapper {
     DiscountLevelDTO mapFrom(DiscountLevel source);
}
