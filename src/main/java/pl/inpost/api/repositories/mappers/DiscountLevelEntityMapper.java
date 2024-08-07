package pl.inpost.api.repositories.mappers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.mapstruct.Mapper;
import pl.inpost.api.domain.model.DiscountLevel;
import pl.inpost.api.domain.model.DiscountParameter;
import pl.inpost.api.repositories.model.DiscountLevelEntity;

import java.util.ArrayList;


@Mapper(componentModel = "spring")
public interface DiscountLevelEntityMapper {

    @SneakyThrows
    default DiscountLevel mapFrom(DiscountLevelEntity source) {
        var json = source.getDiscountParameters();
        var objectMapper = new ObjectMapper();
        var typeRef = new TypeReference<ArrayList<DiscountParameter>>() {};

        return new DiscountLevel(
                source.getProductCountThreshold(),
                objectMapper.readValue(json, typeRef),
                source.getPolicy()
        );
    }

    @SneakyThrows
    default DiscountLevelEntity mapTo(DiscountLevel source) {
        var objectMapper = new ObjectMapper();
        return new DiscountLevelEntity(
                source.productCountThreshold(),
                objectMapper.writeValueAsString(source.discountParameters()),
                source.policy()
        );
    }
}
