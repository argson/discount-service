package pl.inpost.api.domain.services;

import lombok.RequiredArgsConstructor;
import pl.inpost.api.domain.model.DiscountLevel;
import pl.inpost.api.domain.model.Policy;
import pl.inpost.api.domain.repositories.DiscountLevelDAOInterface;

import java.util.List;

@RequiredArgsConstructor
public class DiscountLevelService {
    private final Policy policy;
    private final DiscountLevelDAOInterface daoInterface;

    public List<DiscountLevel> getDiscountLevels() {
        return daoInterface.getDiscountLevels(policy);
    }
}
