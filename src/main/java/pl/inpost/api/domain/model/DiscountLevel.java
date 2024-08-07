package pl.inpost.api.domain.model;

import java.util.List;

public record DiscountLevel(Long productCountThreshold,
                            List<DiscountParameter> discountParameters,
                            Policy policy) implements Comparable<DiscountLevel> {
    @Override
    public int compareTo(DiscountLevel discountLevel) {
        return this.productCountThreshold.compareTo(discountLevel.productCountThreshold());
    }

}
