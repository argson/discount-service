package pl.inpost.api.domain.startegies;

import lombok.NonNull;
import pl.inpost.api.domain.model.DiscountLevel;
import pl.inpost.api.domain.model.DiscountParameter;
import pl.inpost.api.domain.model.Price;
import pl.inpost.api.domain.services.DiscountLevelService;

import java.util.Collections;
import java.util.List;

public abstract class AbstractDiscountStrategy implements DiscountStrategy {
    protected List<DiscountParameter> discountParameters;

    protected AbstractDiscountStrategy(@NonNull Long productCount,
                                       @NonNull DiscountLevelService discountLevelService) {
        var discountLevels = discountLevelService.getDiscountLevels();
        discountParameters = discountLevels.stream()
                .filter(dl -> dl.productCount().compareTo(productCount) <= 0)
                .max(DiscountLevel::compareTo)
                .map(DiscountLevel::discountParameters)
                .orElse(Collections.emptyList());
    }

    @Override
    public Price calculateDiscount(Price inputPrice) {
        validateParameters(discountParameters);
        return applyDiscount(discountParameters, inputPrice);
    }



    @NonNull
    abstract protected Price applyDiscount(@NonNull List<DiscountParameter> discountParameters,
                                           @NonNull Price inputPrice);


    abstract void validateParameters(List<DiscountParameter> discountParameters);
}
