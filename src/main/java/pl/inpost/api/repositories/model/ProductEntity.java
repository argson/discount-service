package pl.inpost.api.repositories.model;

import jakarta.persistence.*;
import lombok.*;
import pl.inpost.api.domain.model.Price;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ProductEntity {
    @NonNull
    @Id()
    //@GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NonNull
    private String name;

    @NonNull
    private BigDecimal price;

    @NonNull
    @Enumerated(EnumType.ORDINAL)
    private Price.DenominationEnum priceDenomination;
}
