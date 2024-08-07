package pl.inpost.api.repositories.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
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
    private UUID id;

    @NonNull
    private String name;

    @NonNull
    private BigDecimal price;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Price.DenominationEnum priceDenomination;
}
