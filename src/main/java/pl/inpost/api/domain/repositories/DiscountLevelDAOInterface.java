package pl.inpost.api.domain.repositories;

import pl.inpost.api.domain.model.DiscountLevel;
import pl.inpost.api.domain.model.Policy;

import java.util.List;

public interface DiscountLevelDAOInterface {
    List<DiscountLevel> getDiscountLevels(Policy policy);
    List<DiscountLevel> save(List<DiscountLevel> discountLevels);
}
