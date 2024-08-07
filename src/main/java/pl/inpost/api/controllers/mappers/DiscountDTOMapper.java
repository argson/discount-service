package pl.inpost.api.controllers.mappers;

import org.mapstruct.Mapper;
import pl.inpost.api.domain.model.Discount;
import pl.inpost.api.dto.model.DiscountDTO;

@Mapper(componentModel = "spring")
public interface DiscountDTOMapper {
    DiscountDTO mapTo(Discount source);
}
