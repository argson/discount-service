package pl.inpost.api.domain.policies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.inpost.api.domain.model.Policy;
import pl.inpost.api.domain.services.DiscountPolicyServiceInterface;

@Component
@RequiredArgsConstructor
public class PolicyFactory {
    private final DiscountPolicyServiceInterface discountPolicyServiceInterface;

    public DiscountPolicy createDiscountPolicy(Policy policy, Long productCount) {
        return switch (policy) {
            case AMOUNT_BASED ->
                    new AmountBasePolicy(productCount, discountPolicyServiceInterface);
            case PERCENTAGE_BASED ->
                    new PercentageBasePolicy(productCount, discountPolicyServiceInterface);
        };
    }
}
