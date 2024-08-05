package pl.inpost.api.domain.startegies;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.inpost.api.domain.model.Price;

import java.math.BigDecimal;

class AmountBaseStrategyTest {
    @Test
    void testApplyDiscountSuccessfully() {
        //given
        var productCount = 12L;
        var discountPrice = Price.builder()
                .denomination(Price.DenominationEnum.PLN)
                .value(BigDecimal.valueOf(10))
                .build();
        var inputPrice = Price.builder()
                .denomination(Price.DenominationEnum.PLN)
                .value(BigDecimal.valueOf(100))
                .build();
        ;
        //when
        var priceAfterDiscount = new AmountBaseStrategy(productCount).applyDiscount(discountPrice, inputPrice);
        //then
        Assertions.assertThat(priceAfterDiscount).extracting(Price::getValue).isEqualTo(BigDecimal.valueOf(90));
    }

    @Test
    void testWrongDenominations() {
        //given
        var productCount = 12L;
        var discountPrice = Price.builder()
                .denomination(Price.DenominationEnum.USD)
                .value(BigDecimal.valueOf(10))
                .build();
        var inputPrice = Price.builder()
                .denomination(Price.DenominationEnum.PLN)
                .value(BigDecimal.valueOf(100))
                .build();
        ;

        try {
            //when
            new AmountBaseStrategy(productCount).applyDiscount(discountPrice, inputPrice);

            //then
            Assertions.fail("IllegalStateException was expected!");
        } catch (IllegalStateException ex) {
            Assertions.assertThat(ex)
                    .hasMessage("Discount AmountBase can be apply only for the same currency denominations! Discount denomination PLN != USD");
        }
    }
}