package pl.inpost.api.domain.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class Price {
    private BigDecimal value;
    private DenominationEnum denomination;

    public enum DenominationEnum {
        PLN,
        USD,
        EUR
    }
}

