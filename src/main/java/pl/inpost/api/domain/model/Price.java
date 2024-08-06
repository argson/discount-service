package pl.inpost.api.domain.model;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Price implements Serializable {
    static final long serialVersionUID = 42L;

    private BigDecimal value;
    private DenominationEnum denomination;

    public enum DenominationEnum {
        PLN,
        USD,
        EUR
    }
}

