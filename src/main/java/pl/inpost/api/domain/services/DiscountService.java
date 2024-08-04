package pl.inpost.api.domain.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.inpost.api.domain.ApiInterface;
import pl.inpost.api.domain.model.Discount;
import pl.inpost.api.domain.model.Policy;
import pl.inpost.api.domain.model.Price;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static pl.inpost.api.domain.model.Price.DenominationEnum.PLN;

@Service
@AllArgsConstructor
public class DiscountService implements ApiInterface {
    private final ProductsService productsService;

    public Discount calculateDiscount(UUID productId, Long productCount, List<Policy> policies) {
        var product = productsService.getProduct(productId);
        return Discount.builder()
                .product(product)
                .discount(Price.builder()
                        .denomination(PLN)
                        .value(BigDecimal.valueOf(90))
                        .build()
                )
                .orderCount(productCount)
                .policies(policies)
                .build();

    }
}
