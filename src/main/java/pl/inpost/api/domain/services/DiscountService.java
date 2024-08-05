package pl.inpost.api.domain.services;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import pl.inpost.api.domain.ApiInterface;
import pl.inpost.api.domain.model.Discount;
import pl.inpost.api.domain.model.Policy;
import pl.inpost.api.domain.startegies.DiscountStrategy;
import pl.inpost.api.domain.startegies.StrategyConfiguration;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DiscountService implements ApiInterface {
    private final ProductsService productsService;

    public Discount calculateDiscount(@NonNull UUID productId,
                                      @NonNull Long productCount,
                                      @NonNull List<Policy> policies) {
        var product = productsService.getProduct(productId);

        var finalDiscount = product.getPrice();
        List<DiscountStrategy> discountStrategies = getDiscountStrategies(productCount, policies);
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

    private List<DiscountStrategy> getDiscountStrategies(@NonNull Long productCount,
                                                         @NonNull List<Policy> policies) {
        var discountStrategies = policies.stream()
                .map(p -> StrategyConfiguration.map(p, productCount))
                .toList();
        return discountStrategies;
    }
}
