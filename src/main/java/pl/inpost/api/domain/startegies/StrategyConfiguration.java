package pl.inpost.api.domain.startegies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.inpost.api.domain.model.Policy;
import pl.inpost.api.domain.repositories.service.DiscountLevelDAOService;
import pl.inpost.api.domain.services.DiscountLevelService;

@Component
@RequiredArgsConstructor
public class StrategyConfiguration {
    private final DiscountLevelDAOService discountLevelDAOService;

    public DiscountStrategy map(Policy policy, Long productCount) {
        return switch (policy) {
            case AMOUNT_BASED ->
                    new AmountBaseStrategy(productCount, new DiscountLevelService(policy, discountLevelDAOService));
            case PERCENTAGE_BASED ->
                    new PercentageBaseStrategy(productCount, new DiscountLevelService(policy, discountLevelDAOService));
        };
    }
}
