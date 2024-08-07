package pl.inpost.api.domain.repositories.model;

import jakarta.persistence.*;
import lombok.*;
import pl.inpost.api.domain.model.Policy;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@IdClass(DiscountLevelKey.class)
public class DiscountLevelEntity {
    @NonNull
    @Id
    private Long productCountThreshold;

    @NonNull
    private String discountParameters;

    @NonNull
    @Id
    @Enumerated(EnumType.ORDINAL)
    private Policy policy;
}
