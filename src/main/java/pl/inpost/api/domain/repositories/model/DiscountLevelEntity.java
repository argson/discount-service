package pl.inpost.api.domain.repositories.model;

import jakarta.persistence.*;
import lombok.*;
import pl.inpost.api.domain.model.Policy;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DiscountLevelEntity {
    @NonNull
    @Id
    private Long productCountThreshold;

    @NonNull
    private String discountParameters;

    @NonNull
    @Enumerated(EnumType.ORDINAL)
    private Policy policy;
}
