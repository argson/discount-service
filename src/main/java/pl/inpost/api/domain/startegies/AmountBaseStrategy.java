package pl.inpost.api.domain.startegies;

import lombok.NonNull;
import pl.inpost.api.domain.model.DiscountLevel;
import pl.inpost.api.domain.model.Price;

import java.math.BigDecimal;
import java.util.List;

import static pl.inpost.api.domain.model.Price.DenominationEnum.PLN;

public class AmountBaseStrategy extends AbstractDiscountStrategy<Price> {
    private static final List<DiscountLevel<Price>> discountLevels = List.of(
            new DiscountLevel(10L, getPrice(2, PLN)),
            new DiscountLevel(100L, getPrice(5, PLN)),
            new DiscountLevel(1000L, getPrice(10, PLN))
    );

    public AmountBaseStrategy(Long productCount) {
        super(productCount, discountLevels);
    }

    @NonNull
    protected Price applyDiscount(@NonNull Price discountValue, @NonNull Price inputPrice) {
        if (inputPrice.getDenomination() != discountValue.getDenomination()) {
            throw new IllegalStateException(String.format("Discount AmountBase can be apply only for the same currency denominations! Discount denomination %s != %s", inputPrice.getDenomination(), discountValue.getDenomination()));
        }
        var priceAfterDiscount = inputPrice.getValue().subtract(discountValue.getValue());
        return Price.builder()
                .value(priceAfterDiscount)
                .denomination(inputPrice.getDenomination())
                .build();
    }

    @NonNull
    private static Price getPrice(@NonNull Integer priceValue, @NonNull Price.DenominationEnum denomination) {
        return Price.builder()
                .value(BigDecimal.valueOf(priceValue))
                .denomination(denomination)
                .build();
    }
}
