package pl.inpost.api.dto.mappers;

import org.mapstruct.Mapper;
import pl.inpost.api.domain.model.Price;
import pl.inpost.api.dto.model.PriceDTO;

@Mapper(componentModel = "spring")
public interface PriceDTOMapper {
    PriceDTO map(Price source);
}
