package pl.inpost.api.dto.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;
import pl.inpost.api.domain.model.DiscountParameter;
import pl.inpost.api.dto.model.DiscountParameterDTO;

@Mapper(componentModel = "spring")
public interface DiscountParameterDTOMMapper {
    default DiscountParameter mapFrom(DiscountParameterDTO source){
        return new DiscountParameter(DiscountParameter.ParameterName.valueOf(source.getName().name()),source.getValue());
    }


    default DiscountParameterDTO mapTo(DiscountParameter source){
        return new DiscountParameterDTO()
                .name(DiscountParameterDTO.NameEnum.valueOf(source.name().name()))
                .value(source.value().toString());
    }
}
