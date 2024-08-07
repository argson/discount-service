package pl.inpost.api.repositories.model;

import pl.inpost.api.domain.model.Policy;

import java.io.Serializable;

public class DiscountLevelKey implements Serializable {
    private Long productCountThreshold;
    private Policy policy;
}
