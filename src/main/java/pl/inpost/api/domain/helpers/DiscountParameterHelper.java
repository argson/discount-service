package pl.inpost.api.domain.helpers;

import lombok.NonNull;
import pl.inpost.api.domain.model.DiscountParameter;
import pl.inpost.api.domain.model.DiscountParameter.ParameterName;
import pl.inpost.api.domain.model.Price;

import java.math.BigDecimal;
import java.util.List;

import static pl.inpost.api.domain.model.DiscountParameter.ParameterName.*;

public class DiscountParameterHelper {
    public static Price.DenominationEnum getDenomination(List<DiscountParameter> discountParameters) {
        var value = (String) getParameter(discountParameters, DENOMINATION).value();
        return Price.DenominationEnum.valueOf(value);
    }

    public static BigDecimal getPriceValue(List<DiscountParameter> discountParameters) {
        var value = (Double)getParameter(discountParameters, PRICE).value();
        return BigDecimal.valueOf(value);
    }

    public static Integer getPercentageValue(List<DiscountParameter> discountParameters) {
        return (Integer) getParameter(discountParameters, PERCENTAGE).value();
    }

    public static boolean isUnavailableParameter(List<DiscountParameter> discountParameters, DiscountParameter.ParameterName expected) {
        return discountParameters.stream().noneMatch(discountParameter -> discountParameter.name().equals(expected));
    }
    @NonNull
    private static DiscountParameter getParameter(@NonNull List<DiscountParameter> discountParameters,
                                           @NonNull ParameterName param) {
        return discountParameters.stream().filter(discountParameter -> discountParameter.name().equals(param)).findFirst().orElseThrow();
    }

    public static List<DiscountParameter> discountParameterForAmount(Double value, Price.DenominationEnum denomination) {
        return List.of(
                new DiscountParameter(PRICE, value),
                new DiscountParameter(DENOMINATION, denomination.name())
        );
    }

    public static List<DiscountParameter> discountParameterForPercentage(Integer value) {
        return List.of(
                new DiscountParameter(PERCENTAGE, value)
        );
    }
}
