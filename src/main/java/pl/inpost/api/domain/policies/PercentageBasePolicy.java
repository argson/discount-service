package pl.inpost.api.domain.policies;

import lombok.NonNull;
import pl.inpost.api.domain.model.DiscountParameter;
import pl.inpost.api.domain.model.Price;
import pl.inpost.api.domain.services.DiscountPolicyServiceInterface;

import java.math.BigDecimal;
import java.util.List;

import static pl.inpost.api.domain.helpers.DiscountParameterHelper.getPercentageValue;
import static pl.inpost.api.domain.helpers.DiscountParameterHelper.isUnavailableParameter;
import static pl.inpost.api.domain.model.DiscountParameter.ParameterName.PERCENTAGE;

class PercentageBasePolicy extends AbstractDiscountPolicy {
    public PercentageBasePolicy(Long productCount, DiscountPolicyServiceInterface discountPolicyServiceInterface) {
        super(productCount, discountPolicyServiceInterface);
    }

    @Override
    protected @NonNull Price applyDiscount(@NonNull List<DiscountParameter> discountParameters, @NonNull Price inputPrice) {
        var percent = getPercentageValue(discountParameters);
        var beforeDiscount = inputPrice.getValue();
        var multiplier = BigDecimal.valueOf(percent).divide(BigDecimal.valueOf(100));
        var percentFromValue = beforeDiscount.multiply(multiplier);
        var afterDiscount = beforeDiscount.subtract(percentFromValue);
        return Price.builder()
                .value(afterDiscount)
                .denomination(inputPrice.getDenomination())
                .build();
    }

    @Override
    void validateParameters(List<DiscountParameter> discountParameters) {
        if (isUnavailableParameter(discountParameters, PERCENTAGE)) {
            throw new IllegalStateException("Expected parameter PERCENTAGE for PercentageBase discount policy!");
        }
    }
}