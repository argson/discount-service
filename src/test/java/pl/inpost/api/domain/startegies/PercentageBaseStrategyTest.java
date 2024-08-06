package pl.inpost.api.domain.startegies;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.inpost.api.domain.helpers.DiscountParameterHelper;
import pl.inpost.api.domain.model.DiscountLevel;
import pl.inpost.api.domain.model.Policy;
import pl.inpost.api.domain.model.Price;
import pl.inpost.api.domain.services.DiscountLevelService;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pl.inpost.api.domain.helpers.DiscountParameterHelper.*;
import static pl.inpost.api.domain.helpers.DiscountParameterHelper.discountParameterForPercentage;

class PercentageBaseStrategyTest {

    @Test
    void testApplyDiscountSuccessfully() {
        //given
        var productCount = 12L;
        var inputPrice = Price.builder()
                .denomination(Price.DenominationEnum.PLN)
                .value(BigDecimal.valueOf(100))
                .build();

        var discountLevels = List.of(
                new DiscountLevel(5L, discountParameterForPercentage(5), Policy.PERCENTAGE_BASED),
                new DiscountLevel(10L, discountParameterForPercentage(10), Policy.PERCENTAGE_BASED)
        );

        var discountLevelService = mock(DiscountLevelService.class);
        when(discountLevelService.getDiscountLevels()).thenReturn(discountLevels);
        //when
        var priceAfterDiscount = new PercentageBaseStrategy(productCount, discountLevelService).calculateDiscount(inputPrice);
        //then
        Assertions.assertThat(priceAfterDiscount).extracting(Price::getValue).isEqualTo(BigDecimal.valueOf(90.0));
    }
}