package pl.inpost.api.domain.services;

import pl.inpost.api.domain.model.DiscountLevel;
import pl.inpost.api.domain.model.Policy;

import java.util.List;

public interface DiscountPolicyServiceInterface {

    List<DiscountLevel> getDiscountPolicyLevels(Policy policy);
}
