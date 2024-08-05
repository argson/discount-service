package pl.inpost.api.domain.startegies;

import pl.inpost.api.domain.model.Policy;

public class StrategyConfiguration {
    public static DiscountStrategy map(Policy policy, Long productCount) {
        return switch(policy) {
            case AMOUNT_BASED -> new AmountBaseStrategy(productCount);
            case PERCENTAGE_BASED -> new PercentageBaseStrategy(productCount);
        };
    }
}
