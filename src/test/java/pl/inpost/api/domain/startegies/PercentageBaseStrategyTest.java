package pl.inpost.api.domain.startegies;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.inpost.api.domain.model.DiscountLevel;
import pl.inpost.api.domain.model.Price;

import java.math.BigDecimal;

class PercentageBaseStrategyTest {

    @Test
    void testApplyDiscountSuccessfully() {
        //given
        var productCount = 12L;
        var discountPercent = 10;
        var inputPrice = Price.builder()
                .denomination(Price.DenominationEnum.PLN)
                .value(BigDecimal.valueOf(100))
                .build();;
        //when
        var priceAfterDiscount = new PercentageBaseStrategy(productCount).applyDiscount(discountPercent, inputPrice);
        //then
        Assertions.assertThat(priceAfterDiscount).extracting(Price::getValue).isEqualTo(BigDecimal.valueOf(90.0));
    }
}