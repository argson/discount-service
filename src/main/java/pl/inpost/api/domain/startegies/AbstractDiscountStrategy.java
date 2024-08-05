package pl.inpost.api.domain.startegies;

import lombok.NonNull;
import pl.inpost.api.domain.model.DiscountLevel;
import pl.inpost.api.domain.model.Price;

import java.util.List;
import java.util.Optional;

public abstract class AbstractDiscountStrategy<T> implements DiscountStrategy{
    protected Optional<T> discountValue;

    protected AbstractDiscountStrategy(Long productCount, List<DiscountLevel<T>> discountLevels) {
        discountValue = discountLevels.stream()
                .filter(dl -> dl.productCount().compareTo(productCount) <= 0)
                .max(DiscountLevel::compareTo)
                .map(DiscountLevel::discountValue);
    }

    @Override
    public Price calculateDiscount(Price inputPrice) {
        return discountValue
                .map(discount -> applyDiscount(discount, inputPrice))
                .orElse(inputPrice);
    }

    @NonNull
    abstract protected Price applyDiscount(@NonNull T discountValue, @NonNull Price inputPrice);
}
