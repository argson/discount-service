package pl.inpost.api.domain.policies;

import lombok.NonNull;
import pl.inpost.api.domain.model.DiscountLevel;
import pl.inpost.api.domain.model.DiscountParameter;
import pl.inpost.api.domain.model.Policy;
import pl.inpost.api.domain.model.Price;
import pl.inpost.api.domain.services.DiscountPolicyServiceInterface;

import java.util.Collections;
import java.util.List;

abstract class AbstractDiscountPolicy implements DiscountPolicy {
    protected List<DiscountParameter> discountParameters;

    protected AbstractDiscountPolicy(@NonNull Long productCount,
                                     @NonNull DiscountPolicyServiceInterface discountPolicyServiceInterface) {
        var discountLevels = discountPolicyServiceInterface.getDiscountPolicyLevels(Policy.AMOUNT_BASED);
        discountParameters = discountLevels.stream()
                .filter(dl -> dl.productCountThreshold().compareTo(productCount) <= 0)
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
