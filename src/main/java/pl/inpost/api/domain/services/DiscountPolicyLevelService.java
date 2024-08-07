package pl.inpost.api.domain.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.inpost.api.domain.model.DiscountLevel;
import pl.inpost.api.domain.model.Policy;
import pl.inpost.api.repositories.DiscountLevelDAOInterface;

import java.util.List;

@Service
@RequiredArgsConstructor
class DiscountPolicyLevelService implements DiscountPolicyServiceInterface {
    private final DiscountLevelDAOInterface discountLevelDAOInterface;

    public List<DiscountLevel> getDiscountPolicyLevels(Policy policy) {
        return discountLevelDAOInterface.getDiscountLevels(policy);
    }
}
