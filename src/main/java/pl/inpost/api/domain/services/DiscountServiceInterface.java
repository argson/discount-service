package pl.inpost.api.domain.services;

import pl.inpost.api.domain.model.Discount;
import pl.inpost.api.domain.model.Policy;

import java.util.List;
import java.util.UUID;

public interface DiscountServiceInterface {
    Discount calculateDiscount(UUID productId, Long productCount, List<Policy> policies);
}
