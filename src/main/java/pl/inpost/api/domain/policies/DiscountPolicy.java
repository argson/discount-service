package pl.inpost.api.domain.policies;

import pl.inpost.api.domain.model.Price;

public interface DiscountPolicy {
    Price calculateDiscount(Price inputPrice);
}
