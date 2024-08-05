package pl.inpost.api.domain.startegies;

import pl.inpost.api.domain.model.Price;

public interface DiscountStrategy {
    Price calculateDiscount(Price inputPrice);
}
