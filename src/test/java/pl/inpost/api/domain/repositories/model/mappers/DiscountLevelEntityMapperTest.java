package pl.inpost.api.domain.repositories.model.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import pl.inpost.api.domain.model.DiscountLevel;
import pl.inpost.api.domain.model.Policy;
import pl.inpost.api.domain.model.Price;
import pl.inpost.api.repositories.mappers.DiscountLevelEntityMapper;
import pl.inpost.api.repositories.model.DiscountLevelEntity;

import static pl.inpost.api.domain.helpers.DiscountParameterHelper.discountParameterForAmount;
import static pl.inpost.api.domain.helpers.DiscountParameterHelper.discountParameterForPercentage;

class DiscountLevelEntityMapperTest {
    ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Test
    void testMapToForAmountPolicyDiscount() {
        //given
        var mapper = Mappers.getMapper(DiscountLevelEntityMapper.class);
        var discountParameters = discountParameterForAmount(10.0, Price.DenominationEnum.PLN);
        var discountParametersJson = objectMapper.writeValueAsString(discountParameters);
        var discountLevelEntity = new DiscountLevelEntity(10L, discountParametersJson, Policy.AMOUNT_BASED);

        //when
        var priceDiscountLevel = mapper.mapFrom(discountLevelEntity);
        //then
        Assertions.assertThat(priceDiscountLevel)
                .extracting(DiscountLevel::productCountThreshold, DiscountLevel::discountParameters)
                .contains(10L, discountParameters);
    }

    @SneakyThrows
    @Test
    void testMapToForPercentageDiscount() {
        //given
        var mapper = Mappers.getMapper(DiscountLevelEntityMapper.class);

        var discountParameters = discountParameterForPercentage(12);
        var discountParametersJson = objectMapper.writeValueAsString(discountParameters);
        var discountLevelEntity = new DiscountLevelEntity(10L, discountParametersJson, Policy.PERCENTAGE_BASED);

        //when
        var priceDiscountLevel = mapper.mapFrom(discountLevelEntity);
        //then
        Assertions.assertThat(priceDiscountLevel)
                .extracting(DiscountLevel::productCountThreshold, DiscountLevel::discountParameters)
                .contains(10L, discountParameters);
    }
}