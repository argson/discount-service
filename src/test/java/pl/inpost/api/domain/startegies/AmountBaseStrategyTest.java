package pl.inpost.api.domain.startegies;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.inpost.api.domain.model.DiscountLevel;
import pl.inpost.api.domain.model.Policy;
import pl.inpost.api.domain.model.Price;
import pl.inpost.api.domain.services.DiscountLevelService;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pl.inpost.api.domain.helpers.DiscountParameterHelper.discountParameterForAmount;

class AmountBaseStrategyTest {
    @Test
    void testApplyDiscountSuccessfully() {
        //given
        var productCount = 12L;
        var inputPrice = Price.builder().denomination(Price.DenominationEnum.PLN).value(BigDecimal.valueOf(100.0)).build();

        var discountLevels = List.of(
                new DiscountLevel(5L, discountParameterForAmount(7.0, Price.DenominationEnum.PLN), Policy.AMOUNT_BASED),
                new DiscountLevel(10L, discountParameterForAmount(10.0, Price.DenominationEnum.PLN), Policy.AMOUNT_BASED)
        );

        var discountLevelService = mock(DiscountLevelService.class);
        when(discountLevelService.getDiscountLevels(Policy.AMOUNT_BASED)).thenReturn(discountLevels);
        //when
        var priceAfterDiscount = new AmountBaseStrategy(productCount, discountLevelService).calculateDiscount(inputPrice);
        //then
        Assertions.assertThat(priceAfterDiscount).extracting(Price::getValue).isEqualTo(BigDecimal.valueOf(90.0));
    }

    @Test
    void testWrongDenominations() {
        //given
        var productCount = 12L;
        var inputPrice = Price.builder().denomination(Price.DenominationEnum.USD).value(BigDecimal.valueOf(100.0)).build();

        var discountLevels = List.of(
                new DiscountLevel(5L, discountParameterForAmount(7.0, Price.DenominationEnum.PLN), Policy.AMOUNT_BASED),
                new DiscountLevel(10L, discountParameterForAmount(10.0, Price.DenominationEnum.PLN), Policy.AMOUNT_BASED)
        );

        var discountLevelService = mock(DiscountLevelService.class);
        when(discountLevelService.getDiscountLevels(Policy.AMOUNT_BASED)).thenReturn(discountLevels);
        try {
            //when
            new AmountBaseStrategy(productCount, discountLevelService).calculateDiscount(inputPrice);

            //then
            Assertions.fail("IllegalStateException was expected!");
        } catch (IllegalStateException ex) {
            Assertions.assertThat(ex).hasMessage("Discount AmountBase can be apply only for the same currency denominations! Product price denomination is USD but discount expect PLN.");
        }
    }
}