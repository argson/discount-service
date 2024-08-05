package pl.inpost.api.domain.startegies;

import lombok.NonNull;
import pl.inpost.api.domain.model.DiscountLevel;
import pl.inpost.api.domain.model.Price;

import java.math.BigDecimal;
import java.util.List;

public class PercentageBaseStrategy extends AbstractDiscountStrategy<Integer> {
    private static final List<DiscountLevel<Integer>> discountLevels = List.of(
            new DiscountLevel(10L, 5),
            new DiscountLevel(50L, 7),
            new DiscountLevel(100L, 10)
    );

    public PercentageBaseStrategy(Long productCount) {
        super(productCount, discountLevels);
    }

    @NonNull
    protected Price applyDiscount(@NonNull Integer discountValue, @NonNull Price inputPrice) {
        var beforeDiscount = inputPrice.getValue();
        var multiplier = BigDecimal.valueOf(discountValue).divide(BigDecimal.valueOf(100));
        var percentFromValue = beforeDiscount.multiply(multiplier);
        var afterDiscount = beforeDiscount.subtract(percentFromValue);
        return Price.builder()
                .value(afterDiscount)
                .denomination(inputPrice.getDenomination())
                .build();
    }
}
