package pl.inpost.api.persistance.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import pl.inpost.api.model.Policy;
import pl.inpost.api.model.Price;
import pl.inpost.api.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ProductEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private BigDecimal price;

    @Enumerated(EnumType.ORDINAL)
    private Price.DenominationEnum priceDenomination;
}
