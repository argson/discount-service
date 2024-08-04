package pl.inpost.api.dto.mappers;

import org.mapstruct.Mapper;
import pl.inpost.api.domain.model.Discount;
import pl.inpost.api.dto.model.DiscountDTO;

@Mapper(componentModel = "spring")
public interface DiscountDTOMapper {
    DiscountDTO map(Discount source);
}
