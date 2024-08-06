package pl.inpost.api.domain.model;

import java.io.Serial;
import java.io.Serializable;


public record DiscountParameter(DiscountParameter.ParameterName name,
                                Object value) implements Serializable {
    @Serial
    private static final long serialVersionUID = 0L;

    public enum ParameterName {
        PRICE,
        DENOMINATION,
        PERCENTAGE
    }
}
