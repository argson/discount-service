package pl.inpost.api.domain.services;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import pl.inpost.api.domain.model.Discount;
import pl.inpost.api.domain.model.Policy;
import pl.inpost.api.domain.policies.DiscountPolicy;
import pl.inpost.api.domain.policies.PolicyFactory;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
class DiscountService implements DiscountServiceInterface {
    private final ProductService productService;
    private final PolicyFactory policyFactory;

    public Discount calculateDiscount(@NonNull UUID productId,
                                      @NonNull Long productCount,
                                      @NonNull List<Policy> policies) {
        var product = productService.getProduct(productId);

        var finalDiscount = product.getPrice();
        List<DiscountPolicy> discountStrategies = getDiscountStrategies(productCount, policies);
        for (var discountStrategy : discountStrategies) {
            finalDiscount = discountStrategy.calculateDiscount(finalDiscount);
        }

        return Discount.builder()
                .product(product)
                .discount(finalDiscount)
                .orderCount(productCount)
                .policies(policies)
                .build();
    }

    private List<DiscountPolicy> getDiscountStrategies(@NonNull Long productCount,
                                                       @NonNull List<Policy> policies) {
        var discountStrategies = policies.stream()
                .map(p -> policyFactory.createDiscountPolicy(p, productCount))
                .toList();
        return discountStrategies;
    }
}
