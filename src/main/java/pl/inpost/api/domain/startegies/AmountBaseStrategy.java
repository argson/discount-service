package pl.inpost.api.domain.startegies;

import lombok.NonNull;
import pl.inpost.api.domain.model.DiscountParameter;
import pl.inpost.api.domain.model.Price;
import pl.inpost.api.domain.services.DiscountLevelService;

import java.util.List;

import static pl.inpost.api.domain.helpers.DiscountParameterHelper.*;
import static pl.inpost.api.domain.model.DiscountParameter.ParameterName.DENOMINATION;
import static pl.inpost.api.domain.model.DiscountParameter.ParameterName.PRICE;

public class AmountBaseStrategy extends AbstractDiscountStrategy {


    public AmountBaseStrategy(@NonNull Long productCount,
                              @NonNull DiscountLevelService discountLevelService) {
        super(productCount, discountLevelService);
    }

    @Override
    protected @NonNull Price applyDiscount(@NonNull List<DiscountParameter> discountParameters,
                                           @NonNull Price inputPrice) {
        var denomination = getDenomination(discountParameters);
        var priceValue = getPriceValue(discountParameters);
        if (inputPrice.getDenomination() != denomination) {
            throw new IllegalStateException(String.format("Discount AmountBase can be apply only for the same currency denominations! Product price denomination is %s but discount expect %s.", inputPrice.getDenomination(), denomination));
        }
        var priceAfterDiscount = inputPrice.getValue().subtract(priceValue);
        return Price.builder()
                .value(priceAfterDiscount)
                .denomination(inputPrice.getDenomination())
                .build();
    }

    @Override
    void validateParameters(@NonNull List<DiscountParameter> discountParameters) {
        if (isUnavailableParameter(discountParameters, PRICE)) {
            throw new IllegalStateException("Expected parameter PRICE for AmountBase discount policy!");
        }
        if (isUnavailableParameter(discountParameters, DENOMINATION)) {
            throw new IllegalStateException("Expected parameter PRICE for AmountBase discount policy!");
        }
    }
}
